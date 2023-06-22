/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/22/2023 8:55 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFOFileProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.List;

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
 * <br>1.0          6/22/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
public class FilePropertyDto extends BaseDto implements IDto<EMFOFileProperty>
{
    final static Logger LOGGER = LogManager.getLogger(FilePropertyDto.class);

    private String filePropertyName;
    private String filePropertyDescription;
    private List<FileFormatPropertyDto> fileFormatProperties;
    private List<FilePropertyDataDto> filePropertyData;

    public FilePropertyDto(EMFOFileProperty jpaEntityFileProperty){
        if(jpaEntityFileProperty.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setId(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityFileProperty.getId()));
        }
        this.setCode(jpaEntityFileProperty.getCode());
        this.setNote(jpaEntityFileProperty.getNote());
        this.setRawCreateUserAccountId(jpaEntityFileProperty.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityFileProperty.getRawLastUpdateUserAccountId());
        if(jpaEntityFileProperty.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityFileProperty.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityFileProperty.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityFileProperty.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityFileProperty.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityFileProperty.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityFileProperty.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityFileProperty.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityFileProperty.getRawActiveStatus());
        this.setExtra01(jpaEntityFileProperty.getExtra01());
        this.setExtra02(jpaEntityFileProperty.getExtra02());
        this.setExtra03(jpaEntityFileProperty.getExtra03());

        this.setFilePropertyName(jpaEntityFileProperty.getFilePropertyName());
        this.setFilePropertyDescription(jpaEntityFileProperty.getFilePropertyDescription());
    }

    @Override
    public EMFOFileProperty getEntity()
    {
        EMFOFileProperty jpaEntityFileProperty = new EMFOFileProperty();

        if(this.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityFileProperty.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getId()));
        }
        jpaEntityFileProperty.setCode(this.getCode());
        jpaEntityFileProperty.setNote(this.getNote());
        jpaEntityFileProperty.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityFileProperty.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityFileProperty.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityFileProperty.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityFileProperty.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityFileProperty.setRawShowStatus(this.getRawShowStatus());
        jpaEntityFileProperty.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityFileProperty.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityFileProperty.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityFileProperty.setExtra01(this.getExtra01());
        jpaEntityFileProperty.setExtra02(this.getExtra02());
        jpaEntityFileProperty.setExtra03(this.getExtra03());

        jpaEntityFileProperty.setFilePropertyName(this.getFilePropertyName());
        jpaEntityFileProperty.setFilePropertyDescription(this.getFilePropertyDescription());

        return jpaEntityFileProperty;
    }
}
