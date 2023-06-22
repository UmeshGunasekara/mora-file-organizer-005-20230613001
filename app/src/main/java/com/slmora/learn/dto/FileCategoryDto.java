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
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.jpa.entity.EMFOFileCategory;
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
public class FileCategoryDto extends BaseDto implements IDto<EMFOFileCategory>
{
    final static Logger LOGGER = LogManager.getLogger(FileCategoryDto.class);

    private String fileCategoryName;
    private List<FileDto> files;

    public FileCategoryDto(EMFOFileCategory jpaEntityFileCategory){
        if(jpaEntityFileCategory.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setId(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityFileCategory.getId()));
        }
        this.setCode(jpaEntityFileCategory.getCode());
        this.setNote(jpaEntityFileCategory.getNote());
        this.setRawCreateUserAccountId(jpaEntityFileCategory.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityFileCategory.getRawLastUpdateUserAccountId());
        if(jpaEntityFileCategory.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityFileCategory.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityFileCategory.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityFileCategory.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityFileCategory.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityFileCategory.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityFileCategory.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityFileCategory.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityFileCategory.getRawActiveStatus());
        this.setExtra01(jpaEntityFileCategory.getExtra01());
        this.setExtra02(jpaEntityFileCategory.getExtra02());
        this.setExtra03(jpaEntityFileCategory.getExtra03());

        this.setFileCategoryName(jpaEntityFileCategory.getFileCategoryName());
    }

    @Override
    public EMFOFileCategory getEntity()
    {
        EMFOFileCategory jpaEntityFileCategory = new EMFOFileCategory();

        if(this.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityFileCategory.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getId()));
        }
        jpaEntityFileCategory.setCode(this.getCode());
        jpaEntityFileCategory.setNote(this.getNote());
        jpaEntityFileCategory.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityFileCategory.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityFileCategory.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityFileCategory.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityFileCategory.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityFileCategory.setRawShowStatus(this.getRawShowStatus());
        jpaEntityFileCategory.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityFileCategory.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityFileCategory.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityFileCategory.setExtra01(this.getExtra01());
        jpaEntityFileCategory.setExtra02(this.getExtra02());
        jpaEntityFileCategory.setExtra03(this.getExtra03());

        jpaEntityFileCategory.setFileCategoryName(this.getFileCategoryName());
//        if(this.getSubDirectories()!=null && !this.getSubDirectories().isEmpty()){
//            jpaEntityDirectory.setSubDirectories(this.getSubDirectories().stream().map(DirectoryDto::getEntity).toList());
//        }
//        if(this.getFiles()!=null && !this.getFiles().isEmpty()){
//            jpaEntityDirectory.setFiles(this.getFiles().stream().map(FileDto::getEntity).toList());
//        }

        return jpaEntityFileCategory;
    }
}
