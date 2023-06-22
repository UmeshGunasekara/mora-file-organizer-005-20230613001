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
@Table(name = "MFO_FILE_PROPERTY_DATA")
public class EMFOFilePropertyData extends BaseEntity
{
    final static Logger LOGGER = LogManager.getLogger(EMFOFilePropertyData.class);

    @Serial
    private static final long serialVersionUID = 451885092483690521L;

    @Column(name = "file_property_value")
    private String filePropertyValue;

    @Column(name = "file_property_value_type")
    private String filePropertyValueType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_property_id", columnDefinition = "BINARY(16)")
    private EMFOFileProperty fileProperty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id", columnDefinition = "BINARY(16)")
    private EMFOFile file;

}
