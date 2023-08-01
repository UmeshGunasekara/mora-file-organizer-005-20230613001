/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/30/2023 5:28 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.dao.IMFOZipDirectoryFileDao;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOZipDirectoryFile;
import com.slmora.learn.service.IMFOZipDirectoryFileService;
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
public class MFOZipDirectoryFileServiceImpl extends GenericServiceImpl<byte[], EMFOZipDirectoryFile> implements
        IMFOZipDirectoryFileService
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFOZipDirectoryFileServiceImpl.class);

    private IMFOZipDirectoryFileDao zipDirectoryFileDao;

    public MFOZipDirectoryFileServiceImpl() {}

    public MFOZipDirectoryFileServiceImpl(IGenericDao<byte[], EMFOZipDirectoryFile> zipDirectoryFileGenericDao) {
        super(zipDirectoryFileGenericDao);
        zipDirectoryFileDao = (IMFOZipDirectoryFileDao) zipDirectoryFileGenericDao;
    }

    @Override
    public Optional<byte[]> addMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        return zipDirectoryFileDao.addMFOZipDirectoryFile(zipDirectoryFile);
    }

    @Override
    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileById(byte[] id)
    {
        return zipDirectoryFileDao.getMFOZipDirectoryFileById(id);
    }

    @Override
    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileByUUID(UUID uuidKey)
    {
        return zipDirectoryFileDao.getMFOZipDirectoryFileByUUID(uuidKey);
    }

    @Override
    public void deleteMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        zipDirectoryFileDao.deleteMFOZipDirectoryFile(zipDirectoryFile);
    }

    @Override
    public List<EMFOZipDirectoryFile> getAllMFOZipDirectoryFiles()
    {
        return zipDirectoryFileDao.getAllMFOZipDirectoryFiles();
    }

    @Override
    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileByCode(Integer code)
    {
        return zipDirectoryFileDao.getMFOZipDirectoryFileByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        return zipDirectoryFileDao.persistReturnIdMFOZipDirectoryFile(zipDirectoryFile);
    }

    @Override
    public EMFOZipDirectoryFile persistMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile)
    {
        return zipDirectoryFileDao.persistMFOZipDirectoryFile(zipDirectoryFile);
    }
}
