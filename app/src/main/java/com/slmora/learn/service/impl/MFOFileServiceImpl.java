/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 8:51 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.dao.IMFODirectoryDao;
import com.slmora.learn.dao.IMFOFileDao;
import com.slmora.learn.dto.FileDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.service.IMFOFileService;
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
 * <br>1.0          6/19/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFOFileServiceImpl extends GenericServiceImpl<byte[], EMFOFile> implements IMFOFileService
{
    final static Logger LOGGER = LogManager.getLogger(MFOFileServiceImpl.class);

    private IMFOFileDao fileDao;

    public MFOFileServiceImpl() {}

    public MFOFileServiceImpl(IGenericDao<byte[], EMFOFile> fileGenericDao) {
        super(fileGenericDao);
        fileDao = (IMFOFileDao) fileGenericDao;
    }

    @Override
    public Optional<byte[]> addMFOFile(EMFOFile file)
    {
        return fileDao.addMFOFile(file);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileById(byte[] id)
    {
        return fileDao.getMFOFileById(id);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileByUUID(UUID uuidKey)
    {
        return fileDao.getMFOFileByUUID(uuidKey);
//        return Optional.empty();
    }

    @Override
    public void deleteMFOFile(EMFOFile file)
    {
        fileDao.deleteMFOFile(file);
    }

    @Override
    public List<EMFOFile> getAllMFOFiles()
    {
        return fileDao.getAllMFOFiles();
//        return null;
    }

    @Override
    public Optional<EMFOFile> getMFOFileByCode(Integer code)
    {
        return fileDao.getMFOFileByCode(code);
//        return Optional.empty();
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFile(EMFOFile file)
    {
        return fileDao.persistReturnIdMFOFile(file);
//        return Optional.empty();
    }

    @Override
    public EMFOFile persistMFOFile(EMFOFile file)
    {
        return fileDao.persistMFOFile(file);
//        return Optional.empty();
    }

    @Override
    public Optional<byte[]> persistMFOFile(FileDto fileDto)
    {
        return fileDao.persistReturnIdMFOFile(fileDto.getEntity());
    }
}
