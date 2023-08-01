/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/30/2023 5:24 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.IMFOFileDao;
import com.slmora.learn.dao.IMFOZipDirectoryFileDao;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOZipDirectoryFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
 * <br>1.0          6/30/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFOZipDirectoryFileDaoImpl extends GenericDaoImpl<byte[], EMFOZipDirectoryFile> implements
        IMFOZipDirectoryFileDao
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFOZipDirectoryFileDaoImpl.class);
    private MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

    @Override
    public Optional<byte[]> addMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Adding Zip Directory File data with UUID {}", (null!=zipDirectoryFile)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipDirectoryFile.getId()):null);
        return add(zipDirectoryFile);
    }

    @Override
    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileById(byte[] id)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve Zip Directory File data with UUID {}", (null!=id)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(id):null);
        return getById(id);
    }

    @Override
    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileByUUID(UUID uuidKey)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve Zip Directory File data with UUID {}", (null!=uuidKey)?uuidKey:null);
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Delete Zip Directory File data with UUID {}", (null!=zipDirectoryFile)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipDirectoryFile.getId()):null);
        delete(zipDirectoryFile);
    }

    @Override
    public List<EMFOZipDirectoryFile> getAllMFOZipDirectoryFiles()
    {
        return getAll();
    }

    @Override
    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileByCode(Integer code)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve Zip Directory File data with Code {}", code);
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding Zip Directory File data with UUID {}", (null!=zipDirectoryFile)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipDirectoryFile.getId()):null);
        return persistReturnId(zipDirectoryFile);
    }

    @Override
    public EMFOZipDirectoryFile persistMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding Zip Directory File data with UUID {}", (null!=zipDirectoryFile)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipDirectoryFile.getId()):null);
        return persist(zipDirectoryFile);
    }
}
