/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:18 PM
 */
package com.slmora.learn.jpa.entity;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.jpa.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private final static MoraLogger LOGGER = MoraLogger.getLogger(EMFOSystemProperty.class);

    @Serial
    private static final long serialVersionUID = -235997167691675905L;

    @Column(name = "system_prop_code")
    @Size(max = 30)
    @NotNull
    private String systemPropCode;

    @Column(name = "system_prop_value")
    @Size(max = 255)
    @NotNull
    private String systemPropValue;

    @Column(name = "system_prop_modify_ind")
    @NotNull
    private Integer systemPropModifyIND;

    @Column(name = "system_prop_value_separator")
    @Size(max = 1)
    private Character systemPropValueSeparator;
}
