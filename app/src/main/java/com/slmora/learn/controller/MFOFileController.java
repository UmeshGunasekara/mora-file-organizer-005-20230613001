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
    public void addFile(Path file, Integer zipFileLevel, EMFOFile zipFile, Path zipParent){
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        IMFOFileCategoryService fileCategoryService = new MFOFileCategoryServiceImpl(new MFOFileCategoryDaoImpl());
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

        IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());

        String dbFileString = file.toAbsolutePath().toString().replace(SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION, zipParent.toAbsolutePath().toString());
        Path dbFile = Paths.get(dbFileString);

        try {
            FileDto fileDto = new FileDto(file, dbFile, zipFileLevel);

            EMFOFile eFile = fileDto.getEntity();
            Path parentDir = dbFile.getParent();
            if (parentDir != null) {
                Optional<EMFODirectory> opEntityDir = dirService.getMFODirectoryByDirectoryFullPathAndZipLevel(parentDir.toAbsolutePath()
                        .toString(), zipFileLevel);

                if (opEntityDir.isPresent()) {
                    eFile.setDirectory(opEntityDir.get());
                }
            }
            eFile.setFileZipParent(zipFile);
            eFile.setFileCategory(fileCategoryService.getMFOFileCategoryByFileFormatName(fileDto.getFileExtension().toLowerCase())
                    .get());

            eFile = fileService.persistMFOFile(eFile);

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
                addZipFile(Paths.get(fileDto.getFileFullPath()), eFile, ++zipFileLevel);
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

    public void addFile(Path file, Integer zipFileLevel){
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        IMFOFileCategoryService fileCategoryService = new MFOFileCategoryServiceImpl(new MFOFileCategoryDaoImpl());
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

        IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());


        try {
            FileDto fileDto = new FileDto(file);
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
                Optional<EMFODirectory> opEntityDir = dirService.getMFODirectoryByDirectoryFullPathAndZipLevel(parentDir.toAbsolutePath()
                        .toString(), zipFileLevel);

                if (opEntityDir.isPresent()) {
                    eFile.setDirectory(opEntityDir.get());
                }
            }

            eFile.setFileCategory(fileCategoryService.getMFOFileCategoryByFileFormatName(fileDto.getFileExtension().toLowerCase())
                    .get());

            eFile = fileService.persistMFOFile(eFile);

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
                addZipFile(Paths.get(fileDto.getFileFullPath()), eFile, ++zipFileLevel);
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

    public void addZipFile(Path file, EMFOFile eFile, Integer zipFileLevel) throws IOException
    {
        MoraFileWriteAccessUtilities fileWriteAccessUtilities = new MoraFileWriteAccessUtilities();
        MoraFileZipUtilities fileZipUtilities = new MoraFileZipUtilities();

        Path zipParent = file.getParent();

        Optional<Path> opExtractedPath = fileZipUtilities.unzipAndGetPath(file, SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(opExtractedPath.isPresent()){
            Path extractedPath = opExtractedPath.get();
            MoraFileOrganizerWalkingController walkingController = new MoraFileOrganizerWalkingController();
            walkingController.sourcePathWalk(extractedPath, zipFileLevel, zipParent, eFile);
            if(SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION.equals(extractedPath.toAbsolutePath().toString())){
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



}
