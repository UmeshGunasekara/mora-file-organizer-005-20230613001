/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 6:57 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.common.cryptography.hmac.util.EHmacAlgorithm;
import com.slmora.learn.common.cryptography.hmac.util.MoraHMACUtilities;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dto.base.BaseDto;
import com.slmora.learn.dto.base.IDto;
import com.slmora.learn.jpa.entity.EMFOFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private String fileFullPathSha256;
    private String fileExtension;
    private Double fileSizeInBytes;
    private LocalDateTime fileCreatedDateTime;
    private LocalDateTime fileLastModifiedDateTime;
    private LocalDateTime fileLastAccessDateTime;
    private Integer fileIsReadOnly;
    private Integer fileIsHidden;
    private Integer fileIsArchive;
    private Integer fileIsSystem;
    private Integer fileIsReadable;
    private Integer fileIsWritable;
    private Integer fileIsExecutable;
    private Integer fileIsZip=0;
    private DirectoryDto directory;
    private FileCategoryDto fileCategory;
    private Path filePath;

    private List<FilePropertyDataDto> filePropertyData;
    private List<VideoFileDataDto> videoFileData;
    private List<AudioFileDataDto> audioFileData;
    private List<ZipDirectoryFileDto> zipDirectoryFile;
    private List<FileDto> subZipFiles;

    public FileDto(Path file) throws NoSuchAlgorithmException, InvalidKeyException, IOException
    {
        this.setFilePath(file);

        this.setFileName(file.getFileName().toString());
        this.setFileFullPath(file.toAbsolutePath().toString());
        this.setFileExtension(FilenameUtils.getExtension(this.getFileName()));
        this.setFileFullPathSha256BytFileFullPath();
        this.setFileIsZip(0);
        setBasicFileAttributes(file);
        setDosFileAttributes(file);
        setFileAccessAttributes(file);

    }

    public FileDto(Path file, Path dbFile, Integer zipFileLevel) throws NoSuchAlgorithmException, InvalidKeyException, IOException
    {
        this.setFilePath(dbFile);

        this.setFileName(dbFile.getFileName().toString());
        this.setFileFullPath(dbFile.toAbsolutePath().toString());
        this.setFileExtension(FilenameUtils.getExtension(this.getFileName()));
        this.setFileFullPathSha256BytFileFullPath();
        this.setFileIsZip(zipFileLevel);
        setBasicFileAttributes(file);
        setDosFileAttributes(file);
        setFileAccessAttributes(file);

    }

    public FileDto(String filePath) throws NoSuchAlgorithmException, IOException, InvalidKeyException
    {
        this(Paths.get(filePath));
    }

    public FileDto(EMFOFile jpaEntityFile){
        if(jpaEntityFile.getId()!=null){
            MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
            this.setId(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(jpaEntityFile.getId()));
        }
        this.setFilePath(Paths.get(jpaEntityFile.getFileFullPath()));
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
        this.setFileFullPathSha256(jpaEntityFile.getFileFullPathSha256());
        this.setFileExtension(jpaEntityFile.getFileExtension());
        this.setFileSizeInBytes(jpaEntityFile.getFileSizeInBytes().doubleValue());
        if(jpaEntityFile.getFileCreatedDateTime()!=null) {
            this.setFileCreatedDateTime(jpaEntityFile.getFileCreatedDateTime().toLocalDateTime());
        }
        if(jpaEntityFile.getFileLastModifiedDateTime()!=null) {
            this.setFileLastModifiedDateTime(jpaEntityFile.getFileLastModifiedDateTime().toLocalDateTime());
        }
        if(jpaEntityFile.getFileLastAccessDateTime()!=null) {
            this.setFileLastAccessDateTime(jpaEntityFile.getFileLastAccessDateTime().toLocalDateTime());
        }
        this.setFileIsReadOnly(jpaEntityFile.getFileIsReadOnly());
        this.setFileIsHidden(jpaEntityFile.getFileIsHidden());
        this.setFileIsArchive(jpaEntityFile.getFileIsArchive());
        this.setFileIsSystem(jpaEntityFile.getFileIsSystem());
        this.setFileIsReadable(jpaEntityFile.getFileIsReadable());
        this.setFileIsWritable(jpaEntityFile.getFileIsWritable());
        this.setFileIsExecutable(jpaEntityFile.getFileIsExecutable());
        this.setFileIsZip(jpaEntityFile.getFileIsZip());
        this.setDirectory(new DirectoryDto(jpaEntityFile.getDirectory()));
        this.setFileCategory(new FileCategoryDto(jpaEntityFile.getFileCategory()));
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

        if(this.getFileFullPathSha256()==null){
            try {
                this.setFileFullPathSha256BytFileFullPath();
            } catch (NoSuchAlgorithmException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            } catch (InvalidKeyException e) {
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            }
            jpaEntityFile.setFileFullPathSha256(this.getFileFullPathSha256());
        }else{
            jpaEntityFile.setFileFullPathSha256(this.getFileFullPathSha256());
        }
        jpaEntityFile.setFileExtension(this.getFileExtension());
        jpaEntityFile.setFileSizeInBytes(new BigDecimal(this.getFileSizeInBytes(), MathContext.DECIMAL64));
        if(this.getFileCreatedDateTime()!=null) {
            jpaEntityFile.setFileCreatedDateTime(Timestamp.valueOf(this.getFileCreatedDateTime()));
        }
        if(this.getFileLastModifiedDateTime()!=null) {
            jpaEntityFile.setFileLastModifiedDateTime(Timestamp.valueOf(this.getFileLastModifiedDateTime()));
        }
        if(this.getFileLastAccessDateTime()!=null) {
            jpaEntityFile.setFileLastAccessDateTime(Timestamp.valueOf(this.getFileLastAccessDateTime()));
        }
        jpaEntityFile.setFileIsReadOnly(this.getFileIsReadOnly());
        jpaEntityFile.setFileIsHidden(this.getFileIsHidden());
        jpaEntityFile.setFileIsArchive(this.getFileIsArchive());
        jpaEntityFile.setFileIsSystem(this.getFileIsSystem());
        jpaEntityFile.setFileIsReadable(this.getFileIsReadable());
        jpaEntityFile.setFileIsWritable(this.getFileIsWritable());
        jpaEntityFile.setFileIsExecutable(this.getFileIsExecutable());
        jpaEntityFile.setFileIsZip(this.getFileIsZip());
        if(this.getDirectory()!=null){
            jpaEntityFile.setDirectory(this.getDirectory().getEntity());
        }
        if(this.getFileCategory()!=null){
            jpaEntityFile.setFileCategory(this.getFileCategory().getEntity());
        }

        return jpaEntityFile;
    }

    public void setFileFullPathSha256BytFileFullPath() throws NoSuchAlgorithmException, InvalidKeyException
    {
        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();
        this.setFileFullPathSha256(hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(), this.getFileFullPath(), this.getFileName()));
    }

    private void setDosFileAttributes(Path file) throws IOException
    {
        DosFileAttributes dosAttr = Files.readAttributes(file, DosFileAttributes.class);
        this.setFileIsReadOnly(dosAttr.isReadOnly() ? 1 : 0);
        this.setFileIsHidden(dosAttr.isHidden() ? 1 : 0);
        this.setFileIsArchive(dosAttr.isArchive() ? 1 : 0);
        this.setFileIsSystem(dosAttr.isSystem() ? 1 : 0);
    }

    private void setBasicFileAttributes(Path file) throws IOException
    {
        //https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html
        BasicFileAttributes basicAttr = Files.readAttributes(file, BasicFileAttributes.class);
        Long fileSize = basicAttr.size();
        this.setFileSizeInBytes(fileSize.doubleValue());
        this.setFileCreatedDateTime(LocalDateTime.ofInstant(basicAttr.creationTime().toInstant(),
                ZoneId.systemDefault()));
        this.setFileLastModifiedDateTime(LocalDateTime.ofInstant(basicAttr.lastModifiedTime()
                .toInstant(), ZoneId.systemDefault()));
        this.setFileLastAccessDateTime(LocalDateTime.ofInstant(basicAttr.lastAccessTime().toInstant(),
                ZoneId.systemDefault()));
    }

    private void setFileAccessAttributes(Path file) throws IOException
    {
        this.setFileIsReadable(Files.isReadable(file) ? 1 : 0);
        this.setFileIsWritable(Files.isWritable(file) ? 1 : 0);
        this.setFileIsExecutable(Files.isExecutable(file) ? 1 : 0);
    }
}
