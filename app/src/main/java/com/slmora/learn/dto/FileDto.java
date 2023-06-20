/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 6:57 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFOFile;
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
 * <br>1.0          6/14/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
public class FileDto  extends BaseDto implements IDto<EMFOFile>
{
    final static Logger LOGGER = LogManager.getLogger(DirectoryDto.class);

    private String fileName;
    private String fileFullPath;
    private DirectoryDto directory;

    public FileDto(EMFOFile jpaEntityFile){
        if(jpaEntityFile.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setId(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityFile.getId()));
        }
        this.setCode(jpaEntityFile.getCode());
        this.setNote(jpaEntityFile.getNote());
        this.setRawCreateUserAccountId(jpaEntityFile.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityFile.getRawLastUpdateUserAccountId());
        if(jpaEntityFile.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityFile.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityFile.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityFile.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityFile.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityFile.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityFile.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityFile.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityFile.getRawActiveStatus());
        this.setExtra01(jpaEntityFile.getExtra01());
        this.setExtra02(jpaEntityFile.getExtra02());
        this.setExtra03(jpaEntityFile.getExtra03());

        this.setFileName(jpaEntityFile.getFileName());
        this.setFileFullPath(jpaEntityFile.getFileFullPath());
        this.setDirectory(new DirectoryDto(jpaEntityFile.getDirectory()));
    }

    @Override
    public EMFOFile getEntity()
    {
        EMFOFile jpaEntityFile = new EMFOFile();

        if(this.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityFile.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getId()));
        }
        jpaEntityFile.setCode(this.getCode());
        jpaEntityFile.setNote(this.getNote());
        jpaEntityFile.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityFile.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityFile.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityFile.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityFile.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityFile.setRawShowStatus(this.getRawShowStatus());
        jpaEntityFile.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityFile.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityFile.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityFile.setExtra01(this.getExtra01());
        jpaEntityFile.setExtra02(this.getExtra02());
        jpaEntityFile.setExtra03(this.getExtra03());

        jpaEntityFile.setFileName(this.getFileName());
        jpaEntityFile.setFileFullPath(this.getFileFullPath());
        if(this.getDirectory()!=null){
            jpaEntityFile.setDirectory(this.getDirectory().getEntity());
        }

        return jpaEntityFile;
    }
}
