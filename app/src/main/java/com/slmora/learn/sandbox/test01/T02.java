/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 7:31 PM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.dto.DirectoryDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;

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
 * <br>1.0          6/19/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T02 {
    public static void main(String[] args)
    {
        DirectoryDto directoryDto = new DirectoryDto();

        directoryDto.setDirectoryName("Brain Byte");
        directoryDto.setDirectoryFullPath("D:\\MORA\\Video\\Java Brains\\Brain Byte");
        directoryDto.setNote("Test Note 01");

        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        MFODirectoryServiceImpl service = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        Optional<byte[]> id = service.addMFODirectory(directoryDto.getEntity());

        if(id.isPresent()) {
            UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(id.get());
            System.out.println(uuid.toString());
        }

        List<EMFODirectory> entries = service.getAllMFODirectories();
        if(entries.size()>0){
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getDirectoryId()).toString()));
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getId()).toString()));
            entries.stream().map(DirectoryDto::new).forEach(i->System.out.println(i.getId().toString()));
        }

        service.close();

    }
}
