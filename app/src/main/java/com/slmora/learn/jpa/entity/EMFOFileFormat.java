/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 4:35 PM
 */
package com.slmora.learn.jpa.entity;

import com.slmora.learn.jpa.entity.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "MFO_FILE_FORMAT")
public class EMFOFileFormat extends BaseEntity
{
    final static Logger LOGGER = LogManager.getLogger(EMFOFileFormat.class);

    @Serial
    private static final long serialVersionUID = -980164999832436355L;

    @Column(name = "file_format_name")
    @NotNull
    private String fileFormatName;

    @Column(name = "file_format_description")
    private String fileFormatDescription;

    @OneToMany(
            mappedBy = "fileFormat",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Collection<EMFOFileFormatProperty> fileFormatProperties = new ArrayList();

    @PreRemove
    private void preRemove(){
        fileFormatProperties.forEach(fileFormatProperty -> fileFormatProperty.setFileFormat(null));
    }
}
