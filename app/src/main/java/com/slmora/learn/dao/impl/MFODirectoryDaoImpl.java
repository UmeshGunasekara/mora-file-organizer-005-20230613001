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
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.model.SearchPathModel;
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

import java.util.ArrayList;
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
    public Optional<EMFODirectory> getMFODirectoryByUUID(UUID uuidKey)
    {
        return getByUUID(uuidKey);
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
    public Optional<byte[]> persistReturnIdMFODirectory(EMFODirectory directory)
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
        return persistReturnId(directory);
    }

    @Override
    public EMFODirectory persistMFODirectory(EMFODirectory directory)
    {
        return persist(directory);
    }

    @Override
    public Optional<List<EMFODirectory>> getAllMFODirectoryByDirectoryFullPathSha256AndZipLevel(String directoryFullPathSha256, Integer zipLevel)
    {
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFODirectory> criteriaQuery = criteriaBuilder.createQuery(EMFODirectory.class);
            Root<EMFODirectory> root = criteriaQuery.from(EMFODirectory.class);
            criteriaQuery.select(root);

            Predicate zipPredicate = criteriaBuilder.equal(root.get("directoryIsZip"), zipLevel);
            Predicate sha256Predicate = criteriaBuilder.equal(root.get("directoryFullPathSha256"), directoryFullPathSha256);
            Predicate finalPredicate = criteriaBuilder.and(zipPredicate,sha256Predicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFODirectory> typedQuery = session.createQuery(criteriaQuery);
            List<EMFODirectory> dirList = typedQuery.getResultList();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(dirList);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getAllMFODirectoryByDirectoryFullPathSha256AndZipLevel No record found for the directory full path SHA 256 : "+directoryFullPathSha256+", zipLevel : "+zipLevel);
            return Optional.empty();
        }catch (Throwable throwable) {
            LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            return Optional.empty();
        }finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<List<EMFODirectory>> getAllMFODirectoryByDirectoryFullPathSha256AndZipLevelDrive(String directoryFullPathSha256,
                                                                                                     Integer zipLevel,
                                                                                                     Integer directoryDriveCode)
    {
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFODirectory> criteriaQuery = criteriaBuilder.createQuery(EMFODirectory.class);
            Root<EMFODirectory> root = criteriaQuery.from(EMFODirectory.class);
            criteriaQuery.select(root);

            Predicate zipPredicate = criteriaBuilder.equal(root.get("directoryIsZip"), zipLevel);
            Predicate sha256Predicate = criteriaBuilder.equal(root.get("directoryFullPathSha256"), directoryFullPathSha256);
            Predicate drivePredicate = criteriaBuilder.equal(root.get("directoryDriveCode"), directoryDriveCode);
            Predicate zipSha256Predicate = criteriaBuilder.and(zipPredicate,sha256Predicate);
            Predicate finalPredicate = criteriaBuilder.and(drivePredicate,zipSha256Predicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFODirectory> typedQuery = session.createQuery(criteriaQuery);
            List<EMFODirectory> dirList = typedQuery.getResultList();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(dirList);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getAllMFODirectoryByDirectoryFullPathSha256AndZipLevelDrive No record found for the directory full path SHA 256 : "+directoryFullPathSha256+", zipLevel : "+zipLevel+", directoryDriveCode : "+directoryDriveCode);
            return Optional.empty();
        }catch (Throwable throwable) {
            LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            throwable.printStackTrace();
            return Optional.empty();
        }finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryByDirectoryFullPathSha256AndZipLevelZipFileDrive(String directoryFullPathSha256, Integer zipLevel, EMFOFile zipFile, Integer directoryDriveCode)
    {
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFODirectory> criteriaQuery = criteriaBuilder.createQuery(EMFODirectory.class);
            Root<EMFODirectory> root = criteriaQuery.from(EMFODirectory.class);
            criteriaQuery.select(root);

            Predicate zipPredicate = criteriaBuilder.equal(root.get("directoryIsZip"), zipLevel);
            Predicate sha256Predicate = criteriaBuilder.equal(root.get("directoryFullPathSha256"), directoryFullPathSha256);
            Predicate zipFilePredicate = criteriaBuilder.equal(root.get("fileZip"), zipFile);
            Predicate drivePredicate = criteriaBuilder.equal(root.get("directoryDriveCode"), directoryDriveCode);
            Predicate zipSha256Predicate = criteriaBuilder.and(zipPredicate,sha256Predicate);
            Predicate zipFileDrivePredicate = criteriaBuilder.and(zipFilePredicate,drivePredicate);
            Predicate finalPredicate = criteriaBuilder.and(zipSha256Predicate,zipFileDrivePredicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFODirectory> typedQuery = session.createQuery(criteriaQuery);
            EMFODirectory dir = typedQuery.getSingleResult();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(dir);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getMFODirectoryByDirectoryFullPathSha256AndZipLevelZipFileDrive No record found for the directory full path SHA 256 : "+directoryFullPathSha256+", zipLevel : "+zipLevel+", directoryDriveCode : "+directoryDriveCode);
            return Optional.empty();
        }catch (Throwable throwable) {
            LOGGER.error(ExceptionUtils.getStackTrace(throwable));
            throwable.printStackTrace();
            return Optional.empty();
        }finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryBySearchPathModelDrive(String directoryFullPathSha256, Integer directoryZipLevel, byte[] zipFileId, Integer directoryDriveCode)
    {
        LOGGER.info("directoryFullPathSha256 : "+directoryFullPathSha256+" , directoryZipLevel : "+directoryZipLevel+" , directoryDriveCode : "+directoryDriveCode);
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFODirectory> criteriaQuery = criteriaBuilder.createQuery(EMFODirectory.class);
            Root<EMFODirectory> root = criteriaQuery.from(EMFODirectory.class);
            Join<Object, Object> fileZip = root.join("fileZip");
            criteriaQuery.select(root);

//            ParameterExpression<String> pTitle = criteriaBuilder.parameter(String.class);
            Predicate directoryFullPathSha256Predicate = criteriaBuilder.equal(root.get("directoryFullPathSha256"), directoryFullPathSha256);
            Predicate directoryZipLevelPredicate = criteriaBuilder.equal(root.get("directoryIsZip"), directoryZipLevel);
            Predicate directoryDrivePredicate = criteriaBuilder.equal(root.get("directoryDriveCode"), directoryDriveCode);
//            Predicate zipFileFullPathSha256Predicate = criteriaBuilder.equal(fileZip.get("fileFullPathSha256"), zipFileFullPathSha256);
//            Predicate zipFileZipLevelPredicate = criteriaBuilder.equal(fileZip.get("fileIsZip"), fileZipLevel);
            Predicate zipFileIdPredicate = criteriaBuilder.equal(fileZip.get("id"), zipFileId);
//            Predicate fileDrivePredicate = criteriaBuilder.equal(fileZip.get("fileDriveCode"), directoryDriveCode);
            Predicate directorySha256ZipLevelPredicate = criteriaBuilder.and(directoryFullPathSha256Predicate,directoryZipLevelPredicate);
            Predicate directoryPredicate = criteriaBuilder.and(directorySha256ZipLevelPredicate,directoryDrivePredicate);
//            Predicate fileSha256ZipLevelPredicate = criteriaBuilder.and(zipFileFullPathSha256Predicate,zipFileZipLevelPredicate);
//            Predicate filePredicate = criteriaBuilder.and(fileSha256ZipLevelPredicate,fileDrivePredicate);
//            Predicate filePredicate = criteriaBuilder.and(zipFileIdPredicate,fileDrivePredicate);
//            Predicate finalPredicate = criteriaBuilder.and(directoryPredicate,filePredicate);
            Predicate finalPredicate = criteriaBuilder.and(directoryPredicate,zipFileIdPredicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFODirectory> typedQuery = session.createQuery(criteriaQuery);
            EMFODirectory dir = typedQuery.getSingleResult();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(dir);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getMFODirectoryByDirectoryFullPathSha256AndZipLevelZipFileDrive No record found for the directoryFullPathSha256 : "+directoryFullPathSha256+" , directoryZipLevel : "+directoryZipLevel);
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

    @Override
    public Optional<EMFODirectory> getMFODirectoryBySearchPathModelDrive(String directoryFullPathSha256,
                                                                         Integer directoryZipLevel,
                                                                         String zipFileFullPathSha256,
                                                                         Integer zipFileZipLevel,
                                                                         Integer driveCode)
    {
        LOGGER.info("directoryFullPathSha256 : "+directoryFullPathSha256+" , directoryZipLevel : "+directoryZipLevel+" , zipFileFullPathSha256 : "+zipFileFullPathSha256+" , zipFileZipLevel : "+zipFileZipLevel+" , driveCode : "+driveCode);
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFODirectory> criteriaQuery = criteriaBuilder.createQuery(EMFODirectory.class);
            Root<EMFODirectory> root = criteriaQuery.from(EMFODirectory.class);
            Join<Object, Object> fileZip = root.join("fileZip");
            criteriaQuery.select(root);

//            ParameterExpression<String> pTitle = criteriaBuilder.parameter(String.class);
            Predicate directoryFullPathSha256Predicate = criteriaBuilder.equal(root.get("directoryFullPathSha256"), directoryFullPathSha256);
            Predicate directoryZipLevelPredicate = criteriaBuilder.equal(root.get("directoryIsZip"), directoryZipLevel);
            Predicate directoryDrivePredicate = criteriaBuilder.equal(root.get("directoryDriveCode"), driveCode);

            Predicate zipFileFullPathSha256Predicate = criteriaBuilder.equal(fileZip.get("fileFullPathSha256"), zipFileFullPathSha256);
            Predicate zipFileZipLevelPredicate = criteriaBuilder.equal(fileZip.get("fileIsZip"), zipFileZipLevel);
//            Predicate zipFileIdPredicate = criteriaBuilder.equal(fileZip.get("fileFullPathSha256"), zipFileId);
            Predicate zipFileDrivePredicate = criteriaBuilder.equal(fileZip.get("fileDriveCode"), driveCode);

            Predicate directorySha256ZipLevelPredicate = criteriaBuilder.and(directoryFullPathSha256Predicate,directoryZipLevelPredicate);
            Predicate directoryPredicate = criteriaBuilder.and(directorySha256ZipLevelPredicate,directoryDrivePredicate);

            Predicate zipFileSha256ZipLevelPredicate = criteriaBuilder.and(zipFileFullPathSha256Predicate,zipFileZipLevelPredicate);
            Predicate zipFilePredicate = criteriaBuilder.and(zipFileSha256ZipLevelPredicate,zipFileDrivePredicate);

//            Predicate filePredicate = criteriaBuilder.and(zipFileIdPredicate,fileDrivePredicate);
            Predicate finalPredicate = criteriaBuilder.and(directoryPredicate,zipFilePredicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFODirectory> typedQuery = session.createQuery(criteriaQuery);
            EMFODirectory dir = typedQuery.getSingleResult();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(dir);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getMFODirectoryByDirectoryFullPathSha256AndZipLevelZipFileDrive No record found for the directoryFullPathSha256 : "+directoryFullPathSha256+" , directoryZipLevel : "+directoryZipLevel+" , zipFileFullPathSha256 : "+zipFileFullPathSha256+" , zipFileZipLevel : "+zipFileZipLevel+" , driveCode : "+driveCode);
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
