/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:32 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.dao.IMFODirectoryDao;
import com.slmora.learn.dao.IMFOFileCategoryDao;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFileCategory;
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
 * <br>1.0          6/20/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFOFileCategoryDaoImpl extends GenericDaoImpl<byte[], EMFOFileCategory> implements IMFOFileCategoryDao
{
    final static Logger LOGGER = LogManager.getLogger(MFOFileCategoryDaoImpl.class);

    @Override
    public Optional<byte[]> addMFOFileCategory(EMFOFileCategory fileCategory)
    {
        return add(fileCategory);
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryById(byte[] id)
    {
        return getById(id);
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryByUUID(UUID uuidKey)
    {
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOFileCategory(EMFOFileCategory fileCategory)
    {
        delete(fileCategory);
    }

    @Override
    public List<EMFOFileCategory> getAllMFOFileCategories()
    {
        return getAll();
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryByCode(Integer code)
    {
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFileCategory(EMFOFileCategory fileCategory)
    {
        return persistReturnId(fileCategory);
    }

    @Override
    public EMFOFileCategory persistMFOFileCategory(EMFOFileCategory fileCategory)
    {
        return persist(fileCategory);
    }
}
