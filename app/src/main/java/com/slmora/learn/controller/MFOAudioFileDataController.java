/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 10:52 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFOAudioFileDataDaoImpl;
import com.slmora.learn.dao.impl.MFOVideoFileDataDaoImpl;
import com.slmora.learn.dto.AudioFileDataDto;
import com.slmora.learn.dto.FileDto;
import com.slmora.learn.dto.VideoFileDataDto;
import com.slmora.learn.jpa.entity.EMFOAudioFileData;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOVideoFileData;
import com.slmora.learn.service.IMFOAudioFileDataService;
import com.slmora.learn.service.IMFOVideoFileDataService;
import com.slmora.learn.service.impl.MFOAudioFileDataServiceImpl;
import com.slmora.learn.service.impl.MFOVideoFileDataServiceImpl;
import com.slmora.learn.util.EMVideoResolutionType;
import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoInfo;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

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
public class MFOAudioFileDataController {
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MFOAudioFileDataController.class);

    public void addAudioFileDate(EMFOFile eFile){
        File source = new File(eFile.getFileFullPath());
        addAudioFileDate(eFile,source);
    }

    public void addAudioFileDate(EMFOFile eFile, Path file){
        File source = file.toFile();
        addAudioFileDate(eFile, source);
    }

    public void addAudioFileDate(EMFOFile eFile, File source){
        IMFOAudioFileDataService audioFileDataService = new MFOAudioFileDataServiceImpl(new MFOAudioFileDataDaoImpl());
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Adding Audio file data with eFile UUID {}, source {}", (null!=eFile)?uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eFile.getId()):null, (null!=source)?source.getAbsolutePath():null);

        FileDto fileDto = new FileDto(eFile);
        Encoder encoder = new Encoder();

        try {
            MultimediaInfo info = encoder.getInfo(source);
            AudioInfo aInfo = info.getAudio();

            AudioFileDataDto fileDataDto = new AudioFileDataDto();

            Long aDuration = info.getDuration();
            fileDataDto.setAudioDurationSeconds(aDuration.intValue());
            if(aInfo!=null) {
                fileDataDto.setAudioBitRateKbps(aInfo.getBitRate());
                fileDataDto.setAudioChannels(aInfo.getChannels());
                fileDataDto.setAudioSamplingRateHz(aInfo.getSamplingRate());
            }

            EMFOAudioFileData eAudioFileData = fileDataDto.getEntity();

            eAudioFileData.setFile(eFile);

            eAudioFileData = audioFileDataService.persistMFOAudioFileData(eAudioFileData);

            UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eAudioFileData.getId());
            LOGGER.info(Thread.currentThread().getStackTrace(), "Added Audio File {} with UUID {}", (null!=fileDto)?fileDto.getFilePath():null, (null!=uuid)?uuid.toString():null);

        } catch (InputFormatException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        } catch (EncoderException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }finally {
            audioFileDataService.close();
        }
    }
}
