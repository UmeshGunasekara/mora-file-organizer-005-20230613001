/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:32 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.IMFOFileCategoryDao;
import com.slmora.learn.jpa.entity.EMFOFileCategory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFOFileCategoryDaoImpl.class);
    private MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

    @Override
    public Optional<byte[]> addMFOFileCategory(EMFOFileCategory fileCategory)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Adding file category with UUID {}", (null!=fileCategory)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(fileCategory.getId()):null);
        return add(fileCategory);
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryById(byte[] id)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file category with UUID {}", (null!=id)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(id):null);
        return getById(id);
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryByUUID(UUID uuidKey)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file category with UUID {}", (null!=uuidKey)?uuidKey:null);
        return getByUUID(uuidKey);
    }

    @Override
    public void deleteMFOFileCategory(EMFOFileCategory fileCategory)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Delete file category with UUID {}", (null!=fileCategory)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(fileCategory.getId()):null);
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
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file category with Code {}", code);
        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFileCategory(EMFOFileCategory fileCategory)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding file category with UUID {}", (null!=fileCategory)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(fileCategory.getId()):null);
        return persistReturnId(fileCategory);
    }

    @Override
    public EMFOFileCategory persistMFOFileCategory(EMFOFileCategory fileCategory)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding file category with UUID {}", (null!=fileCategory)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(fileCategory.getId()):null);
        return persist(fileCategory);
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryByFileFormatName(String fileFormatName)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file category with file format name {}", fileFormatName);
        //https://www.digitalocean.com/community/tutorials/hibernate-query-language-hql-example-tutorial
        //https://thorben-janssen.com/hibernate-tip-left-join-fetch-join-criteriaquery/
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFOFileCategory> criteriaQuery = criteriaBuilder.createQuery(EMFOFileCategory.class);
            Root<EMFOFileCategory> root = criteriaQuery.from(EMFOFileCategory.class);
            Join<Object, Object> fileFormat = root.join("fileFormats");
            criteriaQuery.select(root);

//            ParameterExpression<String> pTitle = criteriaBuilder.parameter(String.class);
            Predicate predicate = criteriaBuilder.equal(fileFormat.get("fileFormatName"), fileFormatName);
            criteriaQuery.where(predicate);

            TypedQuery<EMFOFileCategory> typedQuery = session.createQuery(criteriaQuery);
            EMFOFileCategory fileCategory = typedQuery.getSingleResult();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieved file category with UUID {}", (null!=fileCategory)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(fileCategory.getId()):null);
            return Optional.of(fileCategory);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001", "No file category find with file format name {}", fileFormatName);
            LOGGER.error(Thread.currentThread().getStackTrace(), ex);
            return Optional.empty();
        }catch (Throwable throwable) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error(Thread.currentThread().getStackTrace(), throwable);
            return Optional.empty();
        }finally {
            if(transaction != null && transaction.isActive()){
                transaction.commit();
            }
        }
    }
}
