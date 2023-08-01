/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/13/2023 9:20 PM
 */
package com.slmora.learn.common.hex.util;

import com.slmora.learn.common.logging.MoraLogger;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * This Class created for Hex Utilities
 * Methods
 * <ul>
 *      <li>public Double sumOfNumbers(Double... nums)</li>
 *      <li>public Integer sumOfFiveRandomNumbers()</li>
 *      <li>public void getHeapPollution()</li>
 *      <li>static String getHeapPollutionWithVarargs(List<String>... stringLists)</li>
 * </ul>
 *
 * @since   1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          6/13/2023      SLMORA                Initial Code
 * </pre></blockquote>
 *
 * @see <a href="https://mkyong.com/java/how-to-convert-hex-to-ascii-in-java/">mkyong how-to-convert-hex-to-ascii-in-java</a>
 * @see <a href="https://mkyong.com/java/java-how-to-convert-bytes-to-hex/">mkyong java-how-to-convert-bytes-to-hex</a>
 * @see <a href="https://en.wikipedia.org/wiki/Hexadecimal">wikipedia Hexadecimal</a>
 * @see <a href="https://stackoverflow.com/questions/32180069/how-do-i-perform-mysql-unhex-function-in-java">stackoverflow how-do-i-perform-mysql-unhex-function-in-java</a>
 */
