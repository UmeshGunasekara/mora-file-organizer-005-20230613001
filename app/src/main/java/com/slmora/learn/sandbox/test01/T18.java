/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/1/2023 4:26 PM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.system.property.SingleSystemProperty;

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
public class T18 {
    public static void main(String[] args)
    {
        System.out.println("Hello");

        String pMain = "D:\\TMORA\\ABCD\\ZAGSFD\\A3.txt";

        String p = "D:\\MORA_TEMP\\T";

        System.out.println(SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION);

        String out = pMain.replace(SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION, p);

        System.out.println(out);

        String master = "Hello World \\Baeldung!";
        String target = "\\Baeldung";
        String replacement = "Java";
        String processed = master.replace(target, replacement);

        System.out.println(processed);
    }
}
