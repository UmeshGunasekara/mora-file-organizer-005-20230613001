/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:23 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFOFileCategory;
import com.slmora.learn.jpa.entity.EMFOSystemProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
 * <br>1.0          6/20/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
public class SystemPropertyDto extends BaseDto implements IDto<EMFOSystemProperty>
{
    final static Logger LOGGER = LogManager.getLogger(SystemPropertyDto.class);

    private String systemPropCode;
    private String systemPropValue;
    private Integer systemPropModifyIND;
    private Character systemPropValueSeparator;

    public SystemPropertyDto(EMFOSystemProperty jpaEntitySystemProperty){
        if(jpaEntitySystemProperty.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setId(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntitySystemProperty.getId()));
        }
        this.setCode(jpaEntitySystemProperty.getCode());
        this.setNote(jpaEntitySystemProperty.getNote());
        this.setRawCreateUserAccountId(jpaEntitySystemProperty.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntitySystemProperty.getRawLastUpdateUserAccountId());
        if(jpaEntitySystemProperty.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntitySystemProperty.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntitySystemProperty.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntitySystemProperty.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntitySystemProperty.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntitySystemProperty.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntitySystemProperty.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntitySystemProperty.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntitySystemProperty.getRawActiveStatus());
        this.setExtra01(jpaEntitySystemProperty.getExtra01());
        this.setExtra02(jpaEntitySystemProperty.getExtra02());
        this.setExtra03(jpaEntitySystemProperty.getExtra03());

        this.setSystemPropCode(jpaEntitySystemProperty.getSystemPropCode());
        this.setSystemPropValue(jpaEntitySystemProperty.getSystemPropValue());
        this.setSystemPropModifyIND(jpaEntitySystemProperty.getSystemPropModifyIND());
        this.setSystemPropValueSeparator(jpaEntitySystemProperty.getSystemPropValueSeparator());
    }

    @Override
    public EMFOSystemProperty getEntity()
    {
        EMFOSystemProperty jpaEntitySystemProperty = new EMFOSystemProperty();

        if(this.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntitySystemProperty.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getId()));
        }
        jpaEntitySystemProperty.setCode(this.getCode());
        jpaEntitySystemProperty.setNote(this.getNote());
        jpaEntitySystemProperty.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntitySystemProperty.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntitySystemProperty.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntitySystemProperty.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntitySystemProperty.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntitySystemProperty.setRawShowStatus(this.getRawShowStatus());
        jpaEntitySystemProperty.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntitySystemProperty.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntitySystemProperty.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntitySystemProperty.setExtra01(this.getExtra01());
        jpaEntitySystemProperty.setExtra02(this.getExtra02());
        jpaEntitySystemProperty.setExtra03(this.getExtra03());

        jpaEntitySystemProperty.setSystemPropCode(this.getSystemPropCode());
        jpaEntitySystemProperty.setSystemPropValue(this.getSystemPropValue());
        jpaEntitySystemProperty.setSystemPropModifyIND(this.getSystemPropModifyIND());
        jpaEntitySystemProperty.setSystemPropValueSeparator(this.getSystemPropValueSeparator());

        return jpaEntitySystemProperty;
    }
}
