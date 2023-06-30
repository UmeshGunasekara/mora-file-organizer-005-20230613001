/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 10:33 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.dao.IMFOVideoFileDataDao;
import com.slmora.learn.jpa.entity.EMFOVideoFileData;
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
public class MFOVideoFileDataDaoImpl extends GenericDaoImpl<byte[], EMFOVideoFileData> implements IMFOVideoFileDataDao
{
    final static Logger LOGGER = LogManager.getLogger(MFOVideoFileDataDaoImpl.class);

    @Override
    public Optional<byte[]> addMFOVideoFileData(EMFOVideoFileData file)
    {
        return add(file);
    }

    @Override
    public Optional<EMFOVideoFileData> getMFOVideoFileDataById(byte[] id)
    {
        return getById(id);
    }

    @Override
    public Optional<EMFOVideoFileData> getMFOVideoFileDataByUUID(UUID uuidKey)
    {
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOVideoFileData(EMFOVideoFileData file)
    {
        delete(file);
    }

    @Override
    public List<EMFOVideoFileData> getAllMFOVideoFileData()
    {
        return getAll();
    }

    @Override
    public Optional<EMFOVideoFileData> getMFOVideoFileDataByCode(Integer code)
    {
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOVideoFileData(EMFOVideoFileData file)
    {
        return persistReturnId(file);
    }

    @Override
    public EMFOVideoFileData persistMFOVideoFileData(EMFOVideoFileData file)
    {
        return persist(file);
    }
}
