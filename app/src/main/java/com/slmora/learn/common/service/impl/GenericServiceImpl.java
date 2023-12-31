/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 3:04 PM
 */
package com.slmora.learn.common.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.service.IGenericService;
import com.slmora.learn.jpa.entity.common.BaseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

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
 * <br>1.0          6/14/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public abstract class GenericServiceImpl <K, T extends BaseEntity> implements IGenericService<K, T>
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(GenericServiceImpl.class);
    private IGenericDao<K, T> genericDao;

    public GenericServiceImpl(IGenericDao<K, T> genericDao) {
        this.genericDao = genericDao;
    }

    public GenericServiceImpl() {
    }

    @Override
    public Optional<K> add(T entity) {
        return genericDao.add(entity);
    }

    @Override
    public Optional<byte[]> persistReturnId(T entity)
    {
        return genericDao.persistReturnId(entity);
    }

    @Override
    public T persist(T entity)
    {
        return genericDao.persist(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        genericDao.saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        genericDao.delete(entity);
    }

    @Override
    public Optional<T> getById(K key) {
        return genericDao.getById(key);
    }

    @Override
    public Optional<T> getByUUID(UUID uuidKey) {
        return genericDao.getByUUID(uuidKey);
    }

    @Override
    public Optional<T> getByCode(Integer code) {
        return genericDao.getByCode(code);
    }

    @Override
    public List<T> getAll() {
        return genericDao.getAll();
    }

    @Override
    public void close()
    {
        genericDao.close();
    }

    @Override
    public Session getSession()
    {
        return genericDao.getSession();
    }
}
