/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 1:54 PM
 */
package com.slmora.learn.common.dao.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.ds.hibernate.HibernateHikariAnoUtil;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.jpa.entity.common.BaseEntity;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
public abstract class GenericDaoImpl <K extends Serializable, T extends BaseEntity> implements IGenericDao<K, T>
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(GenericDaoImpl.class);
    private MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

    private final Class<? extends T> daoType;
    private Session session;

    public GenericDaoImpl(Class<? extends T> daoType)
    {
        this.daoType = daoType;
    }

    public GenericDaoImpl(){
        Type t=getClass().getGenericSuperclass();
        ParameterizedType pt=(ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[1];
        try {
//            session= HibernateDBCPAnoUtil.getHibernateSessionFactory().openSession();
            session= HibernateHikariAnoUtil.getHibernateSessionFactory().openSession();
        } catch (Throwable throwable) {
            LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
        }
    }

    protected CriteriaQuery createEntityCriteriaQuery(){
        return session.getCriteriaBuilder().createQuery(daoType);
    }

    protected TypedQuery createTypedQuery() {
        CriteriaQuery criteriaQuery = createEntityCriteriaQuery();
        Root root = criteriaQuery.from(daoType);
        criteriaQuery.select(root);
        TypedQuery typedQuery = session.createQuery(criteriaQuery);
        return typedQuery;
    }

    @Override
    public Optional<K> add(T entity)
    {
//        return (K) getSession().save(entity);
//        Transaction transaction=session.beginTransaction();
//        K k=(K)session.save(entity);
//        transaction.commit();

        Transaction transaction = null;
        if(null != entity) {
            try {
                transaction = session.beginTransaction();
                K k = (K) session.save(entity);
//            session.persist(entity);
                transaction.commit();
                LOGGER.info(Thread.currentThread().getStackTrace(),
                        "Added with UUID {}",
                        uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(entity.getId()));
                return Optional.of(k);
            } catch (Throwable throwable) {
                if (transaction != null) {
                    transaction.rollback();
                }
                LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
            }finally {
                if(transaction != null && transaction.isActive()){
                    transaction.commit();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<byte[]> persistReturnId(T entity)
    {
        Transaction transaction = null;
        if(null != entity) {
            try {
                transaction = session.beginTransaction();
                session.persist(entity);
                session.flush();
                transaction.commit();
                LOGGER.info(Thread.currentThread().getStackTrace(),
                        "Persist with UUID {}",
                        uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(entity.getId()));
                return Optional.of(entity.getId());
            } catch (Throwable throwable) {
                if (transaction != null) {
                    transaction.rollback();
                }
                LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
            }finally {
                if(transaction != null && transaction.isActive()){
                    transaction.commit();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public T persist(T entity)
    {
        Transaction transaction = null;
        if(null != entity) {
            try {
                transaction = session.beginTransaction();
                session.persist(entity);
                session.flush();
                transaction.commit();
                LOGGER.info(Thread.currentThread().getStackTrace(),
                        "Persist with UUID {}",
                        uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(entity.getId()));
            } catch (Throwable throwable) {
                if (transaction != null) {
                    transaction.rollback();
                }
                LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
            }finally {
                if(transaction != null && transaction.isActive()){
                    transaction.commit();
                }
            }
        }
        return entity;
    }

    @Override
    public void saveOrUpdate(T entity)
    {
//        Transaction transaction=session.beginTransaction();
//        session.saveOrUpdate(entity);
//        transaction.commit();

        Transaction transaction = null;
        if(null != entity) {
            try {
                transaction = session.beginTransaction();
                session.saveOrUpdate(entity);
                transaction.commit();
                LOGGER.info(Thread.currentThread().getStackTrace(),
                        "Update with UUID {}",
                        uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(entity.getId()));
            } catch (Throwable throwable) {
                if (transaction != null) {
                    transaction.rollback();
                }
                LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
            }finally {
                if(transaction != null && transaction.isActive()){
                    transaction.commit();
                }
            }
        }
    }

    @Override
    public void delete(T entity)
    {
//        Transaction transaction=session.beginTransaction();
//        session.delete(entity);
//        transaction.commit();

        Transaction transaction = null;
        if(null != entity) {
            try {
                transaction = session.beginTransaction();
//            session.delete(entity);
                session.remove(entity);
                transaction.commit();
                LOGGER.info(Thread.currentThread().getStackTrace(),
                        "Delete with UUID {}",
                        uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(entity.getId()));
            } catch (NoResultException ex) {
                LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001",
                        "No Record find with UUID {}",
                        uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(entity.getId()));
                LOGGER.error(Thread.currentThread().getStackTrace(), ex);
            } catch (Throwable throwable) {
                if (transaction != null) {
                    transaction.rollback();
                }
                LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
            }finally {
                if(transaction != null && transaction.isActive()){
                    transaction.commit();
                }
            }
        }
    }

    @Override
    public Optional<T> getById(K key)
    {
//        Transaction transaction=session.beginTransaction();
//        T t= (T) session.get(daoType, key);
//        transaction.commit();
//        return t;
        Transaction transaction = null;
        if(null != key) {
            try {
                transaction = session.beginTransaction();
                T t = (T) session.get(daoType, key);
                transaction.commit();
                LOGGER.info(Thread.currentThread().getStackTrace(),
                        "Retrieve with UUID {}",
                        (null != t)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(t.getId()):null);
                return Optional.of(t);
            } catch (NoResultException ex) {
                LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001", "No Record find");
                LOGGER.error(Thread.currentThread().getStackTrace(), ex);
            } catch (Throwable throwable) {
                if (transaction != null) {
                    transaction.rollback();
                }
                LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
            }finally {
                if(transaction != null && transaction.isActive()){
                    transaction.commit();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> getByCode(Integer code)
    {
//        Transaction transaction=session.beginTransaction();
//        T t= (T) session.get(daoType, key);
//        transaction.commit();
//        return t;
        Transaction transaction = null;
        if(null != code) {
            try {
                transaction = session.beginTransaction();

                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(daoType);
                Root root = criteriaQuery.from(daoType);
                criteriaQuery.select(root);

                Predicate predicate = criteriaBuilder.like(root.get("code"), code.intValue() + "");
                criteriaQuery.where(predicate);

                TypedQuery typedQuery = session.createQuery(criteriaQuery);
                T t = (T) typedQuery.getSingleResult();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
                transaction.commit();
                LOGGER.info(Thread.currentThread().getStackTrace(),
                        "Retrieve with UUID {}",
                        (null != t) ? uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(t.getId()) : null);
                return Optional.of(t);
            } catch (NoResultException ex) {
                LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001", "No Record find Code {}", code);
                LOGGER.error(Thread.currentThread().getStackTrace(), ex);
            } catch (Throwable throwable) {
                if (transaction != null) {
                    transaction.rollback();
                }
                LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
            }finally {
                if(transaction != null && transaction.isActive()){
                    transaction.commit();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> getByUUID(UUID uuidKey)
    {
//        Transaction transaction=session.beginTransaction();
//        T t= (T) session.get(daoType, key);
//        transaction.commit();
//        return t;
        Transaction transaction = null;
//        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        if(null != uuidKey) {
            try {
                transaction = session.beginTransaction();
                T t = (T) session.get(daoType, uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(uuidKey));
                transaction.commit();
                LOGGER.info(Thread.currentThread().getStackTrace(),
                        "Retrieve with UUID {}",
                        (null != t) ? uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(t.getId()) : null);
                return Optional.of(t);
            } catch (NoResultException ex) {
                LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001", "No Record find UUID {}", uuidKey);
                LOGGER.error(Thread.currentThread().getStackTrace(), ex);
            } catch (Throwable throwable) {
                if (transaction != null) {
                    transaction.rollback();
                }
                LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
            }finally {
                if(transaction != null && transaction.isActive()){
                    transaction.commit();
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<T> getAll()
    {
//        return (List<T>) createEntityCriteria().list();
        return (List<T>) createTypedQuery().getResultList();
    }

    @Override
    public void close()
    {
        if(session!=null){
            LOGGER.info(Thread.currentThread().getStackTrace(), "Session closed");
            session.close();
        }
    }

    @Override
    public Session getSession()
    {
        return session;
    }
}
