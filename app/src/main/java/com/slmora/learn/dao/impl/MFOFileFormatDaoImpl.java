/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 1:59 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
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
    final static Logger LOGGER = LogManager.getLogger(MFOFileFormatDaoImpl.class);

    @Override
    public Optional<byte[]> addMFOFileFormat(EMFOFileFormat fileFormat)
    {
        return add(fileFormat);
    }

    @Override
    public Optional<EMFOFileFormat> getMFOFileFormatById(byte[] id)
    {
        return getById(id);
    }

    @Override
    public Optional<EMFOFileFormat> getMFOFileFormatByUUID(UUID uuidKey)
    {
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOFileFormat(EMFOFileFormat fileFormat)
    {
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
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFileFormat(EMFOFileFormat fileFormat)
    {
        return persistReturnId(fileFormat);
    }

    @Override
    public EMFOFileFormat persistMFOFileFormat(EMFOFileFormat fileFormat)
    {
        return persist(fileFormat);
    }
}
