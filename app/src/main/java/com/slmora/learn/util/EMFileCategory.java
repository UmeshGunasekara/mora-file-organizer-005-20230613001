/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 6:57 PM
 */
package com.slmora.learn.util;

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
 * <br>1.0          6/20/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public enum EMFileCategory
{
    FILE_CAT_VIDEO (1, "Video"),
    FILE_CAT_IMAGE (2, "Image"),
    FILE_CAT_AUDIO (3, "Audio"),
    FILE_CAT_DOC (4, "Document"),
    FILE_CAT_SETUP (5, "Setup"),
    FILE_CAT_PROGRAMMING (6, "Programming"),
    FILE_CAT_Other (7, "Other");

    private int fileCategoryCode;
    private String fileCategoryName;
    private EMFileCategory(int fileCategoryCode, String fileCategoryName)
    {
        this.fileCategoryCode=fileCategoryCode;
        this.fileCategoryName=fileCategoryName;
    }

    public int getFileCategoryCode(){
        return this.fileCategoryCode;
    }

    public String getFileCategoryName(){
        return this.fileCategoryName;
    }
}
