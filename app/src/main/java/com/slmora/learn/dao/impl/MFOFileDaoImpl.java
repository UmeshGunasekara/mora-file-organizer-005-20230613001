/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 8:44 PM
 */
package com.slmora.learn.dao.impl;

import com.slmora.learn.common.dao.impl.GenericDaoImpl;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
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
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFOFileDaoImpl.class);
    private MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

    @Override
    public Optional<byte[]> addMFOFile(EMFOFile file)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Adding File with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
        return add(file);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileById(byte[] id)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve File with UUID {}", (null!=id)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(id):null);
        return getById(id);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileByUUID(UUID uuidKey)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve File with UUID {}", (null!=uuidKey)?uuidKey:null);
        return getByUUID(uuidKey);
//        return Optional.empty();
    }

    @Override
    public void deleteMFOFile(EMFOFile file)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Delete File with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
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
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve File with Code {}", code);
        return getByCode(code);
//        return Optional.empty();
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFile(EMFOFile file)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding File with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
        return persistReturnId(file);
//        return Optional.empty();
    }

    @Override
    public EMFOFile persistMFOFile(EMFOFile file)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Adding File with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
        return persist(file);
//        return Optional.empty();
    }

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256(String fileFullPathSha256)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve files with fileFullPathSha256 {}", fileFullPathSha256);
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
            List<EMFOFile> filesList = typedQuery.getResultList();
//            transaction=getSession().beginTransaction();
//            T t= (T) session.get(daoType, key);
            transaction.commit();
            LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve files with fileFullPathSha256 {} out List Size {}", fileFullPathSha256, (null!=filesList)?filesList.size():null);
            return Optional.of(filesList);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001", "No files found with fileFullPathSha256 {}", fileFullPathSha256);
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

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256AndZipLevel(String fileFullPathSha256,
                                                                                 Integer zipLevel)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve files with fileFullPathSha256 {} and zipLevel {}", fileFullPathSha256, zipLevel);
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
            LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve files with fileFullPathSha256 {}, zipLevel {} out List Size {}", fileFullPathSha256, zipLevel, (null!=fileList)?fileList.size():null);
            return Optional.of(fileList);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001", "No files found with fileFullPathSha256 {} and zipLevel {}", fileFullPathSha256, zipLevel);
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

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256AndZipLevelDrive(String fileFullPathSha256,
                                                                                      Integer zipLevel,
                                                                                      Integer fileDriveCode)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve files with fileFullPathSha256 {}, zipLevel {} and fileDriveCode {}", fileFullPathSha256, zipLevel, fileDriveCode);
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
            LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve files with fileFullPathSha256 {}, zipLevel {} and fileDriveCode {} out List Size {}", fileFullPathSha256, zipLevel, fileDriveCode, (null!=fileList)?fileList.size():null);
            return Optional.of(fileList);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001", "No files found with fileFullPathSha256 {}, zipLevel {} and fileDriveCode {}", fileFullPathSha256, zipLevel, fileDriveCode);
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

    @Override
    public Optional<EMFOFile> getMFOFileByFileFullPathSha256AndZipLevelZipFileDrive(String fileFullPathSha256,
                                                                                    Integer zipLevel,
                                                                                    EMFOFile zipFile,
                                                                                    Integer fileDriveCode)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file with fileFullPathSha256 {}, zipLevel {}, Zip File UUID {} and fileDriveCode {}", fileFullPathSha256, zipLevel, (null!=zipFile)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipFile.getId()):null, fileDriveCode);
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
            LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
            return Optional.of(file);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001", "No file found with fileFullPathSha256 {}, zipLevel {}, Zip File UUID {} and fileDriveCode {}", fileFullPathSha256, zipLevel, (null!=zipFile)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipFile.getId()):null, fileDriveCode);
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

    @Override
    public Optional<EMFOFile> getMFOFileBySearchPathModelDrive(String fileFullPathSha256,
                                                               Integer zipLevel, byte[] zipFileId,
                                                               Integer fileDriveCode)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file with fileFullPathSha256 {}, zipLevel {}, Zip File UUID {} and fileDriveCode {}", fileFullPathSha256, zipLevel, (null!=zipFileId)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipFileId):null, fileDriveCode);
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
            LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
            return Optional.of(file);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.error(Thread.currentThread().getStackTrace(), "ERRO-00001", "No file found with fileFullPathSha256 {}, zipLevel {}, Zip File UUID {} and fileDriveCode {}", fileFullPathSha256, zipLevel, (null!=zipFileId)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipFileId):null, fileDriveCode);
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

    @Override
    public Optional<EMFOFile> getMFOFileBySearchPathModelDrive(String fileFullPathSha256,
                                                               Integer fileZipLevel,
                                                               String zipFileFullPathSha256,
                                                               Integer zipFileZipLevel,
                                                               Integer driveCode)
    {
        LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file with fileFullPathSha256 {}, fileZipLevel {}, zipFileFullPathSha256 {}, zipFileZipLevel {} and driveCode {}", fileFullPathSha256, fileZipLevel, zipFileFullPathSha256, zipFileZipLevel, driveCode);
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
            LOGGER.info(Thread.currentThread().getStackTrace(), "Retrieve file with UUID {}", (null!=file)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(file.getId()):null);
            return Optional.of(file);

//            Session session = getSession();
//            session.createNamedQuery("")
        }catch (NoResultException ex){
            LOGGER.info(Thread.currentThread().getStackTrace(), "ERRO-00001", "No file found with fileFullPathSha256 {}, fileZipLevel {}, zipFileFullPathSha256 {}, zipFileZipLevel {} and driveCode {}", fileFullPathSha256, fileZipLevel, zipFileFullPathSha256, zipFileZipLevel, driveCode);
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
