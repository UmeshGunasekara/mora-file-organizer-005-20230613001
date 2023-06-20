/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 6:55 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
public class DirectoryDto extends BaseDto implements IDto<EMFODirectory>
{
    final static Logger LOGGER = LogManager.getLogger(DirectoryDto.class);

    private String directoryName;
    private String directoryFullPath;
    private DirectoryDto directoryParent;
    private List<DirectoryDto> subDirectories;
    private List<FileDto> files;

    public DirectoryDto(EMFODirectory jpaEntityDirectory){
        if(jpaEntityDirectory.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setId(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityDirectory.getId()));
        }
        this.setCode(jpaEntityDirectory.getCode());
        this.setNote(jpaEntityDirectory.getNote());
        this.setRawCreateUserAccountId(jpaEntityDirectory.getRawCreateUserAccountId());
        this.setRawLastUpdateUserAccountId(jpaEntityDirectory.getRawLastUpdateUserAccountId());
        if(jpaEntityDirectory.getRawCreateDateTime()!=null) {
            this.setRawCreateDateTime(jpaEntityDirectory.getRawCreateDateTime().toLocalDateTime());
        }
        if(jpaEntityDirectory.getRawLastUpdateDateTime()!=null) {
            this.setRawLastUpdateDateTime(jpaEntityDirectory.getRawLastUpdateDateTime().toLocalDateTime());
        }
        this.setRawLastUpdateLogId(jpaEntityDirectory.getRawLastUpdateLogId());
        this.setRawShowStatus(jpaEntityDirectory.getRawShowStatus());
        this.setRawUpdateStatus(jpaEntityDirectory.getRawUpdateStatus());
        this.setRawDeleteStatus(jpaEntityDirectory.getRawDeleteStatus());
        this.setRawActiveStatus(jpaEntityDirectory.getRawActiveStatus());
        this.setExtra01(jpaEntityDirectory.getExtra01());
        this.setExtra02(jpaEntityDirectory.getExtra02());
        this.setExtra03(jpaEntityDirectory.getExtra03());

        this.setDirectoryName(jpaEntityDirectory.getDirectoryName());
        this.setDirectoryFullPath(jpaEntityDirectory.getDirectoryFullPath());
        if(jpaEntityDirectory.getDirectoryParent()!=null){
            this.setDirectoryParent(new DirectoryDto(jpaEntityDirectory.getDirectoryParent()));
        }
//        if(jpaEntityDirectory.getSubDirectories()!=null && !jpaEntityDirectory.getSubDirectories().isEmpty()){
//            this.setSubDirectories(jpaEntityDirectory.getSubDirectories().stream().map(DirectoryDto::new).toList());
//        }
//        if(jpaEntityDirectory.getFiles()!=null && !jpaEntityDirectory.getFiles().isEmpty()){
//            this.setFiles(jpaEntityDirectory.getFiles().stream().map(FileDto::new).toList());
//        }

    }

    @Override
    public EMFODirectory getEntity()
    {
        EMFODirectory jpaEntityDirectory = new EMFODirectory();

        if(this.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            jpaEntityDirectory.setId(uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(this.getId()));
        }
        jpaEntityDirectory.setCode(this.getCode());
        jpaEntityDirectory.setNote(this.getNote());
        jpaEntityDirectory.setRawCreateUserAccountId(this.getRawCreateUserAccountId());
        jpaEntityDirectory.setRawLastUpdateUserAccountId(this.getRawLastUpdateUserAccountId());
        if(this.getRawCreateDateTime()!=null) {
            jpaEntityDirectory.setRawCreateDateTime(Timestamp.valueOf(this.getRawCreateDateTime()));
        }
        if(this.getRawLastUpdateDateTime()!=null) {
            jpaEntityDirectory.setRawLastUpdateDateTime(Timestamp.valueOf(this.getRawLastUpdateDateTime()));
        }
        jpaEntityDirectory.setRawLastUpdateLogId(this.getRawLastUpdateLogId());
        jpaEntityDirectory.setRawShowStatus(this.getRawShowStatus());
        jpaEntityDirectory.setRawUpdateStatus(this.getRawUpdateStatus());
        jpaEntityDirectory.setRawDeleteStatus(this.getRawDeleteStatus());
        jpaEntityDirectory.setRawActiveStatus(this.getRawActiveStatus());
        jpaEntityDirectory.setExtra01(this.getExtra01());
        jpaEntityDirectory.setExtra02(this.getExtra02());
        jpaEntityDirectory.setExtra03(this.getExtra03());

        jpaEntityDirectory.setDirectoryName(this.getDirectoryName());
        jpaEntityDirectory.setDirectoryFullPath(this.getDirectoryFullPath());
        if(this.getDirectoryParent()!=null){
            jpaEntityDirectory.setDirectoryParent(this.getDirectoryParent().getEntity());
        }
//        if(this.getSubDirectories()!=null && !this.getSubDirectories().isEmpty()){
//            jpaEntityDirectory.setSubDirectories(this.getSubDirectories().stream().map(DirectoryDto::getEntity).toList());
//        }
//        if(this.getFiles()!=null && !this.getFiles().isEmpty()){
//            jpaEntityDirectory.setFiles(this.getFiles().stream().map(FileDto::getEntity).toList());
//        }

        return jpaEntityDirectory;
    }

}
