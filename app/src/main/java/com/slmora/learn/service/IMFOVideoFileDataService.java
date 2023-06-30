/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 10:40 PM
 */
package com.slmora.learn.service;

import com.slmora.learn.common.service.IGenericService;
import com.slmora.learn.jpa.entity.EMFOVideoFileData;

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
 * <br>1.0          6/29/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public interface IMFOVideoFileDataService extends IGenericService<byte[], EMFOVideoFileData>
{
    public Optional<byte[]> addMFOVideoFileData(EMFOVideoFileData file);

    public Optional<EMFOVideoFileData> getMFOVideoFileDataById(byte[] id);

    public Optional<EMFOVideoFileData> getMFOVideoFileDataByUUID(UUID uuidKey);

    public void deleteMFOVideoFileData(EMFOVideoFileData file);

    public List<EMFOVideoFileData> getAllMFOVideoFileData();

    public Optional<EMFOVideoFileData> getMFOVideoFileDataByCode(Integer code);

    public Optional<byte[]> persistReturnIdMFOVideoFileData(EMFOVideoFileData file);

    public EMFOVideoFileData persistMFOVideoFileData(EMFOVideoFileData file);
}
