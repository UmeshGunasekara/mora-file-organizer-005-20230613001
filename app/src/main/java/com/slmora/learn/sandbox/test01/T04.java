/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 8:36 PM
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
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
public class T04 {
    public static void main(String[] args) throws InterruptedException
    {
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());

        Optional<EMFODirectory> opDir = dirService.getMFODirectoryByCode(6);
        EMFODirectory dir = null;
        if(opDir.isPresent()){
            dir = opDir.get();
            System.out.println(dir.getDirectoryFullPath());
        }

        EMFOFile file = new EMFOFile();

        file.setFileName("6.mp4");
        file.setFileFullPath("D:\\MORA\\Video\\Java Brains\\Brain Byte\\6.mp4");
        file.setNote("Test Note 01");
        file.setDirectory(dir);


        Optional<byte[]> id = fileService.persistReturnIdMFOFile(file);

        System.out.println("new code :"+file.getCode());

//        fileService.getSession().flush();
//
//        System.out.println("new code :"+file.getCode());

        if(id.isPresent()) {
            UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(id.get());
            System.out.println(uuid.toString());
        }

        List<EMFOFile> entries = fileService.getAllMFOFiles();
        if(entries.size()>0){
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getDirectoryId()).toString()));
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getId()).toString()));
            entries.stream().map(FileDto::new).forEach(i->System.out.println(i.getId().toString()+" , "+i.getCode()));
        }

        dirService.close();
        fileService.close();

        System.out.println("2=========================================");

        IMFOFileService fileService2 = new MFOFileServiceImpl(new MFOFileDaoImpl());

        List<EMFOFile> entries2 = fileService2.getAllMFOFiles();
        if(entries2.size()>0){
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getDirectoryId()).toString()));
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getId()).toString()));
            entries2.stream().map(FileDto::new).forEach(i->System.out.println(i.getId().toString()+" , "+i.getCode()));
        }

        fileService2.close();

    }
}
