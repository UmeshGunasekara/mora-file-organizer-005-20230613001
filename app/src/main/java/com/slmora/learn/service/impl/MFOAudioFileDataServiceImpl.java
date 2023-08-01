/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 10:48 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.dao.IMFOAudioFileDataDao;
import com.slmora.learn.jpa.entity.EMFOAudioFileData;
import com.slmora.learn.service.IMFOAudioFileDataService;
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
public class MFOAudioFileDataServiceImpl extends GenericServiceImpl<byte[], EMFOAudioFileData> implements
        IMFOAudioFileDataService
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFOFileServiceImpl.class);

    private IMFOAudioFileDataDao audioFileDataDao;

    public MFOAudioFileDataServiceImpl() {}

    public MFOAudioFileDataServiceImpl(IGenericDao<byte[], EMFOAudioFileData> audioFileDataGenericDao) {
        super(audioFileDataGenericDao);
        audioFileDataDao = (IMFOAudioFileDataDao) audioFileDataGenericDao;
    }

    @Override
    public Optional<byte[]> addMFOAudioFileData(EMFOAudioFileData file)
    {
        return audioFileDataDao.addMFOAudioFileData(file);
    }

    @Override
    public Optional<EMFOAudioFileData> getMFOAudioFileDataById(byte[] id)
    {
        return audioFileDataDao.getMFOAudioFileDataById(id);
    }

    @Override
    public Optional<EMFOAudioFileData> getMFOAudioFileDataByUUID(UUID uuidKey)
    {
        return audioFileDataDao.getMFOAudioFileDataByUUID(uuidKey);
    }

    @Override
    public void deleteMFOAudioFileData(EMFOAudioFileData file)
    {
        audioFileDataDao.deleteMFOAudioFileData(file);
    }

    @Override
    public List<EMFOAudioFileData> getAllMFOAudioFileData()
    {
        return audioFileDataDao.getAllMFOAudioFileData();
    }

    @Override
    public Optional<EMFOAudioFileData> getMFOAudioFileDataByCode(Integer code)
    {
        return audioFileDataDao.getMFOAudioFileDataByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOAudioFileData(EMFOAudioFileData file)
    {
        return audioFileDataDao.persistReturnIdMFOAudioFileData(file);
    }

    @Override
    public EMFOAudioFileData persistMFOAudioFileData(EMFOAudioFileData file)
    {
        return audioFileDataDao.persistMFOAudioFileData(file);
    }
}
