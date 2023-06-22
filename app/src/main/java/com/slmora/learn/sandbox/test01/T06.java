/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/21/2023 8:46 PM
 */
package com.slmora.learn.sandbox.test01;

import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoInfo;

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
 * <br>1.0          6/21/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T06 {
    public static void main(String[] args)
    {
//        Path path = Paths.get("C:\\Users\\umesh\\Videos\\4K Video Downloader\\edureka!\\What is Git   What is GitHub   Git Tutorial   GitHub Tutorial   Devops Tutorial   Edureka.mp4");

        Path path = Paths.get("D:\\MORA\\Entertain\\Rainy Jazz Cafe - Slow Jazz Music in Coffee Shop Ambience for Work, Study and Relaxation.mp3");


        System.out.println(path.toAbsolutePath().toString());
        File source = new File(path.toAbsolutePath().toString());
        Encoder encoder = new Encoder();

        try {
            MultimediaInfo info = encoder.getInfo(source);
//            VideoInfo vInfo = info.getVideo();
            AudioInfo vInfo = info.getAudio();

            System.out.printf("%s : %s\n","file-Sizee", Files.size(path));

            System.out.printf("%s : %s\n","Bit-rate", vInfo.getBitRate());
//            System.out.printf("%s : %s\n","Frame Rate frame/seconds", vInfo.getFrameRate());
//            System.out.printf("%s : %s\n","Height Pixel", vInfo.getSize().getHeight());
//            System.out.printf("%s : %s\n","Width Pixel", vInfo.getSize().getWidth());
            System.out.printf("%s : %s\n","Duration in seconds", info.getDuration());
            System.out.printf("%s : %s\n","Format", info.getFormat());

            System.out.printf("%s : %s\n","Bit-rate kbps", info.getAudio().getBitRate());
            System.out.printf("%s : %s\n","Channels", info.getAudio().getChannels());
            System.out.printf("%s : %s\n","Sampling Rate Hz", info.getAudio().getSamplingRate());
            System.out.printf("%s : %s\n","Decoder", info.getAudio().getDecoder());


        } catch (EncoderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
