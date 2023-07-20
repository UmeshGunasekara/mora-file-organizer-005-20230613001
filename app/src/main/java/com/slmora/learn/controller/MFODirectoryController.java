/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/22/2023 11:00 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.common.file.util.MoraFileWriteAccessUtilities;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.dto.DirectoryDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.model.SearchPathModel;
import com.slmora.learn.service.IMFODirectoryService;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;
import com.slmora.learn.system.property.SingleSystemProperty;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *  This Class created for
 *  <ul>
 *      <li>....</li>
 *  </ul>
 *
 * @since   1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          6/22/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFODirectoryController {
    final static Logger LOGGER = LogManager.getLogger(MFODirectoryController.class);
    public void addDirectory(Path dir, Integer zipFileLevel, Integer driveCode){
        LOGGER.info("addDirectory dir : "+dir.toAbsolutePath().toString()+", zipFileLevel"+zipFileLevel+", driveCode : "+driveCode);
        if(dir != null && dir.toFile().isDirectory()){
            IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            DirectoryDto directoryDto = new DirectoryDto();
            MoraFileWriteAccessUtilities writeAccessUtilities = new MoraFileWriteAccessUtilities();

            try {
                directoryDto.setDirectoryFullPath(dir.toAbsolutePath().toString());
                EMFODirectory eDir = new EMFODirectory();

                if(!dir.toString().equals(dir.getRoot().toString())){
                    directoryDto.setDirectoryName(dir.getFileName().toString());
                    directoryDto.setDirectoryIsZip(zipFileLevel);
                    directoryDto.setFileTextPathAndSha256(dir);
                    directoryDto.setDirectoryLevel(writeAccessUtilities.getDirectoryLevel(dir)+1);
                    directoryDto.setDirectoryDriveCode(driveCode);
                    eDir = directoryDto.getEntity();

                    Path parentDir = dir.getParent();
                    if(parentDir != null && parentDir.toFile().isDirectory()){
                        LOGGER.info("addDirectory parentDir : "+parentDir.toAbsolutePath().toString()+", zipFileLevel"+zipFileLevel+", driveCode : "+driveCode);
                        Optional<List<EMFODirectory>> opListEntityDir = dirService.getAllMFODirectoryByDirectoryFullPathAndZipLevelDrive(parentDir.toAbsolutePath().toString(), zipFileLevel,driveCode);

                        if (opListEntityDir.isPresent()&&opListEntityDir.get().size()==1) {
                            eDir.setDirectoryParent(opListEntityDir.get().get(0));
                        }else {
                            LOGGER.error(parentDir.toAbsolutePath()
                                    .toString()+" has not only single result");
                        }
                    }
                }else {
                    directoryDto.setDirectoryName(dir.toString());
                    directoryDto.setFileTextPathAndSha256(dir);
                    directoryDto.setDirectoryLevel(writeAccessUtilities.getDirectoryLevel(dir));
                    directoryDto.setDirectoryDriveCode(driveCode);
                    eDir = directoryDto.getEntity();
                }

                eDir = dirService.persistMFODirectory(eDir);

                if(SingleSystemProperty.SEARCH_DIR_STACK.size()>0) {
                    SearchPathModel lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.peek();
                    while (!isSearchPathMatching(dir, lastPathModel.getPath())) {
                        Optional<EMFODirectory> opSearchDir = dirService.getMFODirectoryBySearchPathModelDrive(
                                    lastPathModel, driveCode);
                        if (opSearchDir.isPresent()) {
                            EMFODirectory searchDir = opSearchDir.get();
                            searchDir.setDirectorySearchStatus(1);
                            dirService.persistMFODirectory(searchDir);
                        }
                        LOGGER.info("Poped from the search stack : " + SingleSystemProperty.SEARCH_DIR_STACK.pop());
                        if(SingleSystemProperty.SEARCH_DIR_STACK.size()>0){
                            lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.peek();
                        }else {
                            break;
                        }
                    }
                }
                SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dir,zipFileLevel,null,null,null));

                UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eDir.getId());
                LOGGER.info("Added Directory " + dir.toAbsolutePath()
                        .toString() + " with UUID : " + uuid.toString());
//                System.out.println("Added Directory " + dir.toAbsolutePath()
//                        .toString() + " with UUID : " + uuid.toString());
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            } catch (InvalidKeyException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            } finally {
                dirService.close();
            }
        }
    }

    public void addDirectory(Path dir, Integer zipFileLevel, EMFOFile zipFile, Path zipParent, Integer driveCode){
        LOGGER.info("addDirectory2 dir : "+dir.toAbsolutePath().toString()+", zipFileLevel"+zipFileLevel+", zipFile : "+zipFile.getFileFullPath()+", zipParent"+zipParent.toAbsolutePath().toString()+", driveCode : "+driveCode);
        if(dir != null && dir.toFile().isDirectory()){
            IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            MoraFileWriteAccessUtilities writeAccessUtilities = new MoraFileWriteAccessUtilities();
            DirectoryDto directoryDto = new DirectoryDto();

            String zipExtractionDestination = SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION+"_"+zipFileLevel;

//            String dbDirString = dir.toAbsolutePath().toString().replace(zipExtractionDestination, zipParent.toAbsolutePath().toString());
//            String dbDirString = dir.toAbsolutePath().toString().replace(zipExtractionDestination, getGeneratedZipParent(zipParent,zipFile.getFileName()));
            String dbDirString = dir.toAbsolutePath().toString().replace(zipExtractionDestination, zipFile.getFileFullPath());
            Path dbDir = Paths.get(dbDirString);

            try {
                directoryDto.setDirectoryFullPath(dbDir.toAbsolutePath().toString());
                EMFODirectory eDir = new EMFODirectory();

                if(!dbDir.toString().equals(dbDir.getRoot().toString())){
                    directoryDto.setDirectoryName(dbDir.getFileName().toString());
                    directoryDto.setDirectoryIsZip(zipFileLevel);
                    directoryDto.setFileTextPathAndSha256(dbDir);
                    directoryDto.setDirectoryLevel(writeAccessUtilities.getDirectoryLevel(dir)+1);
                    directoryDto.setDirectoryDriveCode(driveCode);
                    eDir = directoryDto.getEntity();

                    Path parentDir = dbDir.getParent();
                    if(parentDir != null && parentDir.toFile().isDirectory()){
                        LOGGER.info("addDirectory2 parentDir : "+parentDir.toAbsolutePath().toString()+", zipFileLevel"+zipFileLevel+", zipFile : "+zipFile.getFileFullPath()+", driveCode : "+driveCode);
                        Optional<EMFODirectory> opEntityDir = dirService.getMFODirectoryByDirectoryFullPathAndZipLevelZipFileDrive(parentDir.toAbsolutePath().toString(), zipFileLevel,zipFile,driveCode);

                        if(opEntityDir.isPresent()){
                            eDir.setDirectoryParent(opEntityDir.get());
                        }
                    }
                }else {
                    directoryDto.setDirectoryName(dbDir.toString());
                    directoryDto.setFileTextPathAndSha256(dir);
                    directoryDto.setDirectoryLevel(writeAccessUtilities.getDirectoryLevel(dir));
                    directoryDto.setDirectoryDriveCode(driveCode);
                    eDir = directoryDto.getEntity();
                }
                eDir.setFileZip(zipFile);
                eDir = dirService.persistMFODirectory(eDir);

                if(SingleSystemProperty.SEARCH_DIR_STACK.size()>0) {
                    SearchPathModel lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.peek();
                    while (!isSearchPathMatching(dbDir, lastPathModel.getPath())) {
                        Optional<EMFODirectory> opSearchDir = dirService.getMFODirectoryBySearchPathModelDrive(lastPathModel, driveCode);
                        if (opSearchDir.isPresent()) {
                            EMFODirectory searchDir = opSearchDir.get();
                            searchDir.setDirectorySearchStatus(1);
                            dirService.persistMFODirectory(searchDir);
                        }
                        LOGGER.info("Poped from the search stack : " + SingleSystemProperty.SEARCH_DIR_STACK.pop());
                        if(SingleSystemProperty.SEARCH_DIR_STACK.size()>0){
                            lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.peek();
                        }else {
                            break;
                        }
                    }
                }
                SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dbDir,zipFileLevel,Paths.get(zipFile.getFileFullPath()),zipFile.getFileIsZip(),zipFile.getId()));

                MFOZipDirectoryFileController zipDirectoryFileController = new MFOZipDirectoryFileController();
                zipDirectoryFileController.addZipDirectory(zipFile, eDir);

                UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eDir.getId());
                LOGGER.info("Added Directory " + dbDir.toAbsolutePath()
                        .toString() + " with UUID : " + uuid.toString());
