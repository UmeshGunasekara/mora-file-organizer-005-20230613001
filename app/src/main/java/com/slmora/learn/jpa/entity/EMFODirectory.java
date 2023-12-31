/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/13/2023 9:07 PM
 */
package com.slmora.learn.jpa.entity;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.jpa.entity.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

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
 * <br>1.0          6/13/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "MFO_DIRECTORY")
@NamedQueries({
        @NamedQuery(name = "EMFODirectory.findByDirectoryFullPath", query = "FROM EMFODirectory WHERE directoryFullPath = :dirFullPath")
})
public class EMFODirectory extends BaseEntity
{

    private final static MoraLogger LOGGER = MoraLogger.getLogger(EMFODirectory.class);

    @Serial
    private static final long serialVersionUID = 7623096498577642513L;

//    @Id
//    @Column(name = "directory_id", columnDefinition = "BINARY(16)")
//    @GeneratedValue(generator = "mora-uuid-generator")
//    @GenericGenerator(name = "mora-uuid-generator",
//            strategy = "com.slmora.learn.common.ds.hibernate.util.MoraUUIDGenerator")
//    private byte[] directoryId;

//    @Column(name = "directory_code")
//    private Integer directoryCode;

    @Column(name = "directory_name")
    @Size(max = 255)
    @NotNull
    private String directoryName;

    @Column(name = "directory_full_path")
    @NotNull
    private String directoryFullPath;

    @Column(name = "directory_full_path_sha_256")
    @Size(max = 255)
    @NotNull
    private String directoryFullPathSha256;

    @Column(name = "directory_text_path")
    @NotNull
    private String directoryTextPath;

    @Column(name = "directory_text_path_sha_256")
    @Size(max = 255)
    @NotNull
    private String directoryTextPathSha256;

    @Column(name = "directory_is_zip", columnDefinition = "TINYINT(1) default '0'")
    @NotNull
    private Integer directoryIsZip=0;

    @Column(name = "directory_level", columnDefinition = "int default '0'")
    @NotNull
    private Integer directoryLevel=0;

    @Column(name = "directory_search_status", columnDefinition = "TINYINT(1) default '0'")
    @NotNull
    private Integer directorySearchStatus=0;

    @Column(name = "directory_drive_code", columnDefinition = "SMALLINT default '0'")
    @NotNull
    private Integer directoryDriveCode=0;

//    @Column(name = "directory_note")
//    private String directoryNote;
//
//    @Column(name = "raw_create_user_account_id", columnDefinition = "int default '0'")
//    @NotNull
//    private Integer rawCreateUserAccountId=0;
//
//    @Column(name = "raw_last_update_user_account_id", columnDefinition = "int default '0'")
//    @NotNull
//    private Integer rawLastUpdateUserAccountId=0;
//
//    @Column(name = "raw_create_date_time", columnDefinition = "int default 'CURRENT_TIMESTAMP'")
//    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull
//    private Timestamp rawCreateDateTime=new Timestamp(System.currentTimeMillis());
//
//    @Column(name = "raw_last_update_date_time", columnDefinition = "int default 'CURRENT_TIMESTAMP'")
//    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull
//    private Timestamp rawLastUpdateDateTime=new Timestamp(System.currentTimeMillis());
//
//    @Column(name = "raw_last_update_log_id", columnDefinition = "int default '0'")
//    @NotNull
//    private Integer rawLastUpdateLogId=0;
//
//    @Column(name = "raw_show_status", columnDefinition = "int default '0'")
//    @NotNull
//    private Integer rawShowStatus=0;
//
//    @Column(name = "raw_update_status", columnDefinition = "int default '0'")
//    @NotNull
//    private Integer rawUpdateStatus=0;
//
//    @Column(name = "raw_delete_status", columnDefinition = "int default '0'")
//    @NotNull
//    private Integer rawDeleteStatus=0;
//
//    @Column(name = "raw_active_status", columnDefinition = "int default '0'")
//    @NotNull
//    private Integer rawActiveStatus=0;
//
//    @Column(name = "extra_01")
//    private String extra01;
//
//    @Column(name = "extra_02")
//    private String extra02;
//
//    @Column(name = "extra_03")
//    private String extra03;

    @ManyToOne
    @JoinColumn(name="directory_parent_id", columnDefinition = "BINARY(16)")
    private EMFODirectory directoryParent;

    @ManyToOne
    @JoinColumn(name="file_zip_id", columnDefinition = "BINARY(16)")
    private EMFOFile fileZip;

    @OneToMany(
            mappedBy = "directoryParent",
            cascade = CascadeType.PERSIST
    )
    private Collection<EMFODirectory> subDirectories = new ArrayList();

    @OneToMany(
            mappedBy = "directory",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Collection<EMFOFile> files = new ArrayList();

    @OneToMany(
            mappedBy = "directory",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Collection<EMFOZipDirectoryFile> zipDirectoryFile = new ArrayList();

    /**
     * Apply ON DELETE SET NULL constrain on the Entity
     * @since                                   1.0
     */
    @PreRemove
    private void preRemove(){
        subDirectories.forEach(dir -> dir.setDirectoryParent(null));
        files.forEach(file -> file.setDirectory(null));
        zipDirectoryFile.forEach(zipDirFile -> zipDirFile.setDirectory(null));
    }

//    /**
//     * Apply ON DELETE SET NULL constrain on the Entity
//     * @since                                   1.0
//     */
//    @PreRemove
//    private void preRemoveFiles(){
//        files.forEach(file -> file.setDirectory(null));
//    }

}