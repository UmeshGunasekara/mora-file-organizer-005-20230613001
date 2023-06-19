/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 3:37 PM
 */
package com.slmora.learn.dao;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.jpa.entity.EMFODirectory;

import java.util.List;
import java.util.Optional;

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
public interface IMFODirectoryDao extends IGenericDao<byte[], EMFODirectory>
{
    public Optional<byte[]> addMFODirectory(EMFODirectory directory);

    public Optional<EMFODirectory> getMFODirectoryById(byte[] id);

    public void deleteMFODirectory(EMFODirectory directory);

    public List<EMFODirectory> getAllMFODirectories();

    public Optional<EMFODirectory> getMFODirectoryByCode(Integer code);

    public Optional<byte[]> persistMFODirectory(EMFODirectory directory);
}
