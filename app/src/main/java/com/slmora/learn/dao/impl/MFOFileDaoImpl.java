/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 8:44 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.dao.IMFOFileDao;
import com.slmora.learn.jpa.entity.EMFOFile;
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
public class MFOFileDaoImpl extends GenericDaoImpl<byte[], EMFOFile> implements IMFOFileDao
{
    final static Logger LOGGER = LogManager.getLogger(MFOFileDaoImpl.class);

    @Override
    public Optional<byte[]> addMFOFile(EMFOFile file)
    {
        return add(file);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileById(byte[] id)
    {
        return getById(id);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileByUUID(UUID uuidKey)
    {
        return getByUUID(uuidKey);
//        return Optional.empty();
    }

    @Override
    public void deleteMFOFile(EMFOFile file)
    {
        delete(file);
    }

    @Override
    public List<EMFOFile> getAllMFOFiles()
    {
        return getAll();
//        return null;
    }

    @Override
    public Optional<EMFOFile> getMFOFileByCode(Integer code)
    {
        return getByCode(code);
//        return Optional.empty();
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFile(EMFOFile file)
    {
        return persistReturnId(file);
//        return Optional.empty();
    }

    @Override
    public EMFOFile persistMFOFile(EMFOFile file)
    {
        return persist(file);
//        return Optional.empty();
    }
}
