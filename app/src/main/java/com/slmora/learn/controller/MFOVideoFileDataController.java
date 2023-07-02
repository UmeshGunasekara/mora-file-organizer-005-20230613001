/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 10:03 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFOVideoFileDataDaoImpl;
import com.slmora.learn.dto.FileDto;
import com.slmora.learn.dto.VideoFileDataDto;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOVideoFileData;
import com.slmora.learn.service.IMFOVideoFileDataService;
import com.slmora.learn.service.impl.MFOVideoFileDataServiceImpl;
import com.slmora.learn.util.EMVideoResolutionType;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoInfo;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
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
public class MFOVideoFileDataController {
    final static Logger LOGGER = LogManager.getLogger(MFOVideoFileDataController.class);

    public void addVideoFileDate(EMFOFile eFile){
        File source = new File(eFile.getFileFullPath());
        addVideoFileDate(eFile,source);
    }

    public void addVideoFileDate(EMFOFile eFile, Path file){
        File source = file.toFile();
        addVideoFileDate(eFile, source);
    }

    public void addVideoFileDate(EMFOFile eFile, File source){
        IMFOVideoFileDataService videoFileDataService = new MFOVideoFileDataServiceImpl(new MFOVideoFileDataDaoImpl());
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

        FileDto fileDto = new FileDto(eFile);
        Encoder encoder = new Encoder();

        try {
            MultimediaInfo info = encoder.getInfo(source);
            VideoInfo vInfo = info.getVideo();

            VideoFileDataDto fileDataDto = new VideoFileDataDto();

            fileDataDto.setAudioBitRateKbps(info.getAudio().getBitRate());
            Float vFrameRate = vInfo.getFrameRate();
            fileDataDto.setVideoFrameRatePerSecond(vFrameRate.doubleValue());
            fileDataDto.setVideoResolutionWidth(vInfo.getSize().getWidth());
            fileDataDto.setVideoResolutionHeight(vInfo.getSize().getHeight());
            Optional<EMVideoResolutionType> opResolutionType = EMVideoResolutionType.stream()
                    .filter(i->fileDataDto.getVideoResolutionHeight().equals(i.getResolutionHeight())&&fileDataDto.getVideoResolutionWidth().equals(i.getResolutionWidth()))
                    .findFirst();
            if(opResolutionType.isPresent()){
                fileDataDto.setVideoResolutionType(opResolutionType.get().getResolutionCommonName());
            }else {
                fileDataDto.setVideoResolutionType(EMVideoResolutionType.ILE_RES_OTHER.getResolutionCommonName());
            }
            Long vDuration = info.getDuration();
            fileDataDto.setVideoDurationSeconds(vDuration.intValue());
            fileDataDto.setAudioChannels(info.getAudio().getChannels());
            fileDataDto.setAudioSamplingRateHz(info.getAudio().getSamplingRate());

            EMFOVideoFileData eVideoFileData = fileDataDto.getEntity();

            eVideoFileData.setFile(eFile);

            eVideoFileData = videoFileDataService.persistMFOVideoFileData(eVideoFileData);

            UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(eVideoFileData.getId());
            LOGGER.info("Added Video File " + fileDto.getFilePath() + " with UUID : " + uuid.toString());
            System.out.println("Added Video File " + fileDto.getFilePath() + " with UUID : " + uuid.toString());

        } catch (InputFormatException e) {
            LOGGER.error("Error with source : "+source);
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        } catch (EncoderException e) {
            LOGGER.error("Error with source : "+source);
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }finally {
            videoFileDataService.close();
        }
    }
}
