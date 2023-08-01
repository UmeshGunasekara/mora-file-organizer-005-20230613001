/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/23/2023 8:56 AM
 */
package com.slmora.learn.sandbox.test01;
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
 * <br>1.0          7/23/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T25 {
    public static void main(String[] args)
    {
        try {
            int i = 0 / 0;
            System.out.println(i);
        }catch (Exception e){
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
