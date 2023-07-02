/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/30/2023 5:15 PM
 */
package com.slmora.learn.dao;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.jpa.entity.EMFOZipDirectoryFile;

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
 * <br>1.0          6/30/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public interface IMFOZipDirectoryFileDao extends IGenericDao<byte[], EMFOZipDirectoryFile>
{
    public Optional<byte[]> addMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile);

    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileById(byte[] id);

    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileByUUID(UUID uuidKey);

    public void deleteMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile);

    public List<EMFOZipDirectoryFile> getAllMFOZipDirectoryFiles();

    public Optional<EMFOZipDirectoryFile> getMFOZipDirectoryFileByCode(Integer code);

    public Optional<byte[]> persistReturnIdMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile);

    public EMFOZipDirectoryFile persistMFOZipDirectoryFile(EMFOZipDirectoryFile zipDirectoryFile);
}
