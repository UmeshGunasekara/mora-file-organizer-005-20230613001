/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:44 PM
 */
package com.slmora.learn.service;

import com.slmora.learn.common.service.IGenericService;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOFileCategory;

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
 * <br>1.0          6/20/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public interface IMFOFileCategoryService extends IGenericService<byte[], EMFOFileCategory>
{
    public Optional<byte[]> addMFOFileCategory(EMFOFileCategory fileCategory);

    public Optional<EMFOFileCategory> getMFOFileCategoryById(byte[] id);

    public Optional<EMFOFileCategory> getMFOFileCategoryByUUID(UUID uuidKey);

    public void deleteMFOFileCategory(EMFOFileCategory fileCategory);

    public List<EMFOFileCategory> getAllMFOFileCategories();

    public Optional<EMFOFileCategory> getMFOFileCategoryByCode(Integer code);

    public Optional<byte[]> persistReturnIdMFOFileCategory(EMFOFileCategory fileCategory);

    public EMFOFileCategory persistMFOFileCategory(EMFOFileCategory fileCategory);

    public Optional<EMFOFileCategory> getMFOFileCategoryByFileFormatName(String fileFormatName);
}
