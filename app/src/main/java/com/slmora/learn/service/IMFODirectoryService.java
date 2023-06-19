/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 3:45 PM
 */
package com.slmora.learn.service;

import com.slmora.learn.common.service.IGenericService;
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

    public Optional<byte[]> persistMFODirectory(EMFODirectory directory);

}
