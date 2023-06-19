/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 3:04 PM
 */
package com.slmora.learn.common.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.service.IGenericService;
import com.slmora.learn.jpa.entity.common.BaseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

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
    final static Logger LOGGER = LogManager.getLogger(GenericServiceImpl.class);
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
    public Optional<byte[]> persist(T entity)
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
}
