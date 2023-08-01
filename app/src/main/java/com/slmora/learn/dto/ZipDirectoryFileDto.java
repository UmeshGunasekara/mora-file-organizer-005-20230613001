/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/30/2023 12:43 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFOFilePropertyData;
import com.slmora.learn.jpa.entity.EMFOZipDirectoryFile;
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
 * <br>1.0          6/30/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
public class ZipDirectoryFileDto extends BaseDto implements IDto<EMFOZipDirectoryFile>
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(ZipDirectoryFileDto.class);

    private DirectoryDto directory;
    private FileDto file;

    public ZipDirectoryFileDto(EMFOZipDirectoryFile jpaEntityZipDirectoryFile){
        this.setId(jpaEntityZipDirectoryFile.getId());
        if(jpaEntityZipDirectoryFile.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setUuid(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityZipDirectoryFile.getId()));
        }
        this.setCode(jpaEntityZipDirectoryFile.getCode());
        this.setNote(jpaEntityZipDirectoryFile.getNote());
        this.setRawCreateUserAccountId(jpaEntityZipDirectoryFile.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityZipDirectoryFile.getRawLastUpdateUserAccountId());
        if(jpaEntityZipDirectoryFile.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityZipDirectoryFile.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityZipDirectoryFile.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityZipDirectoryFile.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityZipDirectoryFile.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityZipDirectoryFile.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityZipDirectoryFile.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityZipDirectoryFile.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityZipDirectoryFile.getRawActiveStatus());
        this.setExtra01(jpaEntityZipDirectoryFile.getExtra01());
        this.setExtra02(jpaEntityZipDirectoryFile.getExtra02());
        this.setExtra03(jpaEntityZipDirectoryFile.getExtra03());

        this.setDirectory(new DirectoryDto(jpaEntityZipDirectoryFile.getDirectory()));
        this.setFile(new FileDto(jpaEntityZipDirectoryFile.getFile()));
    }

    @Override
    public EMFOZipDirectoryFile getEntity()
    {
        EMFOZipDirectoryFile jpaEntityZipDirectoryFile = new EMFOZipDirectoryFile();

        if(this.getId()!=null){
            jpaEntityZipDirectoryFile.setId(this.getId());
        }else if(this.getUuid()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityZipDirectoryFile.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getUuid()));
        }
        jpaEntityZipDirectoryFile.setCode(this.getCode());
        jpaEntityZipDirectoryFile.setNote(this.getNote());
        jpaEntityZipDirectoryFile.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityZipDirectoryFile.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityZipDirectoryFile.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityZipDirectoryFile.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityZipDirectoryFile.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityZipDirectoryFile.setRawShowStatus(this.getRawShowStatus());
        jpaEntityZipDirectoryFile.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityZipDirectoryFile.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityZipDirectoryFile.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityZipDirectoryFile.setExtra01(this.getExtra01());
        jpaEntityZipDirectoryFile.setExtra02(this.getExtra02());
        jpaEntityZipDirectoryFile.setExtra03(this.getExtra03());

        if(this.getFile()!=null){
            jpaEntityZipDirectoryFile.setFile(this.getFile().getEntity());
        }
        if(this.getDirectory()!=null){
            jpaEntityZipDirectoryFile.setDirectory(this.getDirectory().getEntity());
        }
        return jpaEntityZipDirectoryFile;
    }
}