public class MoraHexUtilities {
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MoraHexUtilities.class);

    private static final char[] HEX_UPPER = "0123456789ABCDEF".toCharArray();
    private static final char[] HEX_LOWER = "0123456789abcdef".toCharArray();

    /**
     * Read Given property form given property file in project resource
     *
     * @param inputString as String Object with file name of property file
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToHexWithApacheCommons(String inputString) {
        // display in uppercase
        //char[] chars = Hex.encodeHex(inputString.getBytes(StandardCharsets.UTF_8), false);
        // display in lowercase, default
        char[] chars = org.apache.commons.codec.binary.Hex.encodeHex(inputString.getBytes(StandardCharsets.UTF_8));
        String result = String.valueOf(chars);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string input {} and get {}", inputString, result);
        return result;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param bytes as String Object with file name of property file
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithApacheCommons(byte[] bytes) {
        char[] chArray = org.apache.commons.codec.binary.Hex.encodeHex(bytes);
        String result = String.valueOf(chArray);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string and get {}", result);
        return result;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param hexString as String Object with file name of property file
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToUnHexWithApacheCommons(String hexString) {
        String result = "";
        try {
            byte[] bytes = org.apache.commons.codec.binary.Hex.decodeHex(hexString);
            result = new String(bytes, StandardCharsets.UTF_8);
        } catch (DecoderException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string input {} and get {}", hexString, result);
        return result;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param hexString as String Object with file name of property file
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public byte[] convertStringToUnHexByteArrayWithApacheCommons(String hexString) {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex bytes input {}", hexString);
        byte[] bytes = new byte[hexString.length()/2];
        try {
            bytes = org.apache.commons.codec.binary.Hex.decodeHex(hexString);
        } catch (DecoderException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }
        return bytes;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param inputString as String Object with file name of property file
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToHexWithIntegerWrapper(String inputString) {

        StringBuffer hex = new StringBuffer();

        // loop chars one by one
        for (char temp : inputString.toCharArray()) {
            // convert char to int, for char `a` decimal 97
            int decimal = (int) temp;
            // convert int to hex, for decimal 97 hex 61
            hex.append(Integer.toHexString(decimal));
        }
        String result = hex.toString();
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string input {} and get {}", inputString, result);
        return result;

    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param hexString as String Object with file name of property file
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    // Hex -> Decimal -> Char
    public String convertStringToUnHexWithIntegerWrapper(String hexString) {

        StringBuilder sb = new StringBuilder();

        // split into two chars per loop, hexString, 0A, 0B, 0C...
        for (int i = 0; i < hexString.length() - 1; i += 2) {
            String tempInHex = hexString.substring(i, (i + 2));
            //convert hexString to decimal
            int decimal = Integer.parseInt(tempInHex, 16);
            // convert the decimal to char
            sb.append((char) decimal);
        }
        String result = sb.toString();
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to Un Hex string input {} and get {}", hexString, result);
        return result;

    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param bytes as String Object with file name of property file
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithIntegerWrapper(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            int decimal = (int) aByte & 0xff;               // bytes widen to int, need mask, prevent sign extension
            // get last 8 bits
            String hex = Integer.toHexString(decimal);
            if (hex.length() % 2 == 1) {                    // if half hex, pad with zero, e.g \t
                hex = "0" + hex;
            }
            sb.append(hex);
        }
        String result = sb.toString();
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string and get {}", result);
        return result;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param inputString as String Object with file name of property file
     * @param lowercase as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToHexWithBitwiseSift(String inputString, boolean lowercase) {

        char[] HEX_ARRAY = lowercase ? HEX_LOWER : HEX_UPPER;

        byte[] bytes = inputString.getBytes(StandardCharsets.UTF_8);

        // two chars form the hex value.
        char[] hex = new char[bytes.length * 2];

        for (int j = 0; j < bytes.length; j++) {

            // 1 byte = 8 bits,
            // upper 4 bits is the first half of hex
            // lower 4 bits is the second half of hex
            // combine both and we will get the hex value, 0A, 0B, 0C

            int v = bytes[j] & 0xFF;               // byte widened to int, need mask 0xff
            // prevent sign extension for negative number

            hex[j * 2] = HEX_ARRAY[v >>> 4];       // get upper 4 bits

            hex[j * 2 + 1] = HEX_ARRAY[v & 0x0F];  // get lower 4 bits

        }
        String result = new String(hex);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string input {} and get {}", inputString, result);
        return result;

    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param bytes as String Object with file name of property file
     * @param lowercase as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithBitwiseSift(byte[] bytes, boolean lowercase) {

        char[] HEX_ARRAY = lowercase ? HEX_LOWER : HEX_UPPER;

        final int nBytes = bytes.length;
        char[] charArray = new char[2 * nBytes];         //  1 hex contains two chars
        //  hex = [0-f][0-f], e.g 0f or ff

        int j = 0;
        for (byte aByte : bytes) {                    // loop byte by byte

            // 0xF0 = FFFF 0000
            charArray[j++] = HEX_ARRAY[(0xF0 & aByte) >>> 4];    // get the top 4 bits, first half hex char

            // 0x0F = 0000 FFFF
            charArray[j++] = HEX_ARRAY[(0x0F & aByte)];          // get the bottom 4 bits, second half hex char

            // combine first and second half, we get a complete hex
        }

        String result = String.valueOf(charArray);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string and get {}", result);
        return result;

    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public byte[] convertStringToUnHexByteArrayWithBitwiseSift(String inputString, boolean lowercase) {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to Un Hex byte input {}", inputString);

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

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithStringFormat(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(String.format("%02x", aByte));
            // upper case
            // result.append(String.format("%02X", aByte));
        }
        String result = sb.toString();
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string and get {}", result);
        return result;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToHexWithSpringCrypto(String inputString) {
        // display in lowercase, default
        char[] chars = org.springframework.security.crypto.codec.Hex.encode(inputString.getBytes(StandardCharsets.UTF_8));
        String result = String.valueOf(chars);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string input {} and get {}", inputString, result);
        return result;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertByteArrayToHexWithSpringCrypto(byte[] bytes) {
        char[] charArray = org.springframework.security.crypto.codec.Hex.encode(bytes);
        String result = new String(charArray);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex string and get {}", result);
        return result;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String convertStringToUnHexWithSpringCrypto(String hexString) {
        byte[] bytes = org.springframework.security.crypto.codec.Hex.decode(hexString);
        String result = new String(bytes, StandardCharsets.UTF_8);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to Un Hex string input {} and get {}", hexString, result);
        return result;
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public byte[] convertStringToUnHexByteArrayWithSpringCrypto(String hexString) {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to Un Hex byte input {}", hexString);
        byte[] bytes = org.springframework.security.crypto.codec.Hex.decode(hexString);
        return bytes;
    }



    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public void convertToHex(PrintStream out, File file) throws IOException {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Convert to hex input {}", (null!=file)?file.getAbsolutePath():null);
        InputStream is = new FileInputStream(file);

        int bytesCounter =0;
        int value = 0;
        StringBuilder sbHex = new StringBuilder();
        StringBuilder sbText = new StringBuilder();
        StringBuilder sbResult = new StringBuilder();

        while ((value = is.read()) != -1) {
            //convert to hex value with "X" formatter
            sbHex.append(String.format("%02X ", value));

            //If the chracater is not convertable, just print a dot symbol "."
            if (!Character.isISOControl(value)) {
                sbText.append((char)value);
            }else {
                sbText.append(".");
            }

            //if 16 bytes are read, reset the counter,
            //clear the StringBuilder for formatting purpose only.
            if(bytesCounter==15){
                sbResult.append(sbHex).append("      ").append(sbText).append("\n");
                sbHex.setLength(0);
                sbText.setLength(0);
                bytesCounter=0;
            }else{
                bytesCounter++;
            }
        }

        //if still got content
        if(bytesCounter!=0){
            //add spaces more formatting purpose only
            for(; bytesCounter<16; bytesCounter++){
                //1 character 3 spaces
                sbHex.append("   ");
            }
            sbResult.append(sbHex).append("      ").append(sbText).append("\n");
        }

        out.print(sbResult);
        is.close();
    }
}
