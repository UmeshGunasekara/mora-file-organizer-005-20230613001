/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:18 PM
 */
package com.slmora.learn.jpa.entity;

import com.slmora.learn.jpa.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
 * <br>1.0          6/20/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "MFO_FILE_CATEGORY")
public class EMFOSystemProperty extends BaseEntity
{
    final static Logger LOGGER = LogManager.getLogger(EMFOSystemProperty.class);

    @Serial
    private static final long serialVersionUID = -235997167691675905L;

    @Column(name = "system_prop_code")
    @NotNull
    private String systemPropCode;

    @Column(name = "system_prop_value")
    @NotNull
    private String systemPropValue;

    @Column(name = "system_prop_modify_ind")
    @NotNull
    private Integer systemPropModifyIND;

    @Column(name = "system_prop_value_separator")
    @NotNull
    private Character systemPropValueSeparator;
}
