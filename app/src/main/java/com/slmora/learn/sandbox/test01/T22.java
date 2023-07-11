/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/6/2023 8:16 PM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.common.file.util.MoraFileWriteAccessUtilities;

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
 * <br>1.0          7/6/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T22 {
    public static void main(String[] args)
    {
        Path p = Paths.get("F:\\308tube\\Git and GitHub Version Control Tutorial\\Git and GitHub Version Control Tutorial - Part 2.mp4");
//        Path p = Paths.get("F:\\");
        MoraFileWriteAccessUtilities fileWriteAccessUtilities = new MoraFileWriteAccessUtilities();
        String s = fileWriteAccessUtilities.getNonWindowsPathPatternByPath(p);
        System.out.println(s);
    }
}
