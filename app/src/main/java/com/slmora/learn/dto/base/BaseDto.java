/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 4:03 PM
 */
package com.slmora.learn.dto.base;

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
@AllArgsConstructor(staticName = "of")
public abstract class BaseDto {
    private UUID id;
    private int code;
    private String note;
    private int rawCreateUserAccountId;
    private int rawLastUpdateUserAccountId;
    private LocalDateTime rawCreateDateTime;
    private LocalDateTime rawLastUpdateDateTime;
    private Integer rawLastUpdateLogId=0;
    private Integer rawShowStatus=0;
    private Integer rawUpdateStatus=0;
    private Integer rawDeleteStatus=0;
    private Integer rawActiveStatus=0;
    private String extra01;
    private String extra02;
    private String extra03;
}
