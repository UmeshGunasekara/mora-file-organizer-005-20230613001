/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 4:35 PM
 */
package com.slmora.learn.jpa.entity;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.jpa.entity.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

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
 * <br>1.0          6/20/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "MFO_VIDEO_FILE_DATA")
public class EMFOVideoFileData extends BaseEntity
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(EMFOVideoFileData.class);

    @Serial
    private static final long serialVersionUID = -8078770271321169417L;

    @Column(name = "video_frame_rate_per_second", precision = 10, scale = 2)
    private BigDecimal videoFrameRatePerSecond;

    @Column(name = "audio_bit_rate_kbps")
    private Integer audioBitRateKbps;

    @Column(name = "video_duration_seconds")
    private Integer videoDurationSeconds;

    @Column(name = "audio_channels")
    private Integer audioChannels;

    @Column(name = "audio_sampling_rate_hz")
    private Integer audioSamplingRateHz;

    @Column(name = "video_resolution_height")
    private Integer videoResolutionHeight;

    @Column(name = "video_resolution_width")
    private Integer videoResolutionWidth;

    @Column(name = "video_resolution_type")
    private String videoResolutionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id", columnDefinition = "BINARY(16)")
    private EMFOFile file;

}
