/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/30/2023 10:48 AM
 */
package com.slmora.learn.sandbox.test01;

import java.nio.file.Files;
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
 * <br>1.0          6/30/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T15 {
    public static void main(String[] args)
    {
        Path p = Paths.get("D:\\MORA\\Video\\TM\\AA\\A5.txt");
        if(Files.isWritable(p)){
            System.out.println("Can Read");
        }
    }
}
