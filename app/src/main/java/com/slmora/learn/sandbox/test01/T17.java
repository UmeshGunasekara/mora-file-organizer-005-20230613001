/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/1/2023 4:17 PM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.controller.MoraFileOrganizerWalkingController;

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
 * <br>1.0          7/1/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T17 {
    public static void main(String[] args)
    {
//        String sourcePath = "D:\\MORA_TEMP\\T";
        String sourcePath = "D:\\MORA\\Video";
        MoraFileOrganizerWalkingController walkingController = new MoraFileOrganizerWalkingController();
        walkingController.sourcePathWalk(Paths.get(sourcePath), 0);
    }
}