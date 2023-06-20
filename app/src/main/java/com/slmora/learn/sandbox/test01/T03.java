/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 8:15 PM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;

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
 * <br>1.0          6/19/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T03 {
    public static void main(String[] args)
    {
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        MFODirectoryServiceImpl service = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
//        Optional<EMFODirectory> opDir = service.getMFODirectoryByCode(5);
        Optional<EMFODirectory> opDir = service.getMFODirectoryByUUID(UUID.fromString("8f143f99-f9fb-4dea-95be-8659021e8f08"));
        if(opDir.isPresent()){
            System.out.println(opDir.get().getDirectoryFullPath());
        }
        service.close();
    }
}
