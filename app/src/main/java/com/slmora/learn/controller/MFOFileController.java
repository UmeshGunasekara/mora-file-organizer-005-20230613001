/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/22/2023 11:36 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.common.file.util.MoraFileWriteAccessUtilities;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.common.zip.util.MoraFileZipUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.dao.impl.MFOFileCategoryDaoImpl;
import com.slmora.learn.dao.impl.MFOFileDaoImpl;
import com.slmora.learn.dto.FileDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.model.SearchPathModel;
import com.slmora.learn.service.IMFODirectoryService;
import com.slmora.learn.service.IMFOFileCategoryService;
import com.slmora.learn.service.IMFOFileService;
import com.slmora.learn.service.impl.MFOFileCategoryServiceImpl;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;
import com.slmora.learn.service.impl.MFOFileServiceImpl;
import com.slmora.learn.system.property.SingleSystemProperty;
import com.slmora.learn.util.EMFileCategory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
public class MFOFileController {
    final static Logger LOGGER = LogManager.getLogger(MFOFileController.class);
    public void addFile(Path file, Integer zipFileLevel, EMFOFile zipFile, Path zipParent, Integer driveCode){
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        IMFOFileCategoryService fileCategoryService = new MFOFileCategoryServiceImpl(new MFOFileCategoryDaoImpl());
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

        IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());

        String zipExtractionDestination = SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION+"_"+zipFileLevel;

        String dbFileString = file.toAbsolutePath().toString().replace(zipExtractionDestination, zipParent.toAbsolutePath().toString());
        Path dbFile = Paths.get(dbFileString);

        try {
            FileDto fileDto = new FileDto(file, dbFile, zipFileLevel);
            fileDto.setFileDriveCode(driveCode);
            EMFOFile eFile = fileDto.getEntity();
            Path parentDir = dbFile.getParent();
            if (parentDir != null) {
                Optional<EMFODirectory> opEntityDir = dirService.getMFODirectoryByDirectoryFullPathAndZipLevelZipFileDrive(parentDir.toAbsolutePath()
                        .toString(), zipFileLevel, zipFile, driveCode);

                if (opEntityDir.isPresent()) {
                    eFile.setDirectory(opEntityDir.get());
                }
            }
            eFile.setFileZipParent(zipFile);
            eFile.setFileCategory(fileCategoryService.getMFOFileCategoryByFileFormatName(fileDto.getFileExtension().toLowerCase())
                    .get());

            eFile = fileService.persistMFOFile(eFile);

            if(SingleSystemProperty.SEARCH_DIR_STACK.size()>0) {
                SearchPathModel lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.peek();
                while (!isSearchPathMatching(dbFile, lastPathModel.getPath())) {
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

            UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eFile.getId());
            LOGGER.info("Added File " + dbFile.toAbsolutePath()
                    .toString() + " with UUID : " + uuid.toString());
            System.out.println("Added File " + dbFile.toAbsolutePath()
                    .toString() + " with UUID : " + uuid.toString());

            if(eFile.getFileCategory().getCode().equals(EMFileCategory.FILE_CAT_VIDEO.getFileCategoryCode())){
                MFOVideoFileDataController videoFileDataController = new MFOVideoFileDataController();
                videoFileDataController.addVideoFileDate(eFile, file);
            }

            if(eFile.getFileCategory().getCode().equals(EMFileCategory.FILE_CAT_AUDIO.getFileCategoryCode())){
                MFOAudioFileDataController audioFileDataController = new MFOAudioFileDataController();
                audioFileDataController.addAudioFileDate(eFile, file);
            }

            if(!fileDto.getFileExtension().isBlank()&&fileDto.getFileExtension().toLowerCase().endsWith("zip")){
                eFile.setFileSearchStatus(0);
                addZipFile(file, eFile, ++zipFileLevel,parentDir, driveCode);
                eFile.setFileSearchStatus(1);
            }


        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        } catch (InvalidKeyException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        } finally {
            dirService.close();
        }

    }

    public void addFile(Path file, Integer zipFileLevel, Integer driveCode){
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        IMFOFileCategoryService fileCategoryService = new MFOFileCategoryServiceImpl(new MFOFileCategoryDaoImpl());
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

        IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());


