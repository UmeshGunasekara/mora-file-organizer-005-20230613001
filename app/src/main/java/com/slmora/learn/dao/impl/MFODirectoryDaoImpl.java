/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 3:37 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.dao.IMFODirectoryDao;
import com.slmora.learn.jpa.entity.EMFODirectory;
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
public class MFODirectoryDaoImpl extends GenericDaoImpl<byte[], EMFODirectory> implements IMFODirectoryDao
{
    final static Logger LOGGER = LogManager.getLogger(MFODirectoryDaoImpl.class);

    @Override
    public Optional<byte[]> addMFODirectory(EMFODirectory directory)
    {
        return add(directory);
//        return persistMFODirectory(directory);
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryById(byte[] id)
    {
        return getById(id);
    }

    @Override
    public void deleteMFODirectory(EMFODirectory directory)
    {
        delete(directory);
    }

    @Override
    public List<EMFODirectory> getAllMFODirectories()
    {
        return getAll();
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryByCode(Integer code)
    {
////        Transaction transaction = null;
//        try{
//            Session session = getSession();
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<EMFODirectory> criteriaQuery = criteriaBuilder.createQuery(EMFODirectory.class);
//            Root<EMFODirectory> root = criteriaQuery.from(EMFODirectory.class);
//            criteriaQuery.select(root);
//
//            Predicate predicate = criteriaBuilder.like(root.get("directoryCode"), code.intValue()+"");
//            criteriaQuery.where(predicate);
//
//            TypedQuery<EMFODirectory> typedQuery = session.createQuery(criteriaQuery);
//            EMFODirectory dir = typedQuery.getSingleResult();
////            transaction=getSession().beginTransaction();
////            T t= (T) session.get(daoType, key);
////            transaction.commit();
//            return Optional.of(dir);
//        } catch (Throwable throwable) {
////            if (transaction != null) {
////                transaction.rollback();
////            }
//            LOGGER.error(ExceptionUtils.getStackTrace(throwable));
//            throwable.printStackTrace();
//        }
//
//        return Optional.empty();

        return getByCode(code);
    }

    @Override
    public Optional<byte[]> persistMFODirectory(EMFODirectory directory)
    {
//        Transaction transaction = null;
//        try{
//            Session session = getSession();
//            transaction=session.beginTransaction();
//            session.persist(directory);
//            transaction.commit();
////            return Optional.of(directory.getDirectoryId());
//            return Optional.of(directory.getId());
//        } catch (Throwable throwable) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            LOGGER.error(ExceptionUtils.getStackTrace(throwable));
//            throwable.printStackTrace();
//        }
//
//        return Optional.empty();
        return persist(directory);
    }
}
