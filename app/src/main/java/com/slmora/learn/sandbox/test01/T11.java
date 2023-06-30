/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 7:20 PM
 */
package com.slmora.learn.sandbox.test01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;

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
public class T11 {
    public static void main(String[] args) throws IOException
    {
        Path file = Paths.get("D:\\SLMORAWorkSpace\\IntelliJProjects\\slmora-io-learn\\mora-file-organizer-005-20230613001\\logs\\2023-06-29\\mora-file-organizer-005-20230613001-info.2023-06-29-1.log");
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

        System.out.println("creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());

        DosFileAttributes attr2 =
                Files.readAttributes(file, DosFileAttributes.class);
        System.out.println("isReadOnly is " + attr2.isReadOnly());
        System.out.println("isHidden is " + attr2.isHidden());
        System.out.println("isArchive is " + attr2.isArchive());
        System.out.println("isSystem is " + attr2.isSystem());
    }
}
