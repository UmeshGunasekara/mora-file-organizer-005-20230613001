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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "MFO_FILE_CATEGORY")
public class EMFOFileCategory extends BaseEntity
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(EMFOFileCategory.class);

    @Serial
    private static final long serialVersionUID = -6682936022582917L;

    @Column(name = "file_category_name")
    @Size(max = 150)
    @NotNull
    private String fileCategoryName;

    @Column(name = "file_category_description")
    @Size(max = 255)
    private String fileCategoryDescription;

    @OneToMany(
            mappedBy = "fileCategory",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Collection<EMFOFile> files = new ArrayList();

    @OneToMany(
            mappedBy = "fileCategory",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Collection<EMFOFileFormat> fileFormats = new ArrayList();

    @PreRemove
    private void preRemove(){
        files.forEach(file -> file.setFileCategory(null));
        fileFormats.forEach(fileFormat -> fileFormat.setFileCategory(null));
    }
}
