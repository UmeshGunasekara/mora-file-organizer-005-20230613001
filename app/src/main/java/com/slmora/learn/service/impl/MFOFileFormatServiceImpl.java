/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 2:05 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.dao.IMFOFileFormatDao;
import com.slmora.learn.jpa.entity.EMFOFileFormat;
import com.slmora.learn.service.IMFOFileFormatService;
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
 * <br>1.0          6/29/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFOFileFormatServiceImpl extends GenericServiceImpl<byte[], EMFOFileFormat> implements
        IMFOFileFormatService
{
    final static Logger LOGGER = LogManager.getLogger(MFOFileFormatServiceImpl.class);

    private IMFOFileFormatDao fileFormatDao;

    public MFOFileFormatServiceImpl() {}

    public MFOFileFormatServiceImpl(IGenericDao<byte[], EMFOFileFormat> fileFormatGenericDao) {
        super(fileFormatGenericDao);
        fileFormatDao = (IMFOFileFormatDao) fileFormatGenericDao;
    }

    @Override
    public Optional<byte[]> addMFOFileFormat(EMFOFileFormat fileFormat)
    {
        return fileFormatDao.addMFOFileFormat(fileFormat);
    }

    @Override
    public Optional<EMFOFileFormat> getMFOFileFormatById(byte[] id)
    {
        return fileFormatDao.getMFOFileFormatById(id);
    }

    @Override
    public Optional<EMFOFileFormat> getMFOFileFormatByUUID(UUID uuidKey)
    {
        return fileFormatDao.getMFOFileFormatByUUID(uuidKey);
    }

    @Override
    public void deleteMFOFileFormat(EMFOFileFormat fileFormat)
    {
        fileFormatDao.deleteMFOFileFormat(fileFormat);
    }

    @Override
    public List<EMFOFileFormat> getAllMFOFileFormats()
    {
        return fileFormatDao.getAllMFOFileFormats();
    }

    @Override
    public Optional<EMFOFileFormat> getMFOFileFormatByCode(Integer code)
    {
        return fileFormatDao.getMFOFileFormatByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFileFormat(EMFOFileFormat fileFormat)
    {
        return fileFormatDao.persistReturnIdMFOFileFormat(fileFormat);
    }

    @Override
    public EMFOFileFormat persistMFOFileFormat(EMFOFileFormat fileFormat)
    {
        return fileFormatDao.persistMFOFileFormat(fileFormat);
    }
}
