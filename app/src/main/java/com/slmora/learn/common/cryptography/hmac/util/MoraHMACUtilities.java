/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/26/2023 11:57 AM
 */
package com.slmora.learn.common.cryptography.hmac.util;

import com.slmora.learn.common.hex.util.MoraHexUtilities;
import com.slmora.learn.common.logging.MoraLogger;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  This Class created for java security cryptography Utilities for has value (Hash-based Message Authentication Code - HMAC)
 *  Code<br>
 *  1 - {@link SecretKeySpec}<br>
 *  2 - {@link Mac}<br>
 *  3 - {@link Mac#getInstance(String)}<br>
 *  4 - {@link Mac#init(Key)}<br>
 *  5 - {@link Mac#doFinal(byte[])}<br>
 *  6 - {@link MoraHexUtilities#convertByteArrayToHexWithApacheCommons(byte[])}
 *  7 - {@link HmacUtils}<br>
 *  8 - {@link HMac}<br>
 *  9 - {@link DigestUtils}<br>
 *  Methods
 *  <ul>
 *       <li>{@link #hmacStringByMacUsingAlgorithmKey_156(String, byte[], String)}</li>
 *       <li>{@link #hmacStringByMacUsingAlgorithmKey_156(String, String, String, Charset)}</li>
 *       <li>{@link #hmacStringByMacUsingAlgorithmKey_156(String, String, String)}</li>
 *       <li>{@link #hmacStringByHmacUtilsUsingAlgorithmKey_7(String, String, String)}</li>
 *       <li>{@link #hmacStringByBouncycastleHmacUsingDigestKey_86(Digest, byte[], String)}</li>
 *       <li>{@link #getDigestByBouncycastle(String)}</li>
 *       <li>{@link #hmacStringByBouncycastleHmacUsingAlgorithmKey_86(String, String, String)}</li>
 *       <li>{@link #hmacStringByBouncycastleHmacUsingAlgorithmKey_86(String, String, Charset, String)}</li>
 *       <li>{@link #hmacStringByCommonsDigestUtilsUsingAlgorithm_9(String, byte[])}</li>
 *       <li>{@link #hmacStringByCommonsDigestUtilsUsingAlgorithm_9(String, String)}</li>
 *       <li>{@link #hmacStringByCommonsDigestUtilsUsingAlgorithm_9(String, FileInputStream)}</li>
 *  </ul>
 *
 * @since   1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          6/26/2023      SLMORA                Initial Code
 * </pre></blockquote>
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html">Java Security Standard Algorithm Names</a>
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc2104">HMAC: Keyed-Hashing for Message Authentication</a>
 * @see <a href="https://www.baeldung.com/java-hmac">HMAC in Java</a>
 */
public class MoraHMACUtilities {

    private final static MoraLogger LOGGER = MoraLogger.getLogger(MoraHMACUtilities.class);

    /**
     * Generate hasmac (encrypted checksum) string with {@link SecretKeySpec} and {@link Mac#getInstance(String)}, {@link Mac#init(Key)} and
     * {@link Mac#doFinal(byte[])} for given {@code data} array and {@code key} for secure key generation and use
     * {@link MoraHexUtilities#convertByteArrayToHexWithApacheCommons(byte[])} for convert byte array to Hex String
     * with given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code algorithm}  like "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512", "HmacSHA512/224", "HmacSHA512/256", "HmacSHA3-224",
     * "HmacSHA3-256", "HmacSHA3-384" and "HmacSHA3-512"
     * @param algorithm     the hashing algorithm in String
     * @param data          the byte data array for generate hmac.
     * @param key           the Key for secret key generation.
     * @return              generated encrypted checksum
     * @throws NoSuchAlgorithmException the provided algorithm is not in  RFC 2104 hashing algorithm list
     * @throws InvalidKeyException
     * @since               1.0
     *
     * @see #hmacStringByMacUsingAlgorithmKey_156(String, String, String, Charset)
     */
    public String hmacStringByMacUsingAlgorithmKey_156(String algorithm, byte[] data, String key)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        MoraHexUtilities hexUtilities = new MoraHexUtilities();

        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(secretKeySpec);
        byte[] hmacByte = mac.doFinal(data);
        String result = hexUtilities.convertByteArrayToHexWithApacheCommons(hmacByte);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "HMAC String :{}, generated with Algorithm:{} and Key:{}", result, algorithm, key);
        return result;
    }

    /**
     * Generate hasmac (encrypted checksum) string with {@link #hmacStringByMacUsingAlgorithmKey_156(String, byte[], String)}
     * for given {@code data} array and {@code key} for secure key generation with given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code algorithm}  like "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512", "HmacSHA512/224", "HmacSHA512/256", "HmacSHA3-224",
     * "HmacSHA3-256", "HmacSHA3-384" and "HmacSHA3-512" and given {@code charset} for byte encoding and if {@code charset} is null internally using
     * UTF-8 as default charset.
     * @param algorithm     the hashing algorithm as String.
     * @param key           the Key for secret key generation.
     * @param data          the String data for generate hmac.
     * @param charset       the Charset encoding type.
     * @return              generated encrypted checksum
     * @throws NoSuchAlgorithmException the provided algorithm is not in  RFC 2104 hashing algorithm list
     * @throws InvalidKeyException
     * @since               1.0
     *
     * @see #hmacStringByMacUsingAlgorithmKey_156(String, String, String)
     */
    public String hmacStringByMacUsingAlgorithmKey_156(String algorithm, String key, String data, Charset charset)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        byte byteData[] = data.getBytes(charset==null? StandardCharsets.UTF_8:charset);
        String result = hmacStringByMacUsingAlgorithmKey_156(algorithm, byteData, key);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "HMAC String :{}, generated with Algorithm:{} and Key:{} for Data:{}", result, algorithm, key, data);
        return result;
    }

    /**
     * Generate hasmac (encrypted checksum) string with {@link #hmacStringByMacUsingAlgorithmKey_156(String, String, String, Charset)}
     * for given {@code data} array and {@code key} for secure key generation with given The HmacSHA* algorithms as
     * defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication” in {@code algorithm}  like "HmacMD5", "HmacSHA1",
     * "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512", "HmacSHA512/224", "HmacSHA512/256", "HmacSHA3-224",
     * "HmacSHA3-256", "HmacSHA3-384" and "HmacSHA3-512" and for byte encoding internally using
     *  {@code StandardCharsets.UTF_8} as default charset.
     * @param algorithm     the hashing algorithm as String.
     * @param data          the String data for generate hmac.
     * @param key           the Key for secret key generation.
     * @return              generated encrypted checksum
     * @throws NoSuchAlgorithmException the provided algorithm is not in  RFC 2104 hashing algorithm list
     * @throws InvalidKeyException
     * @since               1.0
     *
     */
    public String hmacStringByMacUsingAlgorithmKey_156(String algorithm, String data, String key)
            throws NoSuchAlgorithmException, InvalidKeyException
    {
        String result = hmacStringByMacUsingAlgorithmKey_156(algorithm, key, data, StandardCharsets.UTF_8);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "HMAC String :{}, generated with Algorithm:{} and Key:{} for Data:{}", result, algorithm, key, data);
        return result;
    }


    /**
     * Generate hasmac (encrypted checksum) string with {@link HmacUtils#hmacHex(File)} this is internally using {@link SecretKeySpec}
     * and {@link Mac#getInstance(String)}, {@link Mac#init(Key)} and {@link Mac#doFinal(byte[])} for given {@code data} array
     * and {@code key} for secure key generation and {@link HmacUtils#hmacHex(File)} is internally using same process
     * like {@link MoraHexUtilities#convertByteArrayToHexWithApacheCommons(byte[])} for convert byte array to Hex String
     * with given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code algorithm}  like "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512"
     * for data encoding internally use {@code StandardCharsets.UTF_8}.
     * @param algorithm     the hashing algorithm as String.
     * @param data          the String data for generate hmac.
     * @param key           the Key for secret key generation.
     * @since               1.0
     *
     * @see #hmacStringByMacUsingAlgorithmKey_156(String, String, String, Charset)
     */
    public static String hmacStringByHmacUtilsUsingAlgorithmKey_7(String algorithm, String data, String key) {
        String result = new HmacUtils(algorithm, key).hmacHex(data);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "HMAC String :{}, generated with Algorithm:{} and Key:{} for Data:{}", result, algorithm, key, data);
        return result;
    }

    /**
     * Generate hasmac (encrypted checksum) string with {@link HMac}, {@link KeyParameter}, {@link HMac#init(CipherParameters)},
     * {@link HMac#update(byte[], int, int)} and {@link HMac#doFinal(byte[], int)} for given {@code data} array
     * and {@code key} for key parameter generation and use {@link MoraHexUtilities#convertByteArrayToHexWithApacheCommons(byte[])}
     * for convert byte array to Hex String with given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code digest} can generate using {@link #getDigestByBouncycastle(String)} like "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512"
     * for data encoding internally use {@code StandardCharsets.UTF_8}.
     * @param digest        the message digest object from bouncycastle.
     * @param data          the byte data array for generate hmac.
     * @param key           the Key for secret key generation.
     * @since               1.0
     *
     * @see #hmacStringByBouncycastleHmacUsingAlgorithmKey_86(String, String, Charset, String)
     */
    public String hmacStringByBouncycastleHmacUsingDigestKey_86(Digest digest, byte[] data, String key) {

        MoraHexUtilities hexUtilities = new MoraHexUtilities();

        HMac hMac = new HMac(digest);
        hMac.init(new KeyParameter(key.getBytes(StandardCharsets.UTF_8)));

        byte[] hmacIn = data;
        hMac.update(hmacIn, 0, hmacIn.length);
        byte[] hmacOut = new byte[hMac.getMacSize()];

        hMac.doFinal(hmacOut, 0);
        String result = hexUtilities.convertByteArrayToHexWithApacheCommons(hmacOut);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "HMAC String :{}, generated with Algorithm:{} and Key:{}", result, (null != digest)?digest.getAlgorithmName():null, key);
        return result;
    }

    /**
     * Get message digest object based on given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code algorithm} like "HmacMD2", "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512".
     * @param algorithm     the hashing algorithm as String.
     * @since               1.0
     *
     * @see #hmacStringByBouncycastleHmacUsingDigestKey_86(Digest, byte[], String)
     */
    public Digest getDigestByBouncycastle(String algorithm) {
        if(algorithm != null) {
            switch (algorithm) {
                case "HmacMD2":
                    return new MD2Digest();
                case "HmacMD5":
                    return new MD5Digest();
                case "HmacSHA1":
                    return new SHA1Digest();
                case "HmacSHA224":
                    return new SHA224Digest();
                case "HmacSHA256":
                    return new SHA256Digest();
                case "HmacSHA384":
                    return new SHA384Digest();
                case "HmacSHA512":
                    return new SHA512Digest();
            }
        }
        return new SHA256Digest();
    }

    /**
     * Generate hasmac (encrypted checksum) string with {@link #hmacStringByBouncycastleHmacUsingAlgorithmKey_86(String, String, Charset, String)}
     * internally use {@link KeyParameter}, {@link HMac#init(CipherParameters)}, {@link HMac#update(byte[], int, int)} and {@link HMac#doFinal(byte[], int)}
     * for given {@code data} String and {@code key} for key parameter generation and use {@link MoraHexUtilities#convertByteArrayToHexWithApacheCommons(byte[])}
     * for convert byte array to Hex String with given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code algorithm} like "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512"
     * for byte encoding internally using {@code StandardCharsets.UTF_8} as default charset.
     * @param algorithm     the hashing algorithm as String.
     * @param data          the String data for generate hmac.
     * @param key           the Key for secret key generation.
     * @since               1.0
     *
     * @see #hmacStringByBouncycastleHmacUsingAlgorithmKey_86(String, String, String)
     */
    public String hmacStringByBouncycastleHmacUsingAlgorithmKey_86(String algorithm, String data, String key) {
        String result = hmacStringByBouncycastleHmacUsingAlgorithmKey_86(algorithm,data, StandardCharsets.UTF_8,key);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "HMAC String :{}, generated with Algorithm:{} and Key:{} for Data:{}", result, algorithm, key, data);
        return result;
    }

    /**
     * Generate hasmac (encrypted checksum) string with {@link #hmacStringByBouncycastleHmacUsingDigestKey_86(Digest, byte[], String)}, and {@link #getDigestByBouncycastle(String)}
     * internally use {@link KeyParameter}, {@link HMac#init(CipherParameters)}, {@link HMac#update(byte[], int, int)} and {@link HMac#doFinal(byte[], int)}
     * for given {@code data} String and {@code key} for key parameter generation and use {@link MoraHexUtilities#convertByteArrayToHexWithApacheCommons(byte[])}
     * for convert byte array to Hex String with given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code algorithm} like "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512"
     * given {@code charset} for byte encoding and if {@code charset} is null internally using UTF-8 as default charset.
     * @param algorithm     the hashing algorithm as String.
     * @param data          the String data for generate hmac.
     * @param key           the Key for secret key generation.
     * @since               1.0
     *
     * @see #hmacStringByBouncycastleHmacUsingAlgorithmKey_86(String, String, String)
     */
    public String hmacStringByBouncycastleHmacUsingAlgorithmKey_86(String algorithm, String data, Charset charset, String key) {
        String result = hmacStringByBouncycastleHmacUsingDigestKey_86(getDigestByBouncycastle(algorithm),data.getBytes(charset==null? StandardCharsets.UTF_8:charset),key);
        LOGGER.debug(Thread.currentThread().getStackTrace(), "HMAC String :{}, generated with Algorithm:{} and Key:{} for Data:{}", result, algorithm, key, data);
        return result;
    }

    /**
     * Generate hasmac (encrypted checksum) string with {@link DigestUtils} for given {@code data} array
     * with given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code algorithm} like "HmacMD2", "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512",
     * "HmacSHA512/224", "HmacSHA512/256", "HmacSHA3-224", "HmacSHA3-256", "HmacSHA3-384" and "HmacSHA3-512".
     * @param algorithm     the hashing algorithm as String.
     * @param data          the byte data array for generate hmac.
     * @since               1.0
     */
    public String hmacStringByCommonsDigestUtilsUsingAlgorithm_9(String algorithm, byte[] data){
        switch (algorithm){
            case "HmacMD2":
                return DigestUtils.md2Hex(data);
            case "HmacMD5":
                return DigestUtils.md5Hex(data);
            case "HmacSHA1":
                return DigestUtils.sha1Hex(data);
            case "HmacSHA256":
                return DigestUtils.sha256Hex(data);
            case "HmacSHA384":
                return DigestUtils.sha384Hex(data);
            case "HmacSHA512":
                return DigestUtils.sha512Hex(data);
            case "HmacSHA512/224":
                return DigestUtils.sha512_224Hex(data);
            case "HmacSHA512/256":
                return DigestUtils.sha512_256Hex(data);
            case "HmacSHA3-224":
                return DigestUtils.sha3_224Hex(data);
            case "HmacSHA3-256":
                return DigestUtils.sha3_256Hex(data);
            case "HmacSHA3-384":
                return DigestUtils.sha3_384Hex(data);
            case "HmacSHA3-512":
                return DigestUtils.sha3_512Hex(data);
        }
        return DigestUtils.sha256Hex(data);
    }

    /**
     * Generate hasmac (encrypted checksum) string with {@link DigestUtils} for given {@code data} String
     * with given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code algorithm} like "HmacMD2", "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512",
     * "HmacSHA512/224", "HmacSHA512/256", "HmacSHA3-224", "HmacSHA3-256", "HmacSHA3-384" and "HmacSHA3-512".
     * @param algorithm     the hashing algorithm as String.
     * @param data          the String data for generate hmac.
     * @since               1.0
     */
    public String hmacStringByCommonsDigestUtilsUsingAlgorithm_9(String algorithm, String data){
        switch (algorithm){
            case "HmacMD2":
                return DigestUtils.md2Hex(data);
            case "HmacMD5":
                return DigestUtils.md5Hex(data);
            case "HmacSHA1":
                return DigestUtils.sha1Hex(data);
            case "HmacSHA256":
                return DigestUtils.sha256Hex(data);
            case "HmacSHA384":
                return DigestUtils.sha384Hex(data);
            case "HmacSHA512":
                return DigestUtils.sha512Hex(data);
            case "HmacSHA512/224":
                return DigestUtils.sha512_224Hex(data);
            case "HmacSHA512/256":
                return DigestUtils.sha512_256Hex(data);
            case "HmacSHA3-224":
                return DigestUtils.sha3_224Hex(data);
            case "HmacSHA3-256":
                return DigestUtils.sha3_256Hex(data);
            case "HmacSHA3-384":
                return DigestUtils.sha3_384Hex(data);
            case "HmacSHA3-512":
                return DigestUtils.sha3_512Hex(data);
        }
        return DigestUtils.sha256Hex(data);
    }

    /**
     * Generate hasmac (encrypted checksum) string with {@link DigestUtils} for given {@code fileStream} file stream with {@link FileInputStream}
     * with given The HmacSHA* algorithms as defined in RFC 2104 “HMAC: Keyed-Hashing for Message Authentication”
     * in {@code algorithm} like "HmacMD2", "HmacMD5", "HmacSHA1", "HmacSHA224", "HmacSHA256", "HmacSHA384", "HmacSHA512",
     * "HmacSHA512/224", "HmacSHA512/256", "HmacSHA3-224", "HmacSHA3-256", "HmacSHA3-384" and "HmacSHA3-512".
     * @param algorithm     the hashing algorithm as String.
     * @param fileStream    the input stream for the target file to generate hmac.
     * @since               1.0
     */
    public String hmacStringByCommonsDigestUtilsUsingAlgorithm_9(String algorithm, FileInputStream fileStream) throws IOException
    {
        switch (algorithm){
            case "HmacMD2":
                return DigestUtils.md2Hex(fileStream);
            case "HmacMD5":
                return DigestUtils.md5Hex(fileStream);
            case "HmacSHA1":
                return DigestUtils.sha1Hex(fileStream);
            case "HmacSHA256":
                return DigestUtils.sha256Hex(fileStream);
            case "HmacSHA384":
                return DigestUtils.sha384Hex(fileStream);
            case "HmacSHA512":
                return DigestUtils.sha512Hex(fileStream);
            case "HmacSHA512/224":
                return DigestUtils.sha512_224Hex(fileStream);
            case "HmacSHA512/256":
                return DigestUtils.sha512_256Hex(fileStream);
            case "HmacSHA3-224":
                return DigestUtils.sha3_224Hex(fileStream);
            case "HmacSHA3-256":
                return DigestUtils.sha3_256Hex(fileStream);
            case "HmacSHA3-384":
                return DigestUtils.sha3_384Hex(fileStream);
            case "HmacSHA3-512":
                return DigestUtils.sha3_512Hex(fileStream);
        }
        return DigestUtils.sha256Hex(fileStream);
    }

    public static String generateChecksumWithInputStream(String filepath, MessageDigest md) throws IOException {

        // DigestInputStream is better, but you also can hash file like this.
        try (InputStream fis = new FileInputStream(filepath)) {
            byte[] buffer = new byte[1024];
            int nread;
            while ((nread = fis.read(buffer)) != -1) {
                md.update(buffer, 0, nread);
            }
        }

        // bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : md.digest()) {
            sb.append(String.format("%02x", b));
        }
        String result = sb.toString();
        LOGGER.debug(Thread.currentThread().getStackTrace(), "HMAC String :{}, generated with Algorithm:{} for File:{}", result, (null != md)?md.getAlgorithm():null, filepath);
        return result;

    }

    public static String generateChecksumWithDigestInputStream(String filepath, MessageDigest md) throws IOException {

        //SHA, MD2, MD5, SHA-256, SHA-384
        // file hashing with DigestInputStream
        try (DigestInputStream dis = new DigestInputStream(new FileInputStream(filepath), md)) {
            while (dis.read() != -1) ; //empty loop to clear the data
            md = dis.getMessageDigest();
        }

        // bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : md.digest()) {
            sb.append(String.format("%02x", b));
        }
        String result = sb.toString();
        LOGGER.debug(Thread.currentThread().getStackTrace(), "HMAC String :{}, generated with Algorithm:{} for File:{}", result, (null!=md)?md.getAlgorithm():null, filepath);
        return result;

    }


}
