/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/30/2023 12:38 PM
 */
package com.slmora.learn.jpa.entity;

import com.slmora.learn.jpa.entity.common.BaseEntity;
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
 * <br>1.0          6/30/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "MFO_ZIP_DIRECTORY_FILE")
public class EMFOZipDirectoryFile extends BaseEntity
{
    final static Logger LOGGER = LogManager.getLogger(EMFOZipDirectoryFile.class);

    @Serial
    private static final long serialVersionUID = -3592549460482579605L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="directory_id", columnDefinition = "BINARY(16)")
    private EMFODirectory directory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id", columnDefinition = "BINARY(16)")
    private EMFOFile file;
}
