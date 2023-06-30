/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 9:21 PM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.dao.impl.MFOFileCategoryDaoImpl;
import com.slmora.learn.jpa.entity.EMFOFileCategory;
import com.slmora.learn.service.IMFOFileCategoryService;
import com.slmora.learn.service.impl.MFOFileCategoryServiceImpl;

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
 * <br>1.0          6/29/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T12 {
    public static void main(String[] args)
    {
        IMFOFileCategoryService fileCategoryService = new MFOFileCategoryServiceImpl(new MFOFileCategoryDaoImpl());

        Optional<EMFOFileCategory> opFileCategory = fileCategoryService.getMFOFileCategoryByFileFormatName("mp4");
        if(opFileCategory.isPresent()){
            System.out.println(opFileCategory.get().getFileCategoryName());
        }else {
            System.out.println("Not found");
        }

        fileCategoryService.close();
    }
}
