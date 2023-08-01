/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:40 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
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
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFOSystemPropertyDaoImpl.class);
    private MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

    @Override
    public Optional<byte[]> addMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Adding system property with UUID {}", (null!=systemProperty)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(systemProperty.getId()):null);
        return add(systemProperty);
    }

    @Override
    public Optional<EMFOSystemProperty> getMFOSystemPropertyById(byte[] id)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve system property with UUID {}", (null!=id)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(id):null);
        return getById(id);
    }

    @Override
    public Optional<EMFOSystemProperty> getMFOSystemPropertyByUUID(UUID uuidKey)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve system property with UUID {}", (null!=uuidKey)?uuidKey:null);
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Delete system property with UUID {}", (null!=systemProperty)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(systemProperty.getId()):null);
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
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve system property with Code {}", code);
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding system property with UUID {}", (null!=systemProperty)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(systemProperty.getId()):null);
        return persistReturnId(systemProperty);
    }

    @Override
    public EMFOSystemProperty persistMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding system property with UUID {}", (null!=systemProperty)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(systemProperty.getId()):null);
        return persist(systemProperty);
    }
}
