/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/5/2023 8:04 PM
 */
package com.slmora.learn.sandbox.test01;

import org.apache.commons.lang3.SystemUtils;

import java.nio.file.Path;
import java.nio.file.Paths;

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
 * <br>1.0          7/5/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T21 {
    public static void main(String[] args)
    {
        Path path = Paths.get("D:\\Prison Break\\Prison.Break.S02.Season.2.Complete.720p.BluRay.x265-HaxxOr\\Prison Break S02E05 720p BluRay ReEnc DeeJayAhmed eng.srt");

        System.out.println(path.toAbsolutePath().toString());


        String stringWinPath = "";

        System.out.println(path.toAbsolutePath().toAbsolutePath().toString().replace("\\","/"));

        System.out.println(path.getRoot().toAbsolutePath().toString());

        System.out.println(path.getFileSystem().getSeparator());

        System.out.println(SystemUtils.IS_OS_WINDOWS);

        if(path.getFileSystem().getSeparator().equals("/")){

        }
    }
}
