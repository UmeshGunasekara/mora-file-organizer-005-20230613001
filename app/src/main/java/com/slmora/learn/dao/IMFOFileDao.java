/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 8:42 PM
 */
package com.slmora.learn.dao;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
 * <br>1.0          6/19/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public interface IMFOFileDao extends IGenericDao<byte[], EMFOFile>
{
    public Optional<byte[]> addMFOFile(EMFOFile file);

    public Optional<EMFOFile> getMFOFileById(byte[] id);

    public Optional<EMFOFile> getMFOFileByUUID(UUID uuidKey);

    public void deleteMFOFile(EMFOFile file);

    public List<EMFOFile> getAllMFOFiles();

    public Optional<EMFOFile> getMFOFileByCode(Integer code);

    public Optional<byte[]> persistReturnIdMFOFile(EMFOFile file);

    public EMFOFile persistMFOFile(EMFOFile file);

    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256(String fileFullPathSha256);

    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256AndZipLevel(String fileFullPathSha256, Integer zipLevel);

    public Optional<List<EMFOFile>> getAllMFOFileByFileFullPathSha256AndZipLevelDrive(String fileFullPathSha256, Integer zipLevel, Integer fileDriveCode);

    public Optional<EMFOFile> getMFOFileByFileFullPathSha256AndZipLevelZipFileDrive(String fileFullPathSha256, Integer zipLevel, EMFOFile zipFile, Integer fileDriveCode);

    Optional<EMFOFile> getMFOFileBySearchPathModelDrive(String fileFullPathSha256, Integer zipLevel, byte[] zipFileId, Integer fileDriveCode);

    Optional<EMFOFile> getMFOFileBySearchPathModelDrive(String fileFullPathSha256, Integer fileZipLevel, String zipFileFullPathSha256, Integer zipFileZipLevel, Integer driveCode);

}
