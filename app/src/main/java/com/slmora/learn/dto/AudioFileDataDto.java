/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/23/2023 11:22 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFOAudioFileData;
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
public class AudioFileDataDto extends BaseDto implements IDto<EMFOAudioFileData>
{
    final static Logger LOGGER = LogManager.getLogger(AudioFileDataDto.class);

    private Integer audioBitRateKbps;
    private Integer audioDurationSeconds;
    private Integer audioChannels;
    private Integer audioSamplingRateHz;
    private FileDto file;

    public AudioFileDataDto(EMFOAudioFileData jpaEntityAudioFileData){
        if(jpaEntityAudioFileData.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setId(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityAudioFileData.getId()));
        }
        this.setCode(jpaEntityAudioFileData.getCode());
        this.setNote(jpaEntityAudioFileData.getNote());
        this.setRawCreateUserAccountId(jpaEntityAudioFileData.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityAudioFileData.getRawLastUpdateUserAccountId());
        if(jpaEntityAudioFileData.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityAudioFileData.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityAudioFileData.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityAudioFileData.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityAudioFileData.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityAudioFileData.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityAudioFileData.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityAudioFileData.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityAudioFileData.getRawActiveStatus());
        this.setExtra01(jpaEntityAudioFileData.getExtra01());
        this.setExtra02(jpaEntityAudioFileData.getExtra02());
        this.setExtra03(jpaEntityAudioFileData.getExtra03());

        this.setAudioBitRateKbps(jpaEntityAudioFileData.getAudioBitRateKbps());
        this.setAudioDurationSeconds(jpaEntityAudioFileData.getAudioDurationSeconds());
        this.setAudioChannels(jpaEntityAudioFileData.getAudioChannels());
        this.setAudioSamplingRateHz(jpaEntityAudioFileData.getAudioSamplingRateHz());
        this.setFile(new FileDto(jpaEntityAudioFileData.getFile()));
    }

    @Override
    public EMFOAudioFileData getEntity()
    {
        EMFOAudioFileData jpaEntityAudioFileData = new EMFOAudioFileData();

        if(this.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityAudioFileData.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getId()));
        }
        jpaEntityAudioFileData.setCode(this.getCode());
        jpaEntityAudioFileData.setNote(this.getNote());
        jpaEntityAudioFileData.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityAudioFileData.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityAudioFileData.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityAudioFileData.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityAudioFileData.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityAudioFileData.setRawShowStatus(this.getRawShowStatus());
        jpaEntityAudioFileData.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityAudioFileData.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityAudioFileData.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityAudioFileData.setExtra01(this.getExtra01());
        jpaEntityAudioFileData.setExtra02(this.getExtra02());
        jpaEntityAudioFileData.setExtra03(this.getExtra03());

        jpaEntityAudioFileData.setAudioBitRateKbps(this.getAudioBitRateKbps());
        jpaEntityAudioFileData.setAudioDurationSeconds(this.getAudioDurationSeconds());
        jpaEntityAudioFileData.setAudioChannels(this.getAudioChannels());
        jpaEntityAudioFileData.setAudioSamplingRateHz(this.getAudioSamplingRateHz());
        if(this.getFile()!=null){
            jpaEntityAudioFileData.setFile(this.getFile().getEntity());
        }
        return jpaEntityAudioFileData;
    }
}
