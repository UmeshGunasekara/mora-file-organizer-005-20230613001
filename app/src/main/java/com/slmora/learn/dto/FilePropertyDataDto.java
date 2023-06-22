/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/22/2023 9:09 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOFileProperty;
import com.slmora.learn.jpa.entity.EMFOFilePropertyData;
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
public class FilePropertyDataDto extends BaseDto implements IDto<EMFOFilePropertyData>
{
    final static Logger LOGGER = LogManager.getLogger(FilePropertyDataDto.class);

    private String filePropertyValue;
    private String filePropertyValueType;
    private FilePropertyDto fileProperty;
    private FileDto file;

    public FilePropertyDataDto(EMFOFilePropertyData jpaEntityFilePropertyData){
        if(jpaEntityFilePropertyData.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setId(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityFilePropertyData.getId()));
        }
        this.setCode(jpaEntityFilePropertyData.getCode());
        this.setNote(jpaEntityFilePropertyData.getNote());
        this.setRawCreateUserAccountId(jpaEntityFilePropertyData.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityFilePropertyData.getRawLastUpdateUserAccountId());
        if(jpaEntityFilePropertyData.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityFilePropertyData.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityFilePropertyData.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityFilePropertyData.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityFilePropertyData.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityFilePropertyData.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityFilePropertyData.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityFilePropertyData.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityFilePropertyData.getRawActiveStatus());
        this.setExtra01(jpaEntityFilePropertyData.getExtra01());
        this.setExtra02(jpaEntityFilePropertyData.getExtra02());
        this.setExtra03(jpaEntityFilePropertyData.getExtra03());

        this.setFilePropertyValue(jpaEntityFilePropertyData.getFilePropertyValue());
        this.setFilePropertyValueType(jpaEntityFilePropertyData.getFilePropertyValueType());
        this.setFileProperty(new FilePropertyDto(jpaEntityFilePropertyData.getFileProperty()));
        this.setFile(new FileDto(jpaEntityFilePropertyData.getFile()));
    }

    @Override
    public EMFOFilePropertyData getEntity()
    {
        EMFOFilePropertyData jpaEntityFilePropertyData = new EMFOFilePropertyData();

        if(this.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityFilePropertyData.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getId()));
        }
        jpaEntityFilePropertyData.setCode(this.getCode());
        jpaEntityFilePropertyData.setNote(this.getNote());
        jpaEntityFilePropertyData.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityFilePropertyData.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityFilePropertyData.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityFilePropertyData.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityFilePropertyData.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityFilePropertyData.setRawShowStatus(this.getRawShowStatus());
        jpaEntityFilePropertyData.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityFilePropertyData.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityFilePropertyData.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityFilePropertyData.setExtra01(this.getExtra01());
        jpaEntityFilePropertyData.setExtra02(this.getExtra02());
        jpaEntityFilePropertyData.setExtra03(this.getExtra03());

        jpaEntityFilePropertyData.setFilePropertyValue(this.getFilePropertyValue());
        jpaEntityFilePropertyData.setFilePropertyValueType(this.getFilePropertyValueType());
        if(this.getFile()!=null){
            jpaEntityFilePropertyData.setFile(this.getFile().getEntity());
        }
        if(this.getFileProperty()!=null){
            jpaEntityFilePropertyData.setFileProperty(this.getFileProperty().getEntity());
        }
        return null;
    }
}
