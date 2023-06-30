/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 7:17 PM
 */
package com.slmora.learn.sandbox.test01;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;

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
public class T10 {
    public  String getCreationDetails(File file)
    {
        try{
            Path p = Paths.get(file.getAbsolutePath());
            BasicFileAttributes view
                    = Files.getFileAttributeView(p, BasicFileAttributeView.class)
                    .readAttributes();
            FileTime fileTime=view.creationTime();
            return (""+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format((fileTime.toMillis())));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String...str){
        System.out.println
                (new T10().getCreationDetails(new File("D:\\SLMORAWorkSpace\\IntelliJProjects\\slmora-io-learn\\mora-file-organizer-005-20230613001\\logs\\2023-06-29\\mora-file-organizer-005-20230613001-info.2023-06-29-1.log")));
    }
}
