/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/9/2023 10:40 AM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.controller.MFODirectoryController;
import com.slmora.learn.controller.MFOFileController;
import org.apache.commons.lang3.StringUtils;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * <br>1.0          7/9/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T23 {
    public static void main(String[] args)
    {
//        Path p = Paths.get("D:\\Prison Break");

        String sourcePath = "D:\\MORA_TEMP\\T";

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
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException e)
                        {
                            System.err.printf("Visiting failed for %s\n", file);
                            return FileVisitResult.SKIP_SUBTREE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir,
                                                                 BasicFileAttributes attrs)
                        {
                            System.out.printf("About to visit directory %s\n", dir);
                            T23.getDirectoryLevel(dir);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc){
                            System.out.println("Post Visit Directory: "+dir);
                            return FileVisitResult.CONTINUE;
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getDirectoryLevel(Path path){
        String sPath = path.toAbsolutePath().toString();
        Pattern pattern = Pattern.compile("\\\\");
        Matcher matcher = pattern.matcher(sPath);
        System.out.println("===============================================================================");
        System.out.println("Directory Level is : "+ StringUtils.countMatches(sPath,"\\"));
        while (matcher.find()) {
            String group = matcher.group();
            int start = matcher.start();
            int end = matcher.end();
            System.out.println(group + " " + start + " " + end);
        }
        System.out.println("*******************************************************************************");
    }


}
