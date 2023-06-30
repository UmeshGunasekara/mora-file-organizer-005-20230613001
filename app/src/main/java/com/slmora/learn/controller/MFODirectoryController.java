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
import com.slmora.learn.service.IMFODirectoryService;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
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
    public void addDirectory(Path dir){
        if(dir != null && dir.toFile().isDirectory()){
            IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            DirectoryDto directoryDto = new DirectoryDto();

            try {
                directoryDto.setDirectoryFullPath(dir.toAbsolutePath().toString());
                EMFODirectory eDir = new EMFODirectory();

                if(!dir.toString().equals(dir.getRoot().toString())){
                    directoryDto.setDirectoryName(dir.getFileName().toString());
                    eDir = directoryDto.getEntity();

                    Path parentDir = dir.getParent();
                    if(parentDir != null && parentDir.toFile().isDirectory()){
                        Optional<EMFODirectory> opEntityDir = dirService.getMFODirectoryByDirectoryFullPath(parentDir.toAbsolutePath().toString());

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
}
