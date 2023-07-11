/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 8:51 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.cryptography.hmac.util.EHmacAlgorithm;
import com.slmora.learn.common.cryptography.hmac.util.MoraHMACUtilities;
import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.dao.IMFOFileDao;
import com.slmora.learn.dto.FileDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.model.SearchPathModel;
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
 * <br>1.0          6/19/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFOFileServiceImpl extends GenericServiceImpl<byte[], EMFOFile> implements IMFOFileService
{
    final static Logger LOGGER = LogManager.getLogger(MFOFileServiceImpl.class);

    private IMFOFileDao fileDao;

    public MFOFileServiceImpl() {}

    public MFOFileServiceImpl(IGenericDao<byte[], EMFOFile> fileGenericDao) {
        super(fileGenericDao);
        fileDao = (IMFOFileDao) fileGenericDao;
    }

    @Override
    public Optional<byte[]> addMFOFile(EMFOFile file)
    {
        return fileDao.addMFOFile(file);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileById(byte[] id)
    {
        return fileDao.getMFOFileById(id);
//        return Optional.empty();
    }

    @Override
    public Optional<EMFOFile> getMFOFileByUUID(UUID uuidKey)
    {
        return fileDao.getMFOFileByUUID(uuidKey);
//        return Optional.empty();
    }

    @Override
    public void deleteMFOFile(EMFOFile file)
    {
        fileDao.deleteMFOFile(file);
    }

    @Override
    public List<EMFOFile> getAllMFOFiles()
    {
        return fileDao.getAllMFOFiles();
//        return null;
    }

    @Override
    public Optional<EMFOFile> getMFOFileByCode(Integer code)
    {
        return fileDao.getMFOFileByCode(code);
//        return Optional.empty();
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFile(EMFOFile file)
    {
        return fileDao.persistReturnIdMFOFile(file);
//        return Optional.empty();
    }

    @Override
    public EMFOFile persistMFOFile(EMFOFile file)
    {
        return fileDao.persistMFOFile(file);
//        return Optional.empty();
    }

    @Override
    public Optional<byte[]> persistMFOFile(FileDto fileDto)
    {
        return fileDao.persistReturnIdMFOFile(fileDto.getEntity());
    }

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256(String fileFullPathSha256)
    {
        return fileDao.getAllMFOFileByFileFullPathSha256(fileFullPathSha256);
    }

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPath(String fileFullPath) throws
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
        Path file = Paths.get(fileFullPath);
        String fileName = file.getFileName().toString();
        return getAllMFOFileByFileFullPathSha256(
                hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                        EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                        fileFullPath, fileName));
    }

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256AndZipLevel(String fileFullPathSha256,
                                                                                 Integer zipLevel)
    {
        return fileDao.getAllMFOFileByFileFullPathSha256AndZipLevel(fileFullPathSha256,zipLevel);
    }

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256AndZipLevelDrive(String fileFullPathSha256,
                                                                                      Integer zipLevel,
                                                                                      Integer fileDriveCode)
    {
        return fileDao.getAllMFOFileByFileFullPathSha256AndZipLevelDrive(fileFullPathSha256,zipLevel,fileDriveCode);
    }

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathAndZipLevel(String fileFullPath, Integer zipLevel) throws
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
        Path file = Paths.get(fileFullPath);
        String fileName = file.getFileName().toString();
        return getAllMFOFileByFileFullPathSha256AndZipLevel(
                hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                        EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                        fileFullPath, fileName), zipLevel);
    }

    @Override
    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathAndZipLevelDrive(String fileFullPath,
                                                                                Integer zipLevel,
                                                                                Integer fileDriveCode) throws
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
        Path file = Paths.get(fileFullPath);
        String fileName = file.getFileName().toString();
        return getAllMFOFileByFileFullPathSha256AndZipLevelDrive(
                hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                        EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                        fileFullPath, fileName), zipLevel,fileDriveCode);
    }

    @Override
    public Optional<EMFOFile> getMFOFileByFileFullPathSha256AndZipLevelZipFileDrive(String fileFullPathSha256,
                                                                                    Integer zipLevel,
                                                                                    EMFOFile zipFile,
                                                                                    Integer fileDriveCode)
    {
        return fileDao.getMFOFileByFileFullPathSha256AndZipLevelZipFileDrive(fileFullPathSha256,zipLevel,zipFile,fileDriveCode);
    }

    @Override
    public Optional<EMFOFile> getMFOFileByFileFullPathAndZipLevelZipFileDrive(String fileFullPath,
                                                                              Integer zipLevel,
                                                                              EMFOFile zipFile,
                                                                              Integer fileDriveCode) throws
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
        Path file = Paths.get(fileFullPath);
        String fileName = file.getFileName().toString();
        return getMFOFileByFileFullPathSha256AndZipLevelZipFileDrive(
                hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                        EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                        fileFullPath, fileName), zipLevel, zipFile,fileDriveCode);
    }

    @Override
    public Optional<EMFOFile> getMFOFileBySearchPathModelDrive(SearchPathModel pathModel,
                                                                    Integer fileDriveCode) throws
            NoSuchAlgorithmException,
            InvalidKeyException
    {
        if(pathModel.getZipParentFile()==null) {
            Optional<List<EMFOFile>> opListFile = getAllMFOFileByFileFullPathAndZipLevelDrive(pathModel.getPath().toAbsolutePath().toString(), pathModel.getZipLevel(),fileDriveCode);
            if(opListFile.isPresent()){
                if(opListFile.get().size()==1){
                    return Optional.of(opListFile.get().get(0));
                }else {
                    return Optional.empty();
                }
            }else {
                return Optional.empty();
            }
        }else {
            MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
            Path file = pathModel.getPath();
            String fileName = file.getFileName().toString();

            Path zipFile = pathModel.getZipParentFile();
            return fileDao.getMFOFileBySearchPathModelDrive(
                    hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                            EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                            file.toAbsolutePath().toString(), fileName), pathModel.getZipLevel(),
                    hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                            EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                            zipFile.toAbsolutePath().toString(), zipFile.getFileName().toString()), pathModel.getZipParentFileLevel(),fileDriveCode);
        }
    }
}
