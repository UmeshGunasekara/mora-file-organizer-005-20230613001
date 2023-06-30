/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 10:42 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.dao.IMFOFileDao;
import com.slmora.learn.dao.IMFOVideoFileDataDao;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOVideoFileData;
import com.slmora.learn.service.IMFOVideoFileDataService;
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
public class MFOVideoFileDataServiceImpl extends GenericServiceImpl<byte[], EMFOVideoFileData> implements
        IMFOVideoFileDataService
{
    final static Logger LOGGER = LogManager.getLogger(MFOVideoFileDataServiceImpl.class);

    private IMFOVideoFileDataDao videoFileDataDao;

    public MFOVideoFileDataServiceImpl() {}

    public MFOVideoFileDataServiceImpl(IGenericDao<byte[], EMFOVideoFileData> videoFileDataGenericDao) {
        super(videoFileDataGenericDao);
        videoFileDataDao = (IMFOVideoFileDataDao) videoFileDataGenericDao;
    }

    @Override
    public Optional<byte[]> addMFOVideoFileData(EMFOVideoFileData file)
    {
        return videoFileDataDao.addMFOVideoFileData(file);
    }

    @Override
    public Optional<EMFOVideoFileData> getMFOVideoFileDataById(byte[] id)
    {
        return videoFileDataDao.getMFOVideoFileDataById(id);
    }

    @Override
    public Optional<EMFOVideoFileData> getMFOVideoFileDataByUUID(UUID uuidKey)
    {
        return videoFileDataDao.getMFOVideoFileDataByUUID(uuidKey);
    }

    @Override
    public void deleteMFOVideoFileData(EMFOVideoFileData file)
    {
        videoFileDataDao.deleteMFOVideoFileData(file);
    }

    @Override
    public List<EMFOVideoFileData> getAllMFOVideoFileData()
    {
        return videoFileDataDao.getAllMFOVideoFileData();
    }

    @Override
    public Optional<EMFOVideoFileData> getMFOVideoFileDataByCode(Integer code)
    {
        return videoFileDataDao.getMFOVideoFileDataByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOVideoFileData(EMFOVideoFileData file)
    {
        return videoFileDataDao.persistReturnIdMFOVideoFileData(file);
    }

    @Override
    public EMFOVideoFileData persistMFOVideoFileData(EMFOVideoFileData file)
    {
        return videoFileDataDao.persistMFOVideoFileData(file);
    }
}
