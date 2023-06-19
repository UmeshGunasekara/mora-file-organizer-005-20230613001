/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 6:55 PM
 */
package com.slmora.learn.dto;

import com.slmora.learn.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
 * <br>1.0          6/14/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DirectoryDto extends BaseDto
{
    final static Logger LOGGER = LogManager.getLogger(DirectoryDto.class);

    private String directoryName;
    private String directoryFullPath;
    private DirectoryDto directoryParent;
    private Collection<DirectoryDto> subDirectories;
    private Collection<FileDto> files;
}
