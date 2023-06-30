/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/13/2023 9:08 PM
 */
package com.slmora.learn.jpa.entity;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serial;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
 * <br>1.0          6/13/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "MFO_FILE")
public class EMFOFile extends BaseEntity
{

    final static Logger LOGGER = LogManager.getLogger(EMFOFile.class);

    @Serial
    private static final long serialVersionUID = 4954906237766057765L;

    @Column(name = "file_name")
    @Size(max = 150)
    @NotNull
    private String fileName;

    @Column(name = "file_full_path")
    @NotNull
    private String fileFullPath;

    @Column(name = "file_full_path_sha_256")
    @Size(max = 150)
    @NotNull
    private String fileFullPathSha256;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_size_in_bytes", precision = 20, scale = 2)
    private BigDecimal fileSizeInBytes;

    @Column(name = "file_created_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fileCreatedDateTime=new Timestamp(System.currentTimeMillis());

    @Column(name = "file_last_modified_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fileLastModifiedDateTime=new Timestamp(System.currentTimeMillis());

    @Column(name = "file_last_access_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fileLastAccessDateTime=new Timestamp(System.currentTimeMillis());

    @Column(name = "file_is_read_only", columnDefinition = "TINYINT(1)")
    private Integer fileIsReadOnly;

    @Column(name = "file_is_hidden", columnDefinition = "TINYINT(1)")
    private Integer fileIsHidden;

    @Column(name = "file_is_archive", columnDefinition = "TINYINT(1)")
    private Integer fileIsArchive;

    @Column(name = "file_is_system", columnDefinition = "TINYINT(1)")
    private Integer fileIsSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="directory_id", columnDefinition = "BINARY(16)")
    private EMFODirectory directory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_category_id", columnDefinition = "BINARY(16)")
    private EMFOFileCategory fileCategory;

    @OneToMany(
            mappedBy = "file",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Collection<EMFOFilePropertyData> filePropertyData = new ArrayList();

    @OneToMany(
            mappedBy = "file",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Collection<EMFOVideoFileData> videoFileData = new ArrayList();

    @OneToMany(
            mappedBy = "file",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Collection<EMFOAudioFileData> audioFileData = new ArrayList();

    @PreRemove
    private void preRemove(){
        filePropertyData.forEach(fileProperty -> fileProperty.setFile(null));
        videoFileData.forEach(videoFile -> videoFile.setFile(null));
        audioFileData.forEach(audioFile -> audioFile.setFile(null));
    }
}
