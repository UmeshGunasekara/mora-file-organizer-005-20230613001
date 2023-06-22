/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 7:10 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFOFileFormat;
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
 * <br>1.0          6/20/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
public class FileFormatDto extends BaseDto implements IDto<EMFOFileFormat>
{
    final static Logger LOGGER = LogManager.getLogger(FileFormatDto.class);

    private String fileFormatName;
    private String fileFormatDescription;
    private List<FileFormatPropertyDto> fileFormatProperties;

    public FileFormatDto(EMFOFileFormat jpaEntityFileFormat){
        if(jpaEntityFileFormat.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setId(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityFileFormat.getId()));
        }
        this.setCode(jpaEntityFileFormat.getCode());
        this.setNote(jpaEntityFileFormat.getNote());
        this.setRawCreateUserAccountId(jpaEntityFileFormat.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityFileFormat.getRawLastUpdateUserAccountId());
        if(jpaEntityFileFormat.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityFileFormat.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityFileFormat.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityFileFormat.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityFileFormat.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityFileFormat.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityFileFormat.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityFileFormat.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityFileFormat.getRawActiveStatus());
        this.setExtra01(jpaEntityFileFormat.getExtra01());
        this.setExtra02(jpaEntityFileFormat.getExtra02());
        this.setExtra03(jpaEntityFileFormat.getExtra03());

        this.setFileFormatName(jpaEntityFileFormat.getFileFormatName());
        this.setFileFormatDescription(jpaEntityFileFormat.getFileFormatDescription());
    }

    @Override
    public EMFOFileFormat getEntity()
    {
        EMFOFileFormat jpaEntityFileFormat = new EMFOFileFormat();

        if(this.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityFileFormat.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getId()));
        }
        jpaEntityFileFormat.setCode(this.getCode());
        jpaEntityFileFormat.setNote(this.getNote());
        jpaEntityFileFormat.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityFileFormat.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityFileFormat.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityFileFormat.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityFileFormat.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityFileFormat.setRawShowStatus(this.getRawShowStatus());
        jpaEntityFileFormat.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityFileFormat.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityFileFormat.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityFileFormat.setExtra01(this.getExtra01());
        jpaEntityFileFormat.setExtra02(this.getExtra02());
        jpaEntityFileFormat.setExtra03(this.getExtra03());

        jpaEntityFileFormat.setFileFormatName(this.getFileFormatName());
        jpaEntityFileFormat.setFileFormatDescription(this.getFileFormatDescription());
//        if(this.getSubDirectories()!=null && !this.getSubDirectories().isEmpty()){
//            jpaEntityDirectory.setSubDirectories(this.getSubDirectories().stream().map(DirectoryDto::getEntity).toList());
//        }
//        if(this.getFiles()!=null && !this.getFiles().isEmpty()){
//            jpaEntityDirectory.setFiles(this.getFiles().stream().map(FileDto::getEntity).toList());
//        }

        return jpaEntityFileFormat;
    }
}
