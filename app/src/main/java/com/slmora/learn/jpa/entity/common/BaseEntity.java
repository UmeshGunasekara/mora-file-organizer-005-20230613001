/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/13/2023 9:06 PM
 */
package com.slmora.learn.jpa.entity.common;

import com.slmora.learn.common.ds.hibernate.util.LocalDateTimeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
@MappedSuperclass
public abstract class BaseEntity implements Serializable
{
    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "mora-uuid-generator")
    @GenericGenerator(name = "mora-uuid-generator",
            strategy = "com.slmora.learn.common.ds.hibernate.util.MoraUUIDGenerator")
    private byte[] id;

    @Column(name = "code")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer code;

    @Column(name = "note")
    private String note;

    @Column(name = "raw_create_user_account_id", columnDefinition = "int default '0'")
    @NotNull
    private Integer rawCreateUserAccountId=0;

    @Column(name = "raw_last_update_user_account_id", columnDefinition = "int default '0'")
    @NotNull
    private Integer rawLastUpdateUserAccountId=0;

    @Column(name = "raw_create_date_time", columnDefinition = "datetime default 'CURRENT_TIMESTAMP'")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Timestamp rawCreateDateTime=new Timestamp(System.currentTimeMillis());

//    @Convert(converter = LocalDateTimeConverter.class)
//    @Column(name = "raw_create_date_time", columnDefinition = "datetime default 'CURRENT_TIMESTAMP'")
//    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull
//    private LocalDateTime rawCreateDateTime=LocalDateTime.now();

    @Column(name = "raw_last_update_date_time", columnDefinition = "datetime default 'CURRENT_TIMESTAMP'")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Timestamp rawLastUpdateDateTime=new Timestamp(System.currentTimeMillis());

    @Column(name = "raw_last_update_log_id", columnDefinition = "int default '0'")
    @NotNull
    private Integer rawLastUpdateLogId=0;

    @Column(name = "raw_show_status", columnDefinition = "int default '0'")
    @NotNull
    private Integer rawShowStatus=0;

    @Column(name = "raw_update_status", columnDefinition = "int default '0'")
    @NotNull
    private Integer rawUpdateStatus=0;

    @Column(name = "raw_delete_status", columnDefinition = "int default '0'")
    @NotNull
    private Integer rawDeleteStatus=0;

    @Column(name = "raw_active_status", columnDefinition = "int default '0'")
    @NotNull
    private Integer rawActiveStatus=0;

    @Column(name = "extra_01")
    private String extra01;

    @Column(name = "extra_02")
    private String extra02;

    @Column(name = "extra_03")
    private String extra03;
}
