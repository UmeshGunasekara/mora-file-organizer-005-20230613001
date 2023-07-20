/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 8:44 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.dao.IMFOFileDao;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
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
 * <br>1.0          6/19/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFOFileDaoImpl extends GenericDaoImpl<byte[], EMFOFile> implements IMFOFileDao
{
    final static Logger LOGGER = LogManager.getLogger(MFOFileDaoImpl.class);

    @Override
    public Optional<byte[]> addMFOFile(EMFOFile file)
    {
        return add(file);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileById(byte[] id)
    {
        return getById(id);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileByUUID(UUID uuidKey)
    {
        return getByUUID(uuidKey);
//        return Optional.empty();
    }

    @Override
    public void deleteMFOFile(EMFOFile file)
    {
        delete(file);
    }

    @Override
    public List<EMFOFile> getAllMFOFiles()
    {
        return getAll();
//        return null;
    }

    @Override
    public Optional<EMFOFile> getMFOFileByCode(Integer code)
    {
        return getByCode(code);
//        return Optional.empty();
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFile(EMFOFile file)
    {
        return persistReturnId(file);
//        return Optional.empty();
    }

    @Override
    public EMFOFile persistMFOFile(EMFOFile file)
    {
        return persist(file);
//        return Optional.empty();
    }

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256(String fileFullPathSha256)
    {
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFOFile> criteriaQuery = criteriaBuilder.createQuery(EMFOFile.class);
            Root<EMFOFile> root = criteriaQuery.from(EMFOFile.class);
            criteriaQuery.select(root);

            Predicate predicate = criteriaBuilder.like(root.get("fileFullPathSha256"), fileFullPathSha256);
            criteriaQuery.where(predicate);

            TypedQuery<EMFOFile> typedQuery = session.createQuery(criteriaQuery);
            List<EMFOFile> ListFile = typedQuery.getResultList();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(ListFile);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("No record found for the file full path SHA 256 :"+fileFullPathSha256);
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
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256AndZipLevel(String fileFullPathSha256,
                                                                                 Integer zipLevel)
    {
        LOGGER.info("getAllMFOFileByFileFullPathSha256AndZipLevel with fileFullPathSha256 : "+fileFullPathSha256+", zipLevel : "+zipLevel);
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFOFile> criteriaQuery = criteriaBuilder.createQuery(EMFOFile.class);
            Root<EMFOFile> root = criteriaQuery.from(EMFOFile.class);
            criteriaQuery.select(root);

            Predicate zipPredicate = criteriaBuilder.equal(root.get("fileIsZip"), zipLevel);
            Predicate sha256Predicate = criteriaBuilder.equal(root.get("fileFullPathSha256"), fileFullPathSha256);
            Predicate finalPredicate = criteriaBuilder.and(zipPredicate,sha256Predicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFOFile> typedQuery = session.createQuery(criteriaQuery);
            List<EMFOFile> fileList = typedQuery.getResultList();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(fileList);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getAllMFOFileByFileFullPathSha256AndZipLevel No record found for the file full path SHA 256 :"+fileFullPathSha256+", zipLevel : "+zipLevel);
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
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256AndZipLevelDrive(String fileFullPathSha256,
                                                                                      Integer zipLevel,
                                                                                      Integer fileDriveCode)
    {
        LOGGER.info("getAllMFOFileByFileFullPathSha256AndZipLevelDrive with fileFullPathSha256 : "+fileFullPathSha256+", zipLevel : "+zipLevel+", fileDriveCode : "+fileDriveCode);
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFOFile> criteriaQuery = criteriaBuilder.createQuery(EMFOFile.class);
            Root<EMFOFile> root = criteriaQuery.from(EMFOFile.class);
            criteriaQuery.select(root);

            Predicate zipPredicate = criteriaBuilder.equal(root.get("fileIsZip"), zipLevel);
            Predicate sha256Predicate = criteriaBuilder.equal(root.get("fileFullPathSha256"), fileFullPathSha256);
            Predicate drivePredicate = criteriaBuilder.equal(root.get("fileDriveCode"), fileDriveCode);
            Predicate zipSha256Predicate = criteriaBuilder.and(zipPredicate,sha256Predicate);
            Predicate finalPredicate = criteriaBuilder.and(drivePredicate,zipSha256Predicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFOFile> typedQuery = session.createQuery(criteriaQuery);
            List<EMFOFile> fileList = typedQuery.getResultList();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(fileList);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getAllMFOFileByFileFullPathSha256AndZipLevelDrive No record found for the file full path SHA 256 :"+fileFullPathSha256+", zipLevel : "+zipLevel+", fileDriveCode : "+fileDriveCode);
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
    public Optional<EMFOFile> getMFOFileByFileFullPathSha256AndZipLevelZipFileDrive(String fileFullPathSha256,
                                                                                    Integer zipLevel,
                                                                                    EMFOFile zipFile,
                                                                                    Integer fileDriveCode)
    {
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFOFile> criteriaQuery = criteriaBuilder.createQuery(EMFOFile.class);
            Root<EMFOFile> root = criteriaQuery.from(EMFOFile.class);
            criteriaQuery.select(root);

            Predicate zipPredicate = criteriaBuilder.equal(root.get("fileIsZip"), zipLevel);
            Predicate sha256Predicate = criteriaBuilder.equal(root.get("fileFullPathSha256"), fileFullPathSha256);
            Predicate zipFilePredicate = criteriaBuilder.equal(root.get("fileZipParent"), zipFile);
            Predicate drivePredicate = criteriaBuilder.equal(root.get("fileDriveCode"), fileDriveCode);
            Predicate zipSha256Predicate = criteriaBuilder.and(zipPredicate,sha256Predicate);
            Predicate zipFileDrivePredicate = criteriaBuilder.and(zipFilePredicate,drivePredicate);
            Predicate finalPredicate = criteriaBuilder.and(zipSha256Predicate,zipFileDrivePredicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFOFile> typedQuery = session.createQuery(criteriaQuery);
            EMFOFile file = typedQuery.getSingleResult();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(file);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getMFOFileByFileFullPathSha256AndZipLevelZipFileDrive No record found for the file full path SHA 256 :"+fileFullPathSha256+", zipLevel : "+zipLevel+", fileDriveCode : "+fileDriveCode);
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
    public Optional<EMFOFile> getMFOFileBySearchPathModelDrive(String fileFullPathSha256,
                                                               Integer zipLevel, byte[] zipFileId,
                                                               Integer fileDriveCode)
    {
        LOGGER.info("fileFullPathSha256 : "+fileFullPathSha256+" , zipLevel : "+zipLevel+" , zipFileFullPathSha256 : "+" , fileDriveCode : "+fileDriveCode);
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFOFile> criteriaQuery = criteriaBuilder.createQuery(EMFOFile.class);
            Root<EMFOFile> root = criteriaQuery.from(EMFOFile.class);
            Join<Object, Object> fileZip = root.join("fileZipParent");
            criteriaQuery.select(root);

//            ParameterExpression<String> pTitle = criteriaBuilder.parameter(String.class);
            Predicate fileFullPathSha256Predicate = criteriaBuilder.equal(root.get("fileFullPathSha256"), fileFullPathSha256);
            Predicate fileZipLevelPredicate = criteriaBuilder.equal(root.get("fileIsZip"), zipLevel);
            Predicate fileDrivePredicate = criteriaBuilder.equal(root.get("fileDriveCode"), fileDriveCode);
//            Predicate zipFileFullPathSha256Predicate = criteriaBuilder.equal(fileZip.get("fileFullPathSha256"), zipFileFullPathSha256);
//            Predicate zipFileZipLevelPredicate = criteriaBuilder.equal(fileZip.get("fileIsZip"), fileZipLevel);
            Predicate zipFileidPredicate = criteriaBuilder.equal(fileZip.get("id"), zipFileId);
            Predicate zipFileDrivePredicate = criteriaBuilder.equal(fileZip.get("fileDriveCode"), fileDriveCode);
            Predicate fileSha256ZipLevelPredicate = criteriaBuilder.and(fileFullPathSha256Predicate,fileZipLevelPredicate);
            Predicate filePredicate = criteriaBuilder.and(fileSha256ZipLevelPredicate,fileDrivePredicate);
//            Predicate zipFileSha256ZipLevelPredicate = criteriaBuilder.and(zipFileFullPathSha256Predicate,zipFileZipLevelPredicate);
//            Predicate zipFilePredicate = criteriaBuilder.and(zipFileSha256ZipLevelPredicate,zipFileDrivePredicate);
            Predicate zipFilePredicate = criteriaBuilder.and(zipFileidPredicate,zipFileDrivePredicate);
            Predicate finalPredicate = criteriaBuilder.and(filePredicate,zipFilePredicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFOFile> typedQuery = session.createQuery(criteriaQuery);
            EMFOFile file = typedQuery.getSingleResult();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(file);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getMFOFileBySearchPathModelDrive No record found for the file full path SHA 256 :"+fileFullPathSha256+" , zipLevel : "+zipLevel+" , fileDriveCode : "+fileDriveCode);
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
    public Optional<EMFOFile> getMFOFileBySearchPathModelDrive(String fileFullPathSha256,
                                                               Integer fileZipLevel,
                                                               String zipFileFullPathSha256,
                                                               Integer zipFileZipLevel,
                                                               Integer driveCode)
    {
        LOGGER.info("fileFullPathSha256 : "+fileFullPathSha256+" , zipLevel : "+fileZipLevel+" , zipFileFullPathSha256 : "+zipFileFullPathSha256+" , zipFileZipLevel : "+zipFileZipLevel+" , driveCode : "+driveCode);
        Transaction transaction = null;
        try{
            Session session = getSession();
            transaction=session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<EMFOFile> criteriaQuery = criteriaBuilder.createQuery(EMFOFile.class);
            Root<EMFOFile> root = criteriaQuery.from(EMFOFile.class);
            Join<Object, Object> fileZip = root.join("fileZipParent");
            criteriaQuery.select(root);

//            ParameterExpression<String> pTitle = criteriaBuilder.parameter(String.class);
            Predicate fileFullPathSha256Predicate = criteriaBuilder.equal(root.get("fileFullPathSha256"), fileFullPathSha256);
            Predicate fileZipLevelPredicate = criteriaBuilder.equal(root.get("fileIsZip"), fileZipLevel);
            Predicate fileDrivePredicate = criteriaBuilder.equal(root.get("fileDriveCode"), driveCode);

            Predicate zipFileFullPathSha256Predicate = criteriaBuilder.equal(fileZip.get("fileFullPathSha256"), zipFileFullPathSha256);
            Predicate zipFileZipLevelPredicate = criteriaBuilder.equal(fileZip.get("fileIsZip"), fileZipLevel);
//            Predicate zipFileidPredicate = criteriaBuilder.equal(fileZip.get("id"), zipFileId);
            Predicate zipFileDrivePredicate = criteriaBuilder.equal(fileZip.get("fileDriveCode"), driveCode);

            Predicate fileSha256ZipLevelPredicate = criteriaBuilder.and(fileFullPathSha256Predicate,fileZipLevelPredicate);
            Predicate filePredicate = criteriaBuilder.and(fileSha256ZipLevelPredicate,fileDrivePredicate);

            Predicate zipFileSha256ZipLevelPredicate = criteriaBuilder.and(zipFileFullPathSha256Predicate,zipFileZipLevelPredicate);
            Predicate zipFilePredicate = criteriaBuilder.and(zipFileSha256ZipLevelPredicate,zipFileDrivePredicate);

//            Predicate zipFilePredicate = criteriaBuilder.and(zipFileidPredicate,zipFileDrivePredicate);
            Predicate finalPredicate = criteriaBuilder.and(filePredicate,zipFilePredicate);
            criteriaQuery.where(finalPredicate);

            TypedQuery<EMFOFile> typedQuery = session.createQuery(criteriaQuery);
            EMFOFile file = typedQuery.getSingleResult();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            return Optional.of(file);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error("getMFOFileBySearchPathModelDrive No record found for the file full path SHA 256 :"+fileFullPathSha256+" , zipLevel : "+fileZipLevel+" , zipFileFullPathSha256 : "+zipFileFullPathSha256+" , zipFileZipLevel : "+zipFileZipLevel+" , driveCode : "+driveCode);
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
