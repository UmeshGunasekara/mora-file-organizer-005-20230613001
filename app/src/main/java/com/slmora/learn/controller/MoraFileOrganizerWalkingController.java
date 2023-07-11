/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/1/2023 3:24 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.model.SearchPathModel;
import com.slmora.learn.service.IMFODirectoryService;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;
import com.slmora.learn.system.property.SingleSystemProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
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
 * <br>1.0          7/1/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MoraFileOrganizerWalkingController {
    final static Logger LOGGER = LogManager.getLogger(MoraFileOrganizerWalkingController.class);


    public void sourcePathWalk(Path source, Integer zipFileLevel, Path zipParent, EMFOFile eFile, Integer driveCode){

        try{
            Files.walkFileTree(source,
                    new HashSet<FileVisitOption>(Arrays.asList(FileVisitOption.FOLLOW_LINKS)),
                    Integer.MAX_VALUE,
                    new SimpleFileVisitor<>()
                    {


                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        {
//                            System.out.printf("-------- Visiting file %s\n", file+", Level: "+zipFileLevel);
                            LOGGER.info("-------- Visiting file :"+ file.toAbsolutePath()+", Level: "+zipFileLevel);
//                            LOGGER.info("Visiting file "+file+"\n");
                            MFOFileController fileController = new MFOFileController();
                            if(zipFileLevel<1) {
                                fileController.addFile(file, 0, driveCode);
                            }else {
//                                SingleSystemProperty.SEARCH_FILE_STACK.push(SearchPathModel.of(file.toAbsolutePath().toString(),zipFileLevel));
                                fileController.addFile(file, zipFileLevel, eFile, zipParent, driveCode);
                            }
                            System.gc();
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException e)
                        {
//                            System.err.printf("Visiting failed for %s\n", file+", Level: "+zipFileLevel);
//                            LOGGER.info("Visiting failed for "+file+"\n");
                            return FileVisitResult.SKIP_SUBTREE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir,
                                                                 BasicFileAttributes attrs)
                        {
//                            System.out.printf("---------- About to visit directory %s\n", dir);
                            LOGGER.info("---------- Visiting directory :"+ dir.toAbsolutePath()+", Level: "+zipFileLevel);
//                            LOGGER.info("About to visit directory "+dir+"\n");
                            MFODirectoryController directoryController = new MFODirectoryController();
//                            SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dir.toAbsolutePath().toString(),zipFileLevel));
                            if(zipFileLevel<1) {
                                directoryController.addDirectory(dir,0, driveCode);
                            }else {
                                directoryController.addDirectory(dir, zipFileLevel, eFile, zipParent, driveCode);
                            }
                            System.gc();
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                        {
//                            System.out.println("Post Visit Directory: " + dir);
                            return FileVisitResult.CONTINUE;
                        }
                    });
        } catch (IOException e) {
//            LOGGER.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    public void sourcePathWalk(Path source, Integer zipFileLevel, Integer driveCode){
        sourcePathWalk(source, zipFileLevel, null, null, driveCode);
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        try {
            if (SingleSystemProperty.SEARCH_DIR_STACK.size() == 1) {
                SearchPathModel lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.pop();
                Optional<EMFODirectory> opSearchDir = dirService.getMFODirectoryBySearchPathModelDrive(lastPathModel, driveCode);
                if (opSearchDir.isPresent()) {
                    EMFODirectory searchDir = opSearchDir.get();
                    searchDir.setDirectorySearchStatus(1);
                    dirService.persistMFODirectory(searchDir);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }finally {
            dirService.close();
            System.gc();
        }
    }
}
