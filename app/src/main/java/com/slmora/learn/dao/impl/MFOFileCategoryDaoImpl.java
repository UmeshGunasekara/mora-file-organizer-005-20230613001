/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:32 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
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

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryByFileFormatName(String fileFormatName)
    {
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
            EMFOFileCategory file = typedQuery.getSingleResult();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(file);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("No record found for the file category compatible to given file format name :"+fileFormatName);
            return Optional.empty();
        }catch (Throwable throwable) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            throwable.printStackTrace();
            return Optional.empty();
        }finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
