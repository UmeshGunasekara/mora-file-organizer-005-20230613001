/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/22/2023 11:07 PM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.controller.MFODirectoryController;

import javax.swing.filechooser.FileSystemView;
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
 * <br>1.0          6/22/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T08 {
    public static void main(String[] args)
    {
//        Path dir = Paths.get("D:");
        Path dir = Paths.get("D:\\MORA\\Video\\ABC\\RRT");
//        Path dir = Paths.get("D:\\MORA\\Video\\ABC\\RRT\\AAA.wwwwttrr");
        System.out.println(dir.getFileName().toString());
        System.out.println(dir.toAbsolutePath().toString());
        System.out.println(dir.getRoot().toString());

        Path parentDir = dir.getParent();
        System.out.println(parentDir.toAbsolutePath().toString());
    }
}
