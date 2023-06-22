/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/13/2023 9:08 PM
 */
package com.slmora.learn.jpa.entity;

import com.slmora.learn.jpa.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serial;
import java.math.BigDecimal;
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
    @NotNull
    private String fileName;

    @Column(name = "file_full_path")
    @NotNull
    private String fileFullPath;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_size_in_mb", precision = 15, scale = 2)
    private BigDecimal fileSizeInMB;

    @Column(name = "file_created_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Timestamp fileCreatedDateTime=new Timestamp(System.currentTimeMillis());

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="directory_id", columnDefinition = "BINARY(16)")
    private EMFODirectory directory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_category_id", columnDefinition = "BINARY(16)")
    private EMFOFileCategory fileCategory;
}
