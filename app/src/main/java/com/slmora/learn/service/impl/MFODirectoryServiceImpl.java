/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 3:46 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.cryptography.hmac.util.EHmacAlgorithm;
import com.slmora.learn.common.cryptography.hmac.util.MoraHMACUtilities;
import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.IMFODirectoryDao;
import com.slmora.learn.dao.impl.MFOFileDaoImpl;
import com.slmora.learn.dto.DirectoryDto;
import com.slmora.learn.dto.FileDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.model.SearchPathModel;
import com.slmora.learn.service.IMFODirectoryService;
import com.slmora.learn.service.IMFOFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
public class MFODirectoryServiceImpl extends GenericServiceImpl<byte[], EMFODirectory> implements IMFODirectoryService
{

    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFODirectoryServiceImpl.class);
    private IMFODirectoryDao directoryDao;

    public MFODirectoryServiceImpl() {}

    public MFODirectoryServiceImpl(IGenericDao<byte[], EMFODirectory> directoryGenericDao) {
        super(directoryGenericDao);
        directoryDao = (IMFODirectoryDao) directoryGenericDao;
    }

    /**
     * @param address as PbAddress Object.
     * @return addressId Integer Object.
     * @apiNote Add Address.
     */
    @Override
    public Optional<byte[]> addMFODirectory(EMFODirectory directory) {
        return directoryDao.addMFODirectory(directory);
    }

    /**
     * @param addressId as Integer Object.
     * @return PbAddress PbAddress Object.
     * @apiNote Get Address By addressId.
     */
    @Override
    public Optional<EMFODirectory> getMFODirectoryById(byte[] id) {
        return directoryDao.getMFODirectoryById(id);
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryByUUID(UUID uuidKey)
    {
        return directoryDao.getMFODirectoryByUUID(uuidKey);
    }

    /**
     * @param address as PbAddress Object.
     * @apiNote Delete Address.
     */
    @Override
    public void deleteMFODirectory(EMFODirectory directory) {
        directoryDao.deleteMFODirectory(directory);
    }

    /**
     * @apiNote Get All Addresses.
     */
    @Override
    public List<EMFODirectory> getAllMFODirectories() {
        return directoryDao.getAllMFODirectories();
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryByCode(Integer code)
    {
        return directoryDao.getMFODirectoryByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFODirectory(EMFODirectory directory)
    {
        return directoryDao.persistReturnIdMFODirectory(directory);
    }

    @Override
    public EMFODirectory persistMFODirectory(EMFODirectory directory)
    {
        return directoryDao.persistMFODirectory(directory);
    }

    @Override
    public Optional<byte[]> persistMFODirectory(DirectoryDto directoryDto)
    {
        EMFODirectory eDir = directoryDao.persistMFODirectory(directoryDto.getEntity());
        if(!directoryDto.getFiles().isEmpty()){
            IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());
            directoryDto.getFiles().stream().map(FileDto::getEntity).peek(i->i.setDirectory(eDir)).forEach(i->fileService.persistMFOFile(i));
            fileService.close();
        }
        return Optional.of(eDir.getId());
    }

    @Override
    public Optional<List<EMFODirectory>> getMFODirectoryByDirectoryFullPathSha256AndZipLevel(String directoryFullPathSha256, Integer zipLevel)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Service call with directoryFullPathSha256 {} zipLevel {} ", directoryFullPathSha256, zipLevel);
        return directoryDao.getAllMFODirectoryByDirectoryFullPathSha256AndZipLevel(directoryFullPathSha256, zipLevel);
    }

    @Override
    public Optional<List<EMFODirectory>> getMFODirectoryByDirectoryFullPathSha256AndZipLevelDrive(String directoryFullPathSha256,
                                                                                                  Integer zipLevel,
                                                                                                  Integer directoryDriveCode)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Service call with directoryFullPathSha256 {} zipLevel {} directoryDriveCode {}", directoryFullPathSha256, zipLevel, directoryDriveCode);
        return directoryDao.getAllMFODirectoryByDirectoryFullPathSha256AndZipLevelDrive(directoryFullPathSha256, zipLevel,directoryDriveCode);
    }

    @Override
    public Optional<List<EMFODirectory>> getAllMFODirectoryByDirectoryFullPathAndZipLevel(String directoryFullPath, Integer zipLevel) throws
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Service call with directoryFullPath {} zipLevel {} ", directoryFullPath, zipLevel);
        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
        Path dir = Paths.get(directoryFullPath);
        String dirName = dir.toString();
        if(!dir.toString().equals(dir.getRoot().toString())){
            dirName = dir.getFileName().toString();
        }
        return getMFODirectoryByDirectoryFullPathSha256AndZipLevel(
                hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                        EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                        directoryFullPath, dirName), zipLevel);
    }

    @Override
    public Optional<List<EMFODirectory>> getAllMFODirectoryByDirectoryFullPathAndZipLevelDrive(String directoryFullPath,
                                                                                               Integer zipLevel,
                                                                                               Integer directoryDriveCode) throws
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Service call with directoryFullPath {},  directoryFullPath {}, directoryDriveCode {} ", directoryFullPath, zipLevel, directoryDriveCode);
        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
        Path dir = Paths.get(directoryFullPath);
        String dirName = dir.toString();
        if(!dir.toString().equals(dir.getRoot().toString())){
            dirName = dir.getFileName().toString();
        }
        return getMFODirectoryByDirectoryFullPathSha256AndZipLevelDrive(
                hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                        EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                        directoryFullPath, dirName), zipLevel,directoryDriveCode);
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryByDirectoryFullPathSha256AndZipLevelZipFileDrive(String directoryFullPathSha256,
                                                                                                   Integer zipLevel,
                                                                                                   EMFOFile zipFile, Integer directoryDriveCode)
    {
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Service call with directoryFullPathSha256 {},  zipLevel {}, zipFile UUID {}, directoryDriveCode {} ", directoryFullPathSha256, zipLevel, (null!=zipFile)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipFile.getId()):null, directoryDriveCode);
        return directoryDao.getMFODirectoryByDirectoryFullPathSha256AndZipLevelZipFileDrive(directoryFullPathSha256,zipLevel,zipFile,directoryDriveCode);
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryByDirectoryFullPathAndZipLevelZipFileDrive(String directoryFullPath,
                                                                                             Integer zipLevel,
                                                                                             EMFOFile zipFile, Integer directoryDriveCode) throws
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Service call with directoryFullPath {},  zipLevel {}, zipFile UUID {}, directoryDriveCode {} ", directoryFullPath, zipLevel, (null!=zipFile)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(zipFile.getId()):null, directoryDriveCode);
        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
        Path dir = Paths.get(directoryFullPath);
        String dirName = dir.toString();
        if(!dir.toString().equals(dir.getRoot().toString())){
            dirName = dir.getFileName().toString();
        }
        return getMFODirectoryByDirectoryFullPathSha256AndZipLevelZipFileDrive(
                hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                        EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                        directoryFullPath, dirName), zipLevel, zipFile,directoryDriveCode);
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryBySearchPathModelDrive(SearchPathModel pathModel, Integer directoryDriveCode) throws
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Service call with pathModel {},  directoryDriveCode {} ", (null!=pathModel)?pathModel.toString():null, directoryDriveCode);
        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
        Path dir = pathModel.getPath();
        String dirName = dir.toString();
        if(!dir.toString().equals(dir.getRoot().toString())){
            dirName = dir.getFileName().toString();
        }

        Path zipFile=null;
        String zipFileName=null;
        if(pathModel.getZipParentFilePath()!=null) {
            zipFile = pathModel.getZipParentFilePath();
            zipFileName = zipFile.getFileName().toString();
        }

        if(pathModel.getZipParentFilePath()==null){
            Optional<List<EMFODirectory>> opListDir = getAllMFODirectoryByDirectoryFullPathAndZipLevelDrive(pathModel.getPath().toAbsolutePath().toString(), pathModel.getZipLevel(),directoryDriveCode);
            if(opListDir.isPresent()){
                if(opListDir.get().size()==1){
                    return Optional.of(opListDir.get().get(0));
                }else {
                    return Optional.empty();
                }
            }else {
                return Optional.empty();
            }
        } else if (pathModel.getZipParentFileId()==null) {
            return directoryDao.getMFODirectoryBySearchPathModelDrive(
                    hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                            EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                            dir.toAbsolutePath().toString(), dirName),
                    pathModel.getZipLevel(),
                    hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                            EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                            zipFile.toAbsolutePath().toString(), zipFileName),
                    pathModel.getZipLevel(),
                    directoryDriveCode);
        }else {
            return directoryDao.getMFODirectoryBySearchPathModelDrive(
                    hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                            EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                            dir.toAbsolutePath().toString(), dirName), pathModel.getZipLevel(),
                    pathModel.getZipParentFileId(),directoryDriveCode);
        }
    }

}
