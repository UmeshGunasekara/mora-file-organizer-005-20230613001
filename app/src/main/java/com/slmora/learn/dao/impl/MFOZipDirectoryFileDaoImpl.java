/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/30/2023 5:24 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
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
    final static Logger LOGGER = LogManager.getLogger(MFOZipDirectoryFileDaoImpl.class);

    @Override
    public Optional<byte[]> addMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        return add(zipDirectoryFile);
    }

    @Override
    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileById(byte[] id)
    {
        return getById(id);
    }

    @Override
    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileByUUID(UUID uuidKey)
    {
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
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
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        return persistReturnId(zipDirectoryFile);
    }

    @Override
    public EMFOZipDirectoryFile persistMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        return persist(zipDirectoryFile);
    }
}
