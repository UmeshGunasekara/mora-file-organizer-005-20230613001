/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 10:33 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
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
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFOVideoFileDataDaoImpl.class);
    private MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

    @Override
    public Optional<byte[]> addMFOVideoFileData(EMFOVideoFileData file)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Adding video file data with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
        return add(file);
    }

    @Override
    public Optional<EMFOVideoFileData> getMFOVideoFileDataById(byte[] id)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve video file data with UUID {}", (null!=id)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(id):null);
        return getById(id);
    }

    @Override
    public Optional<EMFOVideoFileData> getMFOVideoFileDataByUUID(UUID uuidKey)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve video file data with UUID {}", (null!=uuidKey)?uuidKey:null);
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOVideoFileData(EMFOVideoFileData file)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Delete video file data with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
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
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve video file data with Code {}", code);
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOVideoFileData(EMFOVideoFileData file)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding video file data with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
        return persistReturnId(file);
    }

    @Override
    public EMFOVideoFileData persistMFOVideoFileData(EMFOVideoFileData file)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding video file data with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
        return persist(file);
    }
}
