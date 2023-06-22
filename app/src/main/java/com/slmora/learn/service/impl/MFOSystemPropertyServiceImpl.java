/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:56 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.dao.IMFOFileCategoryDao;
import com.slmora.learn.dao.IMFOSystemPropertyDao;
import com.slmora.learn.jpa.entity.EMFOFileCategory;
import com.slmora.learn.jpa.entity.EMFOSystemProperty;
import com.slmora.learn.service.IMFOSystemPropertyService;
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
public class MFOSystemPropertyServiceImpl extends GenericServiceImpl<byte[], EMFOSystemProperty> implements
        IMFOSystemPropertyService
{
    final static Logger LOGGER = LogManager.getLogger(MFOSystemPropertyServiceImpl.class);

    private IMFOSystemPropertyDao systemPropertyDao;

    public MFOSystemPropertyServiceImpl() {}

    public MFOSystemPropertyServiceImpl(IGenericDao<byte[], EMFOSystemProperty> systemPropertyGenericDao) {
        super(systemPropertyGenericDao);
        systemPropertyDao = (IMFOSystemPropertyDao) systemPropertyGenericDao;
    }

    @Override
    public Optional<byte[]> addMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        return systemPropertyDao.addMFOSystemProperty(systemProperty);
    }

    @Override
    public Optional<EMFOSystemProperty> getMFOSystemPropertyById(byte[] id)
    {
        return systemPropertyDao.getMFOSystemPropertyById(id);
    }

    @Override
    public Optional<EMFOSystemProperty> getMFOSystemPropertyByUUID(UUID uuidKey)
    {
        return systemPropertyDao.getMFOSystemPropertyByUUID(uuidKey);
    }

    @Override
    public void deleteMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        systemPropertyDao.deleteMFOSystemProperty(systemProperty);
    }

    @Override
    public List<EMFOSystemProperty> getAllMFOSystemProperties()
    {
        return systemPropertyDao.getAllMFOSystemProperties();
    }

    @Override
    public Optional<EMFOSystemProperty> getMFOSystemPropertyByCode(Integer code)
    {
        return systemPropertyDao.getMFOSystemPropertyByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        return systemPropertyDao.persistReturnIdMFOSystemProperty(systemProperty);
    }

    @Override
    public EMFOSystemProperty persistMFOSystemProperty(EMFOSystemProperty systemProperty)
    {
        return systemPropertyDao.persistMFOSystemProperty(systemProperty);
    }
}
