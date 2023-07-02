/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/1/2023 3:24 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.jpa.entity.EMFOFile;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;

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
    public void sourcePathWalk(Path source, Integer zipFileLevel, Path zipParent, EMFOFile eFile){
        try{
            Files.walkFileTree(source,
                    new HashSet<FileVisitOption>(Arrays.asList(FileVisitOption.FOLLOW_LINKS)),
                    Integer.MAX_VALUE,
                    new SimpleFileVisitor<>()
                    {


                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        {
                            System.out.printf("Visiting file %s\n", file);
//                            LOGGER.info("Visiting file "+file+"\n");
                            MFOFileController fileController = new MFOFileController();
                            if(zipFileLevel<1) {
                                fileController.addFile(file, 0);
                            }else {
                                fileController.addFile(file, zipFileLevel, eFile, zipParent);
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException e)
                        {
                            System.err.printf("Visiting failed for %s\n", file);
//                            LOGGER.info("Visiting failed for "+file+"\n");
                            return FileVisitResult.SKIP_SUBTREE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir,
                                                                 BasicFileAttributes attrs)
                        {
                            System.out.printf("About to visit directory %s\n", dir);
//                            LOGGER.info("About to visit directory "+dir+"\n");
                            MFODirectoryController directoryController = new MFODirectoryController();
                            if(zipFileLevel<1) {
                                directoryController.addDirectory(dir,0);
                            }else {
                                directoryController.addDirectory(dir, zipFileLevel, eFile, zipParent);
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                        {
                            System.out.println("Post Visit Directory: " + dir);
                            return FileVisitResult.CONTINUE;
                        }
                    });
        } catch (IOException e) {
//            LOGGER.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    public void sourcePathWalk(Path source, Integer zipFileLevel){
        sourcePathWalk(source, zipFileLevel, null, null);
    }
}
