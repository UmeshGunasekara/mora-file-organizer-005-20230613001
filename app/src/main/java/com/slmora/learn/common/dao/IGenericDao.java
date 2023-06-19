/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 1:54 PM
 */
package com.slmora.learn.common.dao;

import com.slmora.learn.jpa.entity.common.BaseEntity;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * This Interface created for
 * <ul>
 *     <li>....</li>
 * </ul>
 *
 * @since 1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          6/14/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public interface IGenericDao<K, T extends BaseEntity>
{
    public Optional<K> add(T entity);

    public Optional<byte[]> persist(T entity);

    public void saveOrUpdate(T entity);

    public void delete(T entity);

    public Optional<T> getById(K key);

    public Optional<T> getByCode(Integer code);

    public List<T> getAll();

    public void close();

    public Session getSession();
}