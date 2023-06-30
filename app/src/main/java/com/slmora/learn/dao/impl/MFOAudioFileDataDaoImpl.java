/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 10:35 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.dao.IMFOAudioFileDataDao;
import com.slmora.learn.jpa.entity.EMFOAudioFileData;
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
public class MFOAudioFileDataDaoImpl extends GenericDaoImpl<byte[], EMFOAudioFileData> implements IMFOAudioFileDataDao
{
    final static Logger LOGGER = LogManager.getLogger(MFOAudioFileDataDaoImpl.class);

    @Override
    public Optional<byte[]> addMFOAudioFileData(EMFOAudioFileData file)
    {
        return add(file);
    }

    @Override
    public Optional<EMFOAudioFileData> getMFOAudioFileDataById(byte[] id)
    {
        return getById(id);
    }

    @Override
    public Optional<EMFOAudioFileData> getMFOAudioFileDataByUUID(UUID uuidKey)
    {
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOAudioFileData(EMFOAudioFileData file)
    {
        delete(file);
    }

    @Override
    public List<EMFOAudioFileData> getAllMFOAudioFileData()
    {
        return getAll();
    }

    @Override
    public Optional<EMFOAudioFileData> getMFOAudioFileDataByCode(Integer code)
    {
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOAudioFileData(EMFOAudioFileData file)
    {
        return persistReturnId(file);
    }

    @Override
    public EMFOAudioFileData persistMFOAudioFileData(EMFOAudioFileData file)
    {
        return persist(file);
    }
}