        try {
            FileDto fileDto = new FileDto(file);
            fileDto.setFileDriveCode(driveCode);
//            fileDto.setFileName(file.getFileName().toString());
//            fileDto.setFileFullPath(file.toAbsolutePath().toString());
//
//            setBasicFileAttributes(file, fileDto);
//            setDosFileAttributes(file, fileDto);
//            setFileAccessAttributes(file,fileDto);
//            fileDto.setFileExtension(FilenameUtils.getExtension(fileDto.getFileName()));


            EMFOFile eFile = fileDto.getEntity();
            Path parentDir = file.getParent();
            if (parentDir != null && parentDir.toFile().isDirectory()) {
                Optional<List<EMFODirectory>> opListEntityDir = dirService.getAllMFODirectoryByDirectoryFullPathAndZipLevelDrive(parentDir.toAbsolutePath()
                        .toString(), zipFileLevel,driveCode);

                if (opListEntityDir.isPresent()&&opListEntityDir.get().size()==1) {
                    eFile.setDirectory(opListEntityDir.get().get(0));
                }else {
                    LOGGER.error(parentDir.toAbsolutePath()
                            .toString()+" has not only single result");
                }
            }

            eFile.setFileCategory(fileCategoryService.getMFOFileCategoryByFileFormatName(fileDto.getFileExtension().toLowerCase())
                    .get());

            eFile = fileService.persistMFOFile(eFile);

            if(SingleSystemProperty.SEARCH_DIR_STACK.size()>0) {
                SearchPathModel lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.peek();
                while (!isSearchPathMatching(file, lastPathModel.getPath())) {
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

            UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eFile.getId());
            LOGGER.info("Added File " + file.toAbsolutePath()
                    .toString() + " with UUID : " + uuid.toString());
            System.out.println("Added File " + file.toAbsolutePath()
                    .toString() + " with UUID : " + uuid.toString());

            if(eFile.getFileCategory().getCode().equals(EMFileCategory.FILE_CAT_VIDEO.getFileCategoryCode())){
                MFOVideoFileDataController videoFileDataController = new MFOVideoFileDataController();
                videoFileDataController.addVideoFileDate(eFile);
            }

            if(eFile.getFileCategory().getCode().equals(EMFileCategory.FILE_CAT_AUDIO.getFileCategoryCode())){
                MFOAudioFileDataController audioFileDataController = new MFOAudioFileDataController();
                audioFileDataController.addAudioFileDate(eFile);
            }

            if(!fileDto.getFileExtension().isBlank()&&fileDto.getFileExtension().toLowerCase().endsWith("zip")){
                eFile.setFileSearchStatus(0);
//                fileService.persistMFOFile(eFile);
                addZipFile(Paths.get(fileDto.getFileFullPath()), eFile, ++zipFileLevel, parentDir, driveCode);
                eFile.setFileSearchStatus(1);
//                fileService.persistMFOFile(eFile);
            }


        } catch (IOException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        } catch (InvalidKeyException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        } finally {
            dirService.close();
        }
    }

    public void addZipFile(Path file, EMFOFile eFile, Integer zipFileLevel, Path zipParent, Integer driveCode) throws IOException
    {
        MoraFileWriteAccessUtilities fileWriteAccessUtilities = new MoraFileWriteAccessUtilities();
        MoraFileZipUtilities fileZipUtilities = new MoraFileZipUtilities();

        Path parent = zipParent!=null?zipParent:file.getParent();

        String zipExtractionDestination = SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION+"_"+zipFileLevel;

        Optional<Path> opExtractedPath = fileZipUtilities.unzipAndGetPath(file, zipExtractionDestination);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(opExtractedPath.isPresent()){
            Path extractedPath = opExtractedPath.get();
            MoraFileOrganizerWalkingController walkingController = new MoraFileOrganizerWalkingController();
            LOGGER.info("File : "+file.toAbsolutePath().toString()+" ,Extracted in "+extractedPath.toAbsolutePath().toString());

            walkingController.sourcePathWalk(extractedPath, zipFileLevel, parent, eFile, driveCode);
            if(zipExtractionDestination.equals(extractedPath.toAbsolutePath().toString())){
                fileWriteAccessUtilities.removeAll(extractedPath,false);
            }else {
                fileWriteAccessUtilities.removeAll(extractedPath,true);
            }
        }
    }

    private void setDosFileAttributes(Path file, FileDto fileDto) throws IOException
    {
        DosFileAttributes dosAttr = Files.readAttributes(file, DosFileAttributes.class);
        fileDto.setFileIsReadOnly(dosAttr.isReadOnly() ? 1 : 0);
        fileDto.setFileIsHidden(dosAttr.isHidden() ? 1 : 0);
        fileDto.setFileIsArchive(dosAttr.isArchive() ? 1 : 0);
        fileDto.setFileIsSystem(dosAttr.isSystem() ? 1 : 0);
    }

    private void setBasicFileAttributes(Path file, FileDto fileDto) throws IOException
    {
        //https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html
        BasicFileAttributes basicAttr = Files.readAttributes(file, BasicFileAttributes.class);
        Long fileSize = basicAttr.size();
        fileDto.setFileSizeInBytes(fileSize.doubleValue());
        fileDto.setFileCreatedDateTime(LocalDateTime.ofInstant(basicAttr.creationTime().toInstant(),
                ZoneId.systemDefault()));
        fileDto.setFileLastModifiedDateTime(LocalDateTime.ofInstant(basicAttr.lastModifiedTime()
                .toInstant(), ZoneId.systemDefault()));
        fileDto.setFileLastAccessDateTime(LocalDateTime.ofInstant(basicAttr.lastAccessTime().toInstant(),
                ZoneId.systemDefault()));
    }

    private void setFileAccessAttributes(Path file, FileDto fileDto) throws IOException
    {
        fileDto.setFileIsReadable(Files.isReadable(file) ? 1 : 0);
        fileDto.setFileIsWritable(Files.isWritable(file) ? 1 : 0);
        fileDto.setFileIsExecutable(Files.isExecutable(file) ? 1 : 0);
    }

    private boolean isSearchPathMatching(Path path, Path searchPath){
        if(searchPath.toString().equals(searchPath.getRoot().toString())){
            return path.toAbsolutePath().toString().equals(searchPath)|| path.toAbsolutePath().toString().contains(searchPath.toAbsolutePath().toString());
        }else {
            return path.toAbsolutePath().toString().equals(searchPath)|| path.toAbsolutePath().toString().contains(searchPath.toAbsolutePath().toString()+path.getFileSystem().getSeparator());
        }

    }

}
