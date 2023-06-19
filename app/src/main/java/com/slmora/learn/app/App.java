/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 7:34 PM
 */
package com.slmora.learn.app;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args)
    {
        LOGGER.info("The App main() method is called");

        Calculator cal = new Calculator();

        try {
            int divide = cal.division(100,2);
            LOGGER.warn("Warning message - Can make exception with division by 0");
            System.out.println("Division of 100 and 2 is : "+divide);
        } catch (Exception e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }

    }

}
