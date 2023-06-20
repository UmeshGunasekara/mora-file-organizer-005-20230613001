/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 9:16 AM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.dao.impl.MFOFileDaoImpl;
import com.slmora.learn.dto.DirectoryDto;
import com.slmora.learn.dto.FileDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.service.IMFODirectoryService;
import com.slmora.learn.service.IMFOFileService;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;
import com.slmora.learn.service.impl.MFOFileServiceImpl;

import java.util.List;

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
public class T05 {
    public static void main(String[] args)
    {
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());

        List<EMFODirectory> dirEntries = dirService.getAllMFODirectories();
        if(dirEntries.size()>0){
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getDirectoryId()).toString()));
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getId()).toString()));
            dirEntries.stream().map(DirectoryDto::new).forEach(i->System.out.println(i.getId().toString()));
        }

        List<EMFOFile> fileEntries = fileService.getAllMFOFiles();
        if(fileEntries.size()>0){
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getDirectoryId()).toString()));
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getId()).toString()));
            fileEntries.stream().map(FileDto::new).forEach(i->System.out.println(i.getId().toString()+" , "+i.getCode()));
        }

        dirService.close();
        fileService.close();
    }
}
