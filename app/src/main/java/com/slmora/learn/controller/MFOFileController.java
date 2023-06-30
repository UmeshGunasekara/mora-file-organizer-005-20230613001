/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/22/2023 11:36 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
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
import com.slmora.learn.util.EMFileCategory;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
public class MFOFileController {
    final static Logger LOGGER = LogManager.getLogger(MFOFileController.class);
    public void addReadableFile(Path file){
        if(Files.exists(file)&&Files.isRegularFile(file)&&Files.isReadable(file)){
//            if(Files.isReadable(file)) {
                IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
                IMFOFileCategoryService fileCategoryService = new MFOFileCategoryServiceImpl(new MFOFileCategoryDaoImpl());
                MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

                IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());
                FileDto fileDto = new FileDto();

                try {
                    //https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html
                    BasicFileAttributes basicAttr = Files.readAttributes(file, BasicFileAttributes.class);
                    fileDto.setFileName(file.getFileName().toString());
                    fileDto.setFileFullPath(file.toAbsolutePath().toString());
                    Long fileSize = basicAttr.size();
                    fileDto.setFileSizeInBytes(fileSize.doubleValue());
                    fileDto.setFileCreatedDateTime(LocalDateTime.ofInstant(basicAttr.creationTime().toInstant(),
                            ZoneId.systemDefault()));
                    fileDto.setFileLastModifiedDateTime(LocalDateTime.ofInstant(basicAttr.lastModifiedTime()
                            .toInstant(), ZoneId.systemDefault()));
                    fileDto.setFileLastAccessDateTime(LocalDateTime.ofInstant(basicAttr.lastAccessTime().toInstant(),
                            ZoneId.systemDefault()));

                    DosFileAttributes dosAttr = Files.readAttributes(file, DosFileAttributes.class);
                    fileDto.setFileIsReadOnly(dosAttr.isReadOnly() ? 1 : 0);
                    fileDto.setFileIsHidden(dosAttr.isHidden() ? 1 : 0);
                    fileDto.setFileIsArchive(dosAttr.isArchive() ? 1 : 0);
                    fileDto.setFileIsSystem(dosAttr.isSystem() ? 1 : 0);

                    fileDto.setFileExtension(FilenameUtils.getExtension(fileDto.getFileName()));

                    EMFOFile eFile = fileDto.getEntity();
                    Path parentDir = file.getParent();
                    if (parentDir != null && parentDir.toFile().isDirectory()) {
                        Optional<EMFODirectory> opEntityDir = dirService.getMFODirectoryByDirectoryFullPath(parentDir.toAbsolutePath()
                                .toString());

                        if (opEntityDir.isPresent()) {
                            eFile.setDirectory(opEntityDir.get());
                        }
                    }

                    eFile.setFileCategory(fileCategoryService.getMFOFileCategoryByFileFormatName(fileDto.getFileExtension())
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


                } catch (IOException e) {
                    LOGGER.error(ExceptionUtils.getStackTrace(e));
                } catch (NoSuchAlgorithmException e) {
                    LOGGER.error(ExceptionUtils.getStackTrace(e));
                } catch (InvalidKeyException e) {
                    LOGGER.error(ExceptionUtils.getStackTrace(e));
                } finally {
                    dirService.close();
                }

            }else {
                LOGGER.error(file.toAbsolutePath().toString()+" Not readable");
            }

//        }
    }

}
