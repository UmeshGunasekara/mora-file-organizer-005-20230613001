/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 1:59 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.IMFOFileFormatDao;
import com.slmora.learn.jpa.entity.EMFOFileFormat;
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
public class MFOFileFormatDaoImpl extends GenericDaoImpl<byte[], EMFOFileFormat> implements IMFOFileFormatDao
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFOFileFormatDaoImpl.class);
    private MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

    @Override
    public Optional<byte[]> addMFOFileFormat(EMFOFileFormat fileFormat)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Adding file format with UUID {}", (null!=fileFormat)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(fileFormat.getId()):null);
        return add(fileFormat);
    }

    @Override
    public Optional<EMFOFileFormat> getMFOFileFormatById(byte[] id)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file format with UUID {}", (null!=id)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(id):null);
        return getById(id);
    }

    @Override
    public Optional<EMFOFileFormat> getMFOFileFormatByUUID(UUID uuidKey)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file format with UUID {}", (null!=uuidKey)?uuidKey:null);
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOFileFormat(EMFOFileFormat fileFormat)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Delete file format with UUID {}", (null!=fileFormat)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(fileFormat.getId()):null);
        delete(fileFormat);
    }

    @Override
    public List<EMFOFileFormat> getAllMFOFileFormats()
    {
        return getAll();
    }

    @Override
    public Optional<EMFOFileFormat> getMFOFileFormatByCode(Integer code)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file format with Code {}", code);
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFileFormat(EMFOFileFormat fileFormat)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding file format with UUID {}", (null!=fileFormat)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(fileFormat.getId()):null);
        return persistReturnId(fileFormat);
    }

    @Override
    public EMFOFileFormat persistMFOFileFormat(EMFOFileFormat fileFormat)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding file format with UUID {}", (null!=fileFormat)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(fileFormat.getId()):null);
        return persist(fileFormat);
    }
}
