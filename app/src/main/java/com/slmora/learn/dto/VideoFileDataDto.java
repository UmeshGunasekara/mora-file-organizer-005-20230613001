/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/23/2023 11:00 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOFileFormat;
import com.slmora.learn.jpa.entity.EMFOVideoFileData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;

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
 * <br>1.0          6/23/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
public class VideoFileDataDto extends BaseDto implements IDto<EMFOVideoFileData>
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(VideoFileDataDto.class);

    private Double videoFrameRatePerSecond;
    private Integer audioBitRateKbps;
    private Integer videoDurationSeconds;
    private Integer audioChannels;
    private Integer audioSamplingRateHz;
    private Integer videoResolutionHeight;
    private Integer videoResolutionWidth;
    private String videoResolutionType;
    private FileDto file;

    public VideoFileDataDto(EMFOVideoFileData jpaEntityVideoFileData){
        this.setId(jpaEntityVideoFileData.getId());
        if(jpaEntityVideoFileData.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setUuid(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityVideoFileData.getId()));
        }
        this.setCode(jpaEntityVideoFileData.getCode());
        this.setNote(jpaEntityVideoFileData.getNote());
        this.setRawCreateUserAccountId(jpaEntityVideoFileData.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityVideoFileData.getRawLastUpdateUserAccountId());
        if(jpaEntityVideoFileData.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityVideoFileData.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityVideoFileData.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityVideoFileData.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityVideoFileData.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityVideoFileData.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityVideoFileData.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityVideoFileData.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityVideoFileData.getRawActiveStatus());
        this.setExtra01(jpaEntityVideoFileData.getExtra01());
        this.setExtra02(jpaEntityVideoFileData.getExtra02());
        this.setExtra03(jpaEntityVideoFileData.getExtra03());

        this.setVideoFrameRatePerSecond(jpaEntityVideoFileData.getVideoFrameRatePerSecond().doubleValue());
        this.setAudioBitRateKbps(jpaEntityVideoFileData.getAudioBitRateKbps());
        this.setVideoDurationSeconds(jpaEntityVideoFileData.getVideoDurationSeconds());
        this.setAudioChannels(jpaEntityVideoFileData.getAudioChannels());
        this.setAudioSamplingRateHz(jpaEntityVideoFileData.getAudioSamplingRateHz());
        this.setVideoResolutionHeight(jpaEntityVideoFileData.getVideoResolutionHeight());
        this.setVideoResolutionWidth(jpaEntityVideoFileData.getVideoResolutionWidth());
        this.setVideoResolutionType(jpaEntityVideoFileData.getVideoResolutionType());
        this.setFile(new FileDto(jpaEntityVideoFileData.getFile()));
    }

    @Override
    public EMFOVideoFileData getEntity()
    {
        EMFOVideoFileData jpaEntityVideoFileData = new EMFOVideoFileData();

        if(this.getId()!=null){
            jpaEntityVideoFileData.setId(this.getId());
        }else if(this.getUuid()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityVideoFileData.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getUuid()));
        }
        jpaEntityVideoFileData.setCode(this.getCode());
        jpaEntityVideoFileData.setNote(this.getNote());
        jpaEntityVideoFileData.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityVideoFileData.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityVideoFileData.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityVideoFileData.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityVideoFileData.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityVideoFileData.setRawShowStatus(this.getRawShowStatus());
        jpaEntityVideoFileData.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityVideoFileData.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityVideoFileData.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityVideoFileData.setExtra01(this.getExtra01());
        jpaEntityVideoFileData.setExtra02(this.getExtra02());
        jpaEntityVideoFileData.setExtra03(this.getExtra03());

        if(this.getVideoFrameRatePerSecond()!=null) {
            jpaEntityVideoFileData.setVideoFrameRatePerSecond(new BigDecimal(this.getVideoFrameRatePerSecond(),
                    MathContext.DECIMAL64));
        }
        jpaEntityVideoFileData.setAudioBitRateKbps(this.getAudioBitRateKbps());
        jpaEntityVideoFileData.setVideoDurationSeconds(this.getVideoDurationSeconds());
        jpaEntityVideoFileData.setAudioChannels(this.getAudioChannels());
        jpaEntityVideoFileData.setAudioSamplingRateHz(this.getAudioSamplingRateHz());
        jpaEntityVideoFileData.setVideoResolutionHeight(this.getVideoResolutionHeight());
        jpaEntityVideoFileData.setVideoResolutionWidth(this.getVideoResolutionWidth());
        jpaEntityVideoFileData.setVideoResolutionType(this.getVideoResolutionType());
        if(this.getFile()!=null){
            jpaEntityVideoFileData.setFile(this.getFile().getEntity());
        }
        return jpaEntityVideoFileData;
    }
}
