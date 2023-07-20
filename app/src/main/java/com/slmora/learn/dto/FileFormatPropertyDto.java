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
import com.slmora.learn.jpa.entity.EMFOFileFormatProperty;
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
 * <br>1.0          6/22/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
public class FileFormatPropertyDto extends BaseDto implements IDto<EMFOFileFormatProperty>
{
    final static Logger LOGGER = LogManager.getLogger(FileFormatPropertyDto.class);

    private FilePropertyDto fileProperty;
    private FileFormatDto fileFormat;

    public FileFormatPropertyDto(EMFOFileFormatProperty jpaEntityFileFormatProperty){
        this.setId(jpaEntityFileFormatProperty.getId());
        if(jpaEntityFileFormatProperty.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setUuid(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityFileFormatProperty.getId()));
        }
        this.setCode(jpaEntityFileFormatProperty.getCode());
        this.setNote(jpaEntityFileFormatProperty.getNote());
        this.setRawCreateUserAccountId(jpaEntityFileFormatProperty.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityFileFormatProperty.getRawLastUpdateUserAccountId());
        if(jpaEntityFileFormatProperty.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityFileFormatProperty.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityFileFormatProperty.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityFileFormatProperty.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityFileFormatProperty.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityFileFormatProperty.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityFileFormatProperty.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityFileFormatProperty.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityFileFormatProperty.getRawActiveStatus());
        this.setExtra01(jpaEntityFileFormatProperty.getExtra01());
        this.setExtra02(jpaEntityFileFormatProperty.getExtra02());
        this.setExtra03(jpaEntityFileFormatProperty.getExtra03());

        this.setFileFormat(new FileFormatDto(jpaEntityFileFormatProperty.getFileFormat()));
        this.setFileProperty(new FilePropertyDto(jpaEntityFileFormatProperty.getFileProperty()));
    }

    @Override
    public EMFOFileFormatProperty getEntity()
    {
        EMFOFileFormatProperty jpaEntityFileFormatProperty = new EMFOFileFormatProperty();

        if(this.getId()!=null){
            jpaEntityFileFormatProperty.setId(this.getId());
        }else if(this.getUuid()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityFileFormatProperty.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getUuid()));
        }
        jpaEntityFileFormatProperty.setCode(this.getCode());
        jpaEntityFileFormatProperty.setNote(this.getNote());
        jpaEntityFileFormatProperty.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityFileFormatProperty.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityFileFormatProperty.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityFileFormatProperty.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityFileFormatProperty.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityFileFormatProperty.setRawShowStatus(this.getRawShowStatus());
        jpaEntityFileFormatProperty.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityFileFormatProperty.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityFileFormatProperty.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityFileFormatProperty.setExtra01(this.getExtra01());
        jpaEntityFileFormatProperty.setExtra02(this.getExtra02());
        jpaEntityFileFormatProperty.setExtra03(this.getExtra03());

        if(this.getFileFormat()!=null){
            jpaEntityFileFormatProperty.setFileFormat(this.getFileFormat().getEntity());
        }
        if(this.getFileProperty()!=null){
            jpaEntityFileFormatProperty.setFileProperty(this.getFileProperty().getEntity());
        }
        return jpaEntityFileFormatProperty;
    }
}
