/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 2:03 PM
 */
package com.slmora.learn.service;

import com.slmora.learn.common.service.IGenericService;
import com.slmora.learn.jpa.entity.EMFOFileFormat;

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
public interface IMFOFileFormatService extends IGenericService<byte[], EMFOFileFormat>
{
    public Optional<byte[]> addMFOFileFormat(EMFOFileFormat fileFormat);

    public Optional<EMFOFileFormat> getMFOFileFormatById(byte[] id);

    public Optional<EMFOFileFormat> getMFOFileFormatByUUID(UUID uuidKey);

    public void deleteMFOFileFormat(EMFOFileFormat fileFormat);

    public List<EMFOFileFormat> getAllMFOFileFormats();

    public Optional<EMFOFileFormat> getMFOFileFormatByCode(Integer code);

    public Optional<byte[]> persistReturnIdMFOFileFormat(EMFOFileFormat fileFormat);

    public EMFOFileFormat persistMFOFileFormat(EMFOFileFormat fileFormat);
}
