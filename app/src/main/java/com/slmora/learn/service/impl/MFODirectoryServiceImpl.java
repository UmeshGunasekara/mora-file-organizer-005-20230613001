/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 3:46 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.dao.IMFODirectoryDao;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.service.IMFODirectoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

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
 * <br>1.0          6/14/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFODirectoryServiceImpl extends GenericServiceImpl<byte[], EMFODirectory> implements IMFODirectoryService
{

    final static Logger LOGGER = LogManager.getLogger(MFODirectoryServiceImpl.class);
    private IMFODirectoryDao directoryDao;

    public MFODirectoryServiceImpl() {}

    public MFODirectoryServiceImpl(IGenericDao<byte[], EMFODirectory> directoryGenericDao) {
        super(directoryGenericDao);
        directoryDao = (IMFODirectoryDao) directoryGenericDao;
    }

    /**
     * @param address as PbAddress Object.
     * @return addressId Integer Object.
     * @apiNote Add Address.
     */
    @Override
    public Optional<byte[]> addMFODirectory(EMFODirectory directory) {
        System.out.println("com.payboot.merchant.service.impl.MerchantAddressServiceImpl.addAddress()");
        return directoryDao.addMFODirectory(directory);
    }

    /**
     * @param addressId as Integer Object.
     * @return PbAddress PbAddress Object.
     * @apiNote Get Address By addressId.
     */
    @Override
    public Optional<EMFODirectory> getMFODirectoryById(byte[] id) {
        System.out.println("com.payboot.merchant.service.impl.MerchantAddressServiceImpl.getAddressById()");
        return directoryDao.getMFODirectoryById(id);
    }

    /**
     * @param address as PbAddress Object.
     * @apiNote Delete Address.
     */
    @Override
    public void deleteMFODirectory(EMFODirectory directory) {
        System.out.println("com.payboot.merchant.service.impl.MerchantAddressServiceImpl.deleteAddress()");
        directoryDao.deleteMFODirectory(directory);
    }

    /**
     * @apiNote Get All Addresses.
     */
    @Override
    public List<EMFODirectory> getAllMFODirectories() {
        System.out.println("com.payboot.merchant.service.impl.MerchantAddressServiceImpl.getAllAddresses()");
        return directoryDao.getAllMFODirectories();
    }

    @Override
    public Optional<EMFODirectory> getMFODirectoryByCode(Integer code)
    {
        return directoryDao.getMFODirectoryByCode(code);
    }

    @Override
    public Optional<byte[]> persistMFODirectory(EMFODirectory directory)
    {
        return directoryDao.persistMFODirectory(directory);
    }

}
