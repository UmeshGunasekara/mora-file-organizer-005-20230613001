/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 4:03 PM
 */
package com.slmora.learn.dto.base;

import com.slmora.learn.jpa.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

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
public abstract class BaseDto {
    private UUID id;
    private Integer code;
    private String note;
    private int rawCreateUserAccountId;
    private int rawLastUpdateUserAccountId;
    private LocalDateTime rawCreateDateTime;
    private LocalDateTime rawLastUpdateDateTime;
    private int rawLastUpdateLogId=0;
    private int rawShowStatus=0;
    private int rawUpdateStatus=0;
    private int rawDeleteStatus=0;
    private int rawActiveStatus=0;
    private String extra01;
    private String extra02;
    private String extra03;

}
