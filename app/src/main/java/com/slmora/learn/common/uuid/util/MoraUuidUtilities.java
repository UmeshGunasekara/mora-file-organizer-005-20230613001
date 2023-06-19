/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/13/2023 9:18 PM
 */
package com.slmora.learn.common.uuid.util;

import com.slmora.learn.common.hex.util.MoraHexUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * This Class created for UUID utilities
 * Methods
 * <ul>
 *      <li>public void printUUIDDetails(UUID uuid) throws UnsupportedOperationException</li>
 *      <li>public String removeHyphensFromUUID(UUID uuid)</li>
 *      <li>public String addingHyphensToUUID(String uuidString)</li>
 *      <li>public String getUniqueStringUUID(boolean withHyphens)</li>
 *      <li>public String getOrderedUUIDString(UUID uuid)</li>
 *      <li>public String getUniqueStringUUID(boolean withHyphens)</li>
 *      <li>public String getUniqueStringUUID(boolean withHyphens)</li>
 *      <li>public String getUniqueStringUUID(boolean withHyphens)</li>
 * </ul>
 *
 * @since   1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          6/13/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MoraUuidUtilities {
    final static Logger LOGGER = LogManager.getLogger(MoraUuidUtilities.class);

    private volatile SecureRandom numberGenerator = null;
    private final long MSB = 0x8000000000000000L;

    /**
     * <p>Read Given UUID and print properties of UUID</p>
     * <ul>
     *      <li>UUID String</li>
     *      <li>UUID Variant</li>
     *      <li>UUID Version</li>
     *      <li>UUID Most Significant 64 Bits</li>
     *      <li>UUID Last Significant 64 Bits</li>
     *      <li>UUID Time Stamp</li>
     *      <li>UUID Clock Sequence</li>
     *      <li>UUID Node</li>
     * </ul>
     * @param uuid                              Input UUID Object for analyze
     * @throws UnsupportedOperationException    <code>long uuidTimeStamp =  uuid.timestamp();</code> will throw
     *                                          UnsupportedOperationException with compatibility issue
     * @since                                   1.0
     */
    public void printUUIDDetails(UUID uuid) throws UnsupportedOperationException{
        LOGGER.info("printUUIDDetails() is called with uuid = "+uuid);
        System.out.println("UUID String : "+uuid.toString());
        System.out.println("UUID Variant : "+uuid.variant());
        System.out.println("UUID Version : "+uuid.version());
        System.out.println("UUID Most Significant 64 Bits : "+uuid.getMostSignificantBits());
        System.out.println("UUID Last Significant 64 Bits : "+uuid.getLeastSignificantBits());
        long uuidTimeStamp =  uuid.timestamp();
        if(uuidTimeStamp != 0L){
            System.out.println("UUID Time Stamp : "+ uuidTimeStamp);
            System.out.println("UUID Clock Sequence : "+uuid.clockSequence());
            System.out.println("UUID Node : "+uuid.node());
        }else{
            System.out.println("UUID Time Stamp : Give UUID is not an time based one, No Time Stamp");
            System.out.println("UUID Clock Sequence : Give UUID is not an time based one, No Clock Sequence");
            System.out.println("UUID Node : Give UUID is not an time based one, No Node");
        }

    }

    /**
     * <p>Will Returns string of given UUID without hyphens</p>
     * <P>Ex:   this will remove all hyphens in uuid as example 123e4567-e89b-12d3-a456-426614174000 will
     *          returns 123e4567e89b12d3a456426614174000</P>
     * @param uuid      Input UUID Object
     * @return          String Object with by removing hyphens from given UUID String
     * @since           1.0
     */
    public String removeHyphensFromUUID(UUID uuid){
        return uuid.toString().replace("-","");
    }

    /**
     * <p>Will Returns string of given UUID by adding hyphens</p>
     * <P>Ex:   this will add hyphens in uuid string as example 123e4567e89b12d3a456426614174000 will
     *          returns 123e4567-e89b-12d3-a456-426614174000</P>
     * @param uuidString    Input String Object contain uuid without hyphens
     * @return              String Object with by adding hyphens from given UUID String
     * @since               1.0
     */
    public String addingHyphensToUUID(String uuidString){
        StringBuilder sb =  new StringBuilder();
        sb.append(uuidString.substring(0,8)+"-")
                .append(uuidString.substring(8,12)+"-")
                .append(uuidString.substring(12,16)+"-")
                .append(uuidString.substring(16,20)+"-")
                .append(uuidString.substring(20));
        return sb.toString();
    }

    /**
     * <p>Generate UUID from SecureRandom values</p>
     * @param withHyphens       Input boolean value for add hyphens
     * @return                  String Object with generate UUID using SecureRandom
     * @since                   1.0
     */
    public String getUniqueStringUUID(boolean withHyphens) {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }
        String uuidWithoutHyphen = Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
        if(withHyphens) {
            return addingHyphensToUUID(uuidWithoutHyphen);
        }else{
            return uuidWithoutHyphen;
        }
    }

    /**
     * <p>Generate ordered UUID from given UUID</p>
     * @param uuid      Input UUID Object for process
     * @return          String object with ordered UUID
     * @since           1.0
     */
    public String getOrderedUUIDString(UUID uuid) {
        String uuidString = uuid.toString();
        StringBuilder sb = new StringBuilder();
        //can replace append(uuidString.substring(14, 18)) by append(uuidString, 14, 18)
        sb.append(uuidString, 14, 18)
                .append(uuidString, 9, 13)
                .append(uuidString, 0, 8)
                .append(uuidString, 19, 23)
                .append(uuidString.substring(24));
        return sb.toString();
    }

    /**
     * Read Given UUID and print properties of UUID
     * @return return console out with UUID analyzed details
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read properties of given UUID and print
     * @Note analyze the given UUID object
     */
    public UUID getUUIDFromOrderedUUIDString(String orderedUUIDString) {
        StringJoiner joiner = new StringJoiner("-");
        joiner
                .add(orderedUUIDString.substring(8,16))
                .add(orderedUUIDString.substring(4,8))
                .add(orderedUUIDString.substring(0,4))
                .add(orderedUUIDString.substring(16,20))
                .add(orderedUUIDString.substring(20));
        return UUID.fromString(joiner.toString());
    }

    /**
     * Read Given UUID and print properties of UUID
     * @return return console out with UUID analyzed details
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read properties of given UUID and print
     * @Note analyze the given UUID object
     */
    public byte[] getOrderedUUIDByteArrayFromUUIDWithApacheCommons(UUID uuid) {
        MoraHexUtilities hexUtils = new MoraHexUtilities();
        return hexUtils.convertStringToUnHexByteArrayWithApacheCommons(getOrderedUUIDString(uuid));
    }

    /**
     * Read Given UUID and print properties of UUID
     * @return return console out with UUID analyzed details
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read properties of given UUID and print
     * @Note analyze the given UUID object
     */
    public UUID getUUIDFromOrderedUUIDByteArrayWithApacheCommons(byte[] orderedUUID) {
        MoraHexUtilities hexUtils = new MoraHexUtilities();
        String orderedUUIDString = hexUtils.convertByteArrayToHexWithApacheCommons(orderedUUID);
        return getUUIDFromOrderedUUIDString(orderedUUIDString);
    }
}
