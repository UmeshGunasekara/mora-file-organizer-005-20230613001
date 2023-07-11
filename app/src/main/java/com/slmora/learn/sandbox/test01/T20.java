/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/4/2023 6:46 PM
 */
package com.slmora.learn.sandbox.test01;

import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoInfo;
import it.sauronsoftware.jave.VideoSize;

import java.io.File;
import java.io.IOException;
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
 * <br>1.0          7/4/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T20 {
    public static void main(String[] args)
    {
//        Path path = Paths.get("D:\\MORA\\Video\\TM\\2023-07-04_11-00-24_MSI_Security_meeting.mp4");
//        Path path = Paths.get("D:\\MORA\\Video\\TM\\How to make paper umbrella origami.mp4");
//        Path path = Paths.get("F:\\Henry Pham Origami\\Origamic\\How to make paper umbrella origami.mp4");
        Path path = Paths.get("D:\\MORA\\Video\\TM\\Beginner JavaScript Tutorial - 21 - do while.flv");


        System.out.println(path.toAbsolutePath().toString());
        File source = new File(path.toAbsolutePath().toString());
        Encoder encoder = new Encoder();

        try {
            MultimediaInfo info = encoder.getInfo(source);
            VideoInfo vInfo = info.getVideo();
            AudioInfo aInfo = info.getAudio();

            System.out.printf("%s : %s\n","Bit-rate", vInfo.getBitRate());
            System.out.printf("%s : %s\n","Frame Rate frame/seconds", vInfo.getFrameRate());
            VideoSize size = vInfo.getSize();
            System.out.printf("%s : %s\n","Height Pixel", vInfo.getSize().getHeight());
            System.out.printf("%s : %s\n","Width Pixel", vInfo.getSize().getWidth());
            System.out.printf("%s : %s\n","Duration in seconds", info.getDuration());
            System.out.printf("%s : %s\n","Format", info.getFormat());

            System.out.printf("%s : %s\n","Bit-rate kbps", aInfo.getBitRate());
            System.out.printf("%s : %s\n","Channels", aInfo.getChannels());
            System.out.printf("%s : %s\n","Sampling Rate Hz", aInfo.getSamplingRate());
            System.out.printf("%s : %s\n","Decoder", aInfo.getDecoder());


        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
