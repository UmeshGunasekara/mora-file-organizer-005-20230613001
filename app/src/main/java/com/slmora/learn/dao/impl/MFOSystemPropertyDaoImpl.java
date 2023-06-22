/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:40 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.dao.IMFOSystemPropertyDao;
import com.slmora.learn.jpa.entity.EMFOSystemProperty;
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
public class MFOSystemPropertyDaoImpl extends GenericDaoImpl<byte[], EMFOSystemProperty> implements
        IMFOSystemPropertyDao
{
    final static Logger LOGGER = LogManager.getLogger(MFOSystemPropertyDaoImpl.class);

    @Override
    public Optional<byte[]> addMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        return add(systemProperty);
    }

    @Override
    public Optional<EMFOSystemProperty> getMFOSystemPropertyById(byte[] id)
    {
        return getById(id);
    }

    @Override
    public Optional<EMFOSystemProperty> getMFOSystemPropertyByUUID(UUID uuidKey)
    {
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        delete(systemProperty);
    }

    @Override
    public List<EMFOSystemProperty> getAllMFOSystemProperties()
    {
        return getAll();
    }

    @Override
    public Optional<EMFOSystemProperty> getMFOSystemPropertyByCode(Integer code)
    {
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        return persistReturnId(systemProperty);
    }

    @Override
    public EMFOSystemProperty persistMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        return persist(systemProperty);
    }
}
