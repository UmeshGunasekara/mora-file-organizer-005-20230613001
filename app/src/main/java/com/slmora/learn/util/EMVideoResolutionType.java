/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/21/2023 11:27 PM
 */
package com.slmora.learn.util;

import java.util.stream.Stream;

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
 * <br>1.0          6/21/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public enum EMVideoResolutionType
{

    FILE_RES_SD (1, "SD (Standard Definition)", "480p", 480, 640),
    FILE_RES_HD (2, "HD (High Definition)", "720p", 720, 1280),
    FILE_RES_FHD (3, "Full HD (FHD)", "1080p", 1080, 1920),
    FILE_RES_QHD (4, "QHD (Quad HD)", "1440p", 1440, 2560),
    FILE_RES_2K (5, "2K video", "2K", 1080, 2048),
    FILE_RES_4K (6, "4K video or Ultra HD (UHD)", "4K", 2160, 3840),
    ILE_RES_8K (7, "8K video or Full Ultra HD", "8K", 4320, 7680),
    ILE_RES_OTHER (8, "OtherResolution Type", "Other", 1, 1);

    private int resolutionCode;
    private String resolutionTypeDescription;
    private String resolutionCommonName;
    private int resolutionHeight;
    private int resolutionWidth;

    private EMVideoResolutionType(int resolutionCode, String resolutionTypeDescription, String resolutionCommonName, int resolutionHeight, int resolutionWidth){
        this.resolutionCode=resolutionCode;
        this.resolutionTypeDescription=resolutionTypeDescription;
        this.resolutionCommonName=resolutionCommonName;
        this.resolutionHeight=resolutionHeight;
        this.resolutionWidth=resolutionWidth;
    }

    public int getResolutionCode()
    {
        return this.resolutionCode;
    }

    public String getResolutionTypeDescription()
    {
        return this.resolutionTypeDescription;
    }

    public String getResolutionCommonName()
    {
        return this.resolutionCommonName;
    }

    public int getResolutionHeight()
    {
        return this.resolutionHeight;
    }

    public int getResolutionWidth()
    {
        return this.resolutionWidth;
    }

    public static Stream<EMVideoResolutionType> stream() {
        return Stream.of(EMVideoResolutionType.values());
    }
}
