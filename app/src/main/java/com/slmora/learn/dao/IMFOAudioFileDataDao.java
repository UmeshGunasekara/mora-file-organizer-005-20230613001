/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 10:32 PM
 */
package com.slmora.learn.dao;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.jpa.entity.EMFOAudioFileData;

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
public interface IMFOAudioFileDataDao extends IGenericDao<byte[], EMFOAudioFileData>
{
    public Optional<byte[]> addMFOAudioFileData(EMFOAudioFileData file);

    public Optional<EMFOAudioFileData> getMFOAudioFileDataById(byte[] id);

    public Optional<EMFOAudioFileData> getMFOAudioFileDataByUUID(UUID uuidKey);

    public void deleteMFOAudioFileData(EMFOAudioFileData file);

    public List<EMFOAudioFileData> getAllMFOAudioFileData();

    public Optional<EMFOAudioFileData> getMFOAudioFileDataByCode(Integer code);

    public Optional<byte[]> persistReturnIdMFOAudioFileData(EMFOAudioFileData file);

    public EMFOAudioFileData persistMFOAudioFileData(EMFOAudioFileData file);
}