//                System.out.println("Added Directory " + dbDir.toAbsolutePath()
//                        .toString() + " with UUID : " + uuid.toString());
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            } catch (InvalidKeyException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            } finally {
                dirService.close();
            }
        }
    }

    private boolean isSearchPathMatching(Path path, Path searchPath){
        if(searchPath.toString().equals(searchPath.getRoot().toString())){
            return path.toAbsolutePath().toString().equals(searchPath)|| path.toAbsolutePath().toString().contains(searchPath.toAbsolutePath().toString());
        }else {
            return path.toAbsolutePath().toString().equals(searchPath)|| path.toAbsolutePath().toString().contains(searchPath.toAbsolutePath().toString()+path.getFileSystem().getSeparator());
        }

    }

    private String getGeneratedZipParent(Path zipParent, String zipFileName){
        String generatedZipParent = zipParent.toAbsolutePath().toString()+zipParent.getFileSystem().getSeparator()+removeExtension(zipFileName);
//        String generatedZipParent = zipParent.toAbsolutePath().toString()+zipParent.getFileSystem().getSeparator()+zipFileName;
        return generatedZipParent;
    }

    private String removeExtension(final String filename) {
        final int index = FilenameUtils.indexOfExtension(filename); //used the String.lastIndexOf() method
        if (index == -1) {
            return filename;
        } else {
            return filename.substring(0, index);
        }
    }
}
