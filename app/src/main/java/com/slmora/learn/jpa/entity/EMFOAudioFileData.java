/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/22/2023 8:34 PM
 */
package com.slmora.learn.jpa.entity;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.jpa.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serial;
import java.math.BigDecimal;

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
 * <br>1.0          6/22/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "MFO_AUDIO_FILE_DATA")
public class EMFOAudioFileData extends BaseEntity
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(EMFOAudioFileData.class);

    @Serial
    private static final long serialVersionUID = 4051090441217195916L;

    @Column(name = "audio_bit_rate_kbps")
    private Integer audioBitRateKbps;

    @Column(name = "audio_duration_seconds")
    private Integer audioDurationSeconds;

    @Column(name = "audio_channels")
    private Integer audioChannels;

    @Column(name = "audio_sampling_rate_hz")
    private Integer audioSamplingRateHz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id", columnDefinition = "BINARY(16)")
    private EMFOFile file;
}
