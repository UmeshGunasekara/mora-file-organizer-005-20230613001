/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/29/2023 11:33 PM
 */
package com.slmora.learn.sandbox.test01;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
 * <br>1.0          6/29/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T13 {
    private static final char[] HEX_UPPER = "0123456789ABCDEF".toCharArray();
    private static final char[] HEX_LOWER = "0123456789abcdef".toCharArray();

    public static void main(String[] args)
    {
        String s1 = "1234567890";
        byte[] b1 = s1.getBytes(StandardCharsets.UTF_8);

        System.out.println(s1);
        System.out.println( Base64.getEncoder().encodeToString(b1));
        System.out.println(new String(b1,StandardCharsets.UTF_8));

        T13 t = new T13();

        byte[] b2 = t.convertStringToUnHexByteArrayWithBitwiseSift(s1,true);

        System.out.println(new String(b2,StandardCharsets.UTF_8));
    }

    public byte[] convertStringToUnHexByteArrayWithBitwiseSift(String inputString, boolean lowercase) {

        char[] HEX_ARRAY = lowercase ? HEX_LOWER : HEX_UPPER;

        int nChars = inputString.length();

        if (nChars % 2 != 0) {
            throw new IllegalArgumentException(
                    "Hex-encoded string must have an even number of characters");
        }

        byte[] result = new byte[nChars / 2];                                  // 1 hex = 2 char

        for (int i = 0; i < nChars; i += 2) {                                  // step 2, 1 hex = 2 char
            int msb = Character.digit(inputString.charAt(i), 16);                         // char -> hex, base16
            int lsb = Character.digit(inputString.charAt(i + 1), 16);

            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException(
                        "Detected a Non-hex character at " + (i + 1) + " or " + (i + 2) + " position");
            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        return result;

    }
}
