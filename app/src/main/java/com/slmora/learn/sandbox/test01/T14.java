/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/30/2023 9:53 AM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.controller.MFODirectoryController;
import com.slmora.learn.controller.MFOFileController;

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
 * <br>1.0          6/30/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T14 {
    public static void main(String[] args)
    {
        String sourcePath = "D:\\MORA\\Video\\TM";

        try{
            Files.walkFileTree(Paths.get(sourcePath),
                    new HashSet<FileVisitOption>(Arrays.asList(FileVisitOption.FOLLOW_LINKS)),
                    Integer.MAX_VALUE,
                    new SimpleFileVisitor<Path>()
                    {


                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        {
                            System.out.printf("Visiting file %s\n", file);
//                            LOGGER.info("Visiting file "+file+"\n");
                            if(Files.isReadable(file)){
                                System.out.printf("Read file %s\n", file);
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
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc){
                            System.out.println("Post Visit Directory: "+dir);
                            return FileVisitResult.CONTINUE;
                        }
                    });
        } catch (IOException e) {
//            LOGGER.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }
}
