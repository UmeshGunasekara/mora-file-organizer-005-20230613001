/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 4:28 PM
 */
package com.slmora.learn.common.ds.hibernate.util;

import com.slmora.learn.common.logging.MoraLogger;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp>
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(LocalDateTimeConverter.class);
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert local date time {}", (null!=attribute)?attribute.toString():null);
        if(attribute != null) {
            return Timestamp.valueOf(attribute);
        }
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData)
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert Time Stamp {}", (null!=dbData)?dbData.toString():null);
        if(dbData!=null){
            return dbData.toLocalDateTime();
//            LocalDateTime.ofInstant(new Instant(dbData), ZoneId.of("UTC"));
        }
        return null;
    }
}
