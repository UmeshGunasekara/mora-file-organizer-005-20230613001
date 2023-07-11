/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 3:45 PM
 */
package com.slmora.learn.service;

import com.slmora.learn.common.service.IGenericService;
import com.slmora.learn.dto.DirectoryDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.model.SearchPathModel;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
 * <br>1.0          6/14/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public interface IMFODirectoryService extends IGenericService<byte[], EMFODirectory>
{

    /**
     * @param address as PbAddress Object.
     * @return addressId Integer Object.
     * @apiNote Add Address.
     */
    public Optional<byte[]> addMFODirectory(EMFODirectory directory);

    /**
     * @param addressId as Integer Object.
     * @return PbAddress PbAddress Object.
     * @apiNote Get Address By addressId.
     */
    public Optional<EMFODirectory> getMFODirectoryById(byte[] id);

    public Optional<EMFODirectory> getMFODirectoryByUUID(UUID uuidKey);

    /**
     * @param address as PbAddress Object.
     * @apiNote Delete Address.
     */
    public void deleteMFODirectory(EMFODirectory directory);

    /**
     * @apiNote Get All Addresses.
     */
    public List<EMFODirectory> getAllMFODirectories();

    public Optional<EMFODirectory> getMFODirectoryByCode(Integer code);

    public Optional<byte[]> persistReturnIdMFODirectory(EMFODirectory directory);

    public EMFODirectory persistMFODirectory(EMFODirectory directory);

    public Optional<byte[]> persistMFODirectory(DirectoryDto directoryDto);

    public Optional<List<EMFODirectory>> getMFODirectoryByDirectoryFullPathSha256AndZipLevel(String directoryFullPathSha256, Integer zipLevel);

    public Optional<List<EMFODirectory>> getMFODirectoryByDirectoryFullPathSha256AndZipLevelDrive(String directoryFullPathSha256, Integer zipLevel, Integer directoryDriveCode);

    public Optional<List<EMFODirectory>> getAllMFODirectoryByDirectoryFullPathAndZipLevel(String directoryFullPath, Integer zipLevel) throws
            NoSuchAlgorithmException,
            InvalidKeyException;

    public Optional<List<EMFODirectory>> getAllMFODirectoryByDirectoryFullPathAndZipLevelDrive(String directoryFullPath, Integer zipLevel, Integer directoryDriveCode) throws
            NoSuchAlgorithmException,
            InvalidKeyException;

    public Optional<EMFODirectory> getMFODirectoryByDirectoryFullPathSha256AndZipLevelZipFileDrive(String directoryFullPathSha256, Integer zipLevel, EMFOFile zipFile, Integer directoryDriveCode);

    public Optional<EMFODirectory> getMFODirectoryByDirectoryFullPathAndZipLevelZipFileDrive(String directoryFullPath, Integer zipLevel, EMFOFile zipFile, Integer directoryDriveCode) throws
            NoSuchAlgorithmException,
            InvalidKeyException;

    Optional<EMFODirectory> getMFODirectoryBySearchPathModelDrive(SearchPathModel pathModel, Integer directoryDriveCode) throws
            NoSuchAlgorithmException,
            InvalidKeyException;

}
