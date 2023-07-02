/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/22/2023 11:00 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.dto.DirectoryDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.service.IMFODirectoryService;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;
import com.slmora.learn.system.property.SingleSystemProperty;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
    public void addDirectory(Path dir, Integer zipFileLevel){
        if(dir != null && dir.toFile().isDirectory()){
            IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            DirectoryDto directoryDto = new DirectoryDto();

            try {
                directoryDto.setDirectoryFullPath(dir.toAbsolutePath().toString());
                EMFODirectory eDir = new EMFODirectory();

                if(!dir.toString().equals(dir.getRoot().toString())){
                    directoryDto.setDirectoryName(dir.getFileName().toString());
                    directoryDto.setDirectoryIsZip(zipFileLevel);
                    eDir = directoryDto.getEntity();

                    Path parentDir = dir.getParent();
                    if(parentDir != null && parentDir.toFile().isDirectory()){
                        Optional<EMFODirectory> opEntityDir = dirService.getMFODirectoryByDirectoryFullPathAndZipLevel(parentDir.toAbsolutePath().toString(), zipFileLevel);

                        if(opEntityDir.isPresent()){
                            eDir.setDirectoryParent(opEntityDir.get());
                        }
                    }
                }else {
                    directoryDto.setDirectoryName(dir.toString());
                    eDir = directoryDto.getEntity();
                }
                eDir = dirService.persistMFODirectory(eDir);

                UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eDir.getId());
                LOGGER.info("Added Directory " + dir.toAbsolutePath()
                        .toString() + " with UUID : " + uuid.toString());
                System.out.println("Added Directory " + dir.toAbsolutePath()
                        .toString() + " with UUID : " + uuid.toString());
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            } catch (InvalidKeyException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            } finally {
                dirService.close();
            }
        }
    }

    public void addDirectory(Path dir, Integer zipFileLevel, EMFOFile zipFile, Path zipParent){
        if(dir != null && dir.toFile().isDirectory()){
            IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            DirectoryDto directoryDto = new DirectoryDto();

            String dbDirString = dir.toAbsolutePath().toString().replace(SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION, zipParent.toAbsolutePath().toString());
            Path dbDir = Paths.get(dbDirString);

            try {
                directoryDto.setDirectoryFullPath(dbDir.toAbsolutePath().toString());
                EMFODirectory eDir = new EMFODirectory();

                if(!dbDir.toString().equals(dbDir.getRoot().toString())){
                    directoryDto.setDirectoryName(dbDir.getFileName().toString());
                    directoryDto.setDirectoryIsZip(zipFileLevel);
                    eDir = directoryDto.getEntity();

                    Path parentDir = dbDir.getParent();
                    if(parentDir != null && parentDir.toFile().isDirectory()){
                        Optional<EMFODirectory> opEntityDir = dirService.getMFODirectoryByDirectoryFullPathAndZipLevel(parentDir.toAbsolutePath().toString(), zipFileLevel);

                        if(opEntityDir.isPresent()){
                            eDir.setDirectoryParent(opEntityDir.get());
                        }
                    }
                }else {
                    directoryDto.setDirectoryName(dbDir.toString());
                    eDir = directoryDto.getEntity();
                }
                eDir = dirService.persistMFODirectory(eDir);

                MFOZipDirectoryFileController zipDirectoryFileController = new MFOZipDirectoryFileController();
                zipDirectoryFileController.addZipDirectory(zipFile, eDir);

                UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eDir.getId());
                LOGGER.info("Added Directory " + dbDir.toAbsolutePath()
                        .toString() + " with UUID : " + uuid.toString());
                System.out.println("Added Directory " + dbDir.toAbsolutePath()
                        .toString() + " with UUID : " + uuid.toString());
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            } catch (InvalidKeyException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            } finally {
                dirService.close();
            }
        }
    }
}
