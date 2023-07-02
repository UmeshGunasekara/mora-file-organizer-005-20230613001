/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/1/2023 4:49 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFOZipDirectoryFileDaoImpl;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOZipDirectoryFile;
import com.slmora.learn.service.IMFOZipDirectoryFileService;
import com.slmora.learn.service.impl.MFOZipDirectoryFileServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
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
 * <br>1.0          7/1/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFOZipDirectoryFileController {
    final static Logger LOGGER = LogManager.getLogger(MFOZipDirectoryFileController.class);

    public void addZipDirectory(EMFOFile zipFile, EMFODirectory zipDir){
        IMFOZipDirectoryFileService zipDirectoryFileService = new MFOZipDirectoryFileServiceImpl(new MFOZipDirectoryFileDaoImpl());

        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

        EMFOZipDirectoryFile eZipDirectoryFile = new EMFOZipDirectoryFile();
        eZipDirectoryFile.setFile(zipFile);
        eZipDirectoryFile.setDirectory(zipDir);

        eZipDirectoryFile = zipDirectoryFileService.persistMFOZipDirectoryFile(eZipDirectoryFile);

        UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eZipDirectoryFile.getId());
        LOGGER.info("For Zip File "+zipFile.getFileFullPath()+" Added Zip Directory " + zipDir.getDirectoryFullPath() + " with UUID : " + uuid.toString());
        System.out.println("For Zip File "+zipFile.getFileFullPath()+" Added Zip Directory " + zipDir.getDirectoryFullPath() + " with UUID : " + uuid.toString());

    }
}
