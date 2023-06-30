/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:47 PM
 */
package com.slmora.learn.service.impl;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.common.service.impl.GenericServiceImpl;
import com.slmora.learn.dao.IMFOFileCategoryDao;
import com.slmora.learn.jpa.entity.EMFOFileCategory;
import com.slmora.learn.service.IMFOFileCategoryService;
import com.slmora.learn.util.EMFileCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
 * <br>1.0          6/20/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MFOFileCategoryServiceImpl extends GenericServiceImpl<byte[], EMFOFileCategory> implements IMFOFileCategoryService
{
    final static Logger LOGGER = LogManager.getLogger(MFOFileCategoryServiceImpl.class);

    private IMFOFileCategoryDao fileCategoryDao;

    public MFOFileCategoryServiceImpl() {}

    public MFOFileCategoryServiceImpl(IGenericDao<byte[], EMFOFileCategory> fileCategoryGenericDao) {
        super(fileCategoryGenericDao);
        fileCategoryDao = (IMFOFileCategoryDao) fileCategoryGenericDao;
    }

    @Override
    public Optional<byte[]> addMFOFileCategory(EMFOFileCategory fileCategory)
    {
        return fileCategoryDao.addMFOFileCategory(fileCategory);
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryById(byte[] id)
    {
        return fileCategoryDao.getMFOFileCategoryById(id);
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryByUUID(UUID uuidKey)
    {
        return fileCategoryDao.getMFOFileCategoryByUUID(uuidKey);
    }

    @Override
    public void deleteMFOFileCategory(EMFOFileCategory fileCategory)
    {
        fileCategoryDao.deleteMFOFileCategory(fileCategory);
    }

    @Override
    public List<EMFOFileCategory> getAllMFOFileCategories()
    {
        return fileCategoryDao.getAllMFOFileCategories();
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryByCode(Integer code)
    {
        return fileCategoryDao.getMFOFileCategoryByCode(code);
    }

    @Override
    public Optional<byte[]> persistReturnIdMFOFileCategory(EMFOFileCategory fileCategory)
    {
        return fileCategoryDao.persistReturnIdMFOFileCategory(fileCategory);
    }

    @Override
    public EMFOFileCategory persistMFOFileCategory(EMFOFileCategory fileCategory)
    {
        return fileCategoryDao.persistMFOFileCategory(fileCategory);
    }

    @Override
    public Optional<EMFOFileCategory> getMFOFileCategoryByFileFormatName(String fileFormatName)
    {
        Optional<EMFOFileCategory> opEFileCategory =  fileCategoryDao.getMFOFileCategoryByFileFormatName(fileFormatName);
        if(!opEFileCategory.isPresent()){
            return fileCategoryDao.getMFOFileCategoryByCode(EMFileCategory.FILE_CAT_OTHER.getFileCategoryCode());
        }
        return opEFileCategory;
    }
}
