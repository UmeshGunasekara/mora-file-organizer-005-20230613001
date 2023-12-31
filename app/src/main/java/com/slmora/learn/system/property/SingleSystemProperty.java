/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/1/2023 9:21 AM
 */
package com.slmora.learn.system.property;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.property.util.MoraAccessProperties;
import com.slmora.learn.model.SearchPathModel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.Stack;

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
 * <br>1.0          7/1/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public final class SingleSystemProperty {
    private final static MoraLogger LOGGER = MoraLogger.getLogger(SingleSystemProperty.class);

    public static String PROP_MFO_ZIP_EXTRACT_DESTINATION;
    public static Stack<SearchPathModel> SEARCH_DIR_STACK = new Stack<>();

    public static Stack<SearchPathModel> SEARCH_FILE_STACK = new Stack<>();

    static {
        setSystemProperties();
    }

    private SingleSystemProperty(){}

    private static void setSystemProperties(){
        try {
            Properties dataSourceProperties = new MoraAccessProperties().getAllPropertiesFromResource(
                    "constant.properties");
            if (PROP_MFO_ZIP_EXTRACT_DESTINATION == null || PROP_MFO_ZIP_EXTRACT_DESTINATION.isBlank()) {
                PROP_MFO_ZIP_EXTRACT_DESTINATION = dataSourceProperties.getProperty("MORA.CONSTANT.ZIP_EXTRACT_PATH");
            }
        }catch (Exception e){
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }
//        PROP_MFO_ZIP_EXTRACT_DESTINATION
    }
}
