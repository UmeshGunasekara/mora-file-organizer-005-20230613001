/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/27/2023 6:31 PM
 */
package com.slmora.learn.common.cryptography.hmac.util;

/**
 * This Enum created for
 * <ul>
 *     <li>....</li>
 * </ul>
 *
 * @since 1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          6/27/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public enum EHmacAlgorithm
{
    MD2("HmacMD2"),
    MD5("HmacMD5"),
    SHA1("HmacSHA1"),
    SHA224("HmacSHA224"),
    SHA256("HmacSHA256"),
    SHA384("HmacSHA384"),
    SHA512("HmacSHA512"),
    SHA512_224("HmacSHA512/224"),
    SHA512_256("HmacSHA512/256"),
    HmacSHA3_224("HmacSHA3-224"),
    HmacSHA3_256("HmacSHA3-256"),
    HmacSHA3_384("HmacSHA3-384"),
    HmacSHA3_512("HmacSHA3-512");
    private String hmacAlgorithmNameString;

    private EHmacAlgorithm(String hmacAlgorithmNameString){
        this.hmacAlgorithmNameString = hmacAlgorithmNameString;
    }

    public String getHmacAlgorithmNameString()
    {
        return hmacAlgorithmNameString;
    }
}
