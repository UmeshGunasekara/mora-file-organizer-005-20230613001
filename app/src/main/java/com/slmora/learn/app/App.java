/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 7:34 PM
 */
package com.slmora.learn.app;

import com.slmora.learn.common.property.util.MoraAccessProperties;
import com.slmora.learn.controller.MoraFileOrganizerWalkingController;
import com.slmora.learn.model.SourceJsonModel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

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
public class App
{
    final static Logger LOGGER = LogManager.getLogger(App.class);
    public static String PROP_MFO_LOC_JSON;

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args)
    {
//        LOGGER.info("The App main() method is called");
//
//        Calculator cal = new Calculator();
//
//        try {
//            int divide = cal.division(100,2);
//            LOGGER.warn("Warning message - Can make exception with division by 0");
//            System.out.println("Division of 100 and 2 is : "+divide);
//        } catch (Exception e) {
//            LOGGER.error(ExceptionUtils.getStackTrace(e));
//            e.printStackTrace();
//        }

        try {
            Properties dataSourceProperties = new MoraAccessProperties().getAllPropertiesFromResource(
                    "constant.properties");
            if (PROP_MFO_LOC_JSON == null || PROP_MFO_LOC_JSON.isBlank()) {
                PROP_MFO_LOC_JSON = dataSourceProperties.getProperty("MORA.CONSTANT.LOC_JSON");
            }
            MoraFileOrganizerWalkingController walkingController = new MoraFileOrganizerWalkingController();
            Optional<List<SourceJsonModel>> opSourceList = SourceJsonModel.getSourceJsonModelListFromJsonFile(PROP_MFO_LOC_JSON);
            System.out.println();
            opSourceList.ifPresent(list->list.forEach(i ->
                    walkingController.sourcePathWalk(Paths.get(i.getSource()), 0, i.getDrive(), i.getIsskip())
            ));
        }catch (Exception e){
            LOGGER.error(ExceptionUtils.getStackTrace(e));
        }

    }

}
