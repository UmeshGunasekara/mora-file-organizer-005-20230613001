/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/4/2023 6:44 PM
 */
package com.slmora.learn.sandbox.test01;

import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

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
public class T19 {
    public static void main(String[] args)
    {
        Path path = Paths.get("D:\\MORA\\Entertain\\Rainy Jazz Cafe - Slow Jazz Music in Coffee Shop Ambience for Work, Study and Relaxation.mp3");


        System.out.println(path.toAbsolutePath().toString());
        File source = new File(path.toAbsolutePath().toString());
        Encoder encoder = new Encoder();

        try {
            MultimediaInfo info = encoder.getInfo(source);
            AudioInfo aInfo = info.getAudio();

            System.out.printf("%s : %s\n","Bit-rate", aInfo.getBitRate());
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
