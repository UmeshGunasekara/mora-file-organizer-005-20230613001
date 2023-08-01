/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 2:11 PM
 */
package com.slmora.learn.common.property.util;

import com.slmora.learn.common.logging.MoraLogger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

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
public class MoraAccessProperties {
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MoraAccessProperties.class);

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @implNote Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public String getPropertyFromResource(String propertyFileName, String propertyRef){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Property from file {} for reference {}", propertyFileName, propertyRef);
        String property = null;
        try(InputStream iStream = getClass().getClassLoader().getResourceAsStream(propertyFileName)){
            Properties properties = new Properties();
            properties.load(iStream);
            property = properties.getProperty(propertyRef);
            LOGGER.debug(Thread.currentThread().getStackTrace(), "Property from file {} for reference {} out {}", propertyFileName, propertyRef, property);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }finally {
            return property;
        }
    }

    /**
     * Read Given property form given property file in project resource
     *
     * @param propertyFileName as String Object with file name of property file
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this
     */
    public Properties getAllPropertiesFromResource(String propertyFileName){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Properties from file {}", propertyFileName);
        Properties properties = new Properties();
        try(InputStream iStream = getClass().getClassLoader().getResourceAsStream(propertyFileName)){
            properties.load(iStream);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }finally {
            return properties;
        }
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
//    public String getPropertyFromResourceWithPath(String propertyFileName, String propertyRef){
//        String property = null;
//        String filePath = getClass().getClassLoader().getResource(propertyFileName).getPath();
////        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        LOGGER.info("Root path is :"+filePath);
//        try(InputStream iStream = new FileInputStream(filePath)){
//            Properties properties = new Properties();
//            properties.load(iStream);
//            property = properties.getProperty(propertyRef);
//        } catch (IOException e) {
//            LOGGER.error(ExceptionUtils.getStackTrace(e));
//            e.printStackTrace();
//        }finally {
//            return property;
//        }
//    }

    /**
     * Read Given property form given property file in given file path
     *
     * @param propertyFilePath as String Object with path of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file from given path and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this for resource path we can use getClass().getClassLoader().getResource(propertyFileName).getPath()
     */
    public String getPropertyFromPath(String propertyFilePath, String propertyRef){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Property from file {} for reference {}", propertyFilePath, propertyRef);
        String property = null;
        try(InputStream iStream = new FileInputStream(propertyFilePath)){
            Properties properties = new Properties();
            properties.load(iStream);
            property = properties.getProperty(propertyRef);
            LOGGER.debug(Thread.currentThread().getStackTrace(), "Property from file {} for reference {} out {}", propertyFilePath, propertyRef, property);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }finally {
            return property;
        }
    }

    /**
     * Read Given property form given property file in given file path
     *
     * @param propertyFilePath as String Object with path of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file from given path and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this for resource path we can use getClass().getClassLoader().getResource(propertyFileName).getPath()
     */
    public Integer setPropertyFromPath(String propertyFilePath, String propertyRef, String propertyValue, String comment){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Set Property for file {} for reference {} with property value {} and comment {}", propertyFilePath, propertyRef, propertyValue, comment);
        Integer out = -1;
        try(InputStream iStream = new FileInputStream(propertyFilePath)){
            Properties properties = new Properties();
            properties.load(iStream);
            properties.setProperty(propertyRef, propertyValue);
            properties.store(
                    new FileWriter(propertyFilePath),
                    (null == comment) ? "Store in property file" : comment);
            out = 1;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }finally {
            return out;
        }
    }

    /**
     * Read Given property form given Xml property file in given file path
     *
     * @param propertyFilePath as String Object with path of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file from given path and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this for resource path we can use getClass().getClassLoader().getResource(propertyFileName).getPath()
     */
    public String getXmlPropertyFromPath(String propertyFilePath, String propertyRef){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Property from file {} for reference {}", propertyFilePath, propertyRef);
        String property = null;
        try(InputStream iStream = new FileInputStream(propertyFilePath)){
            Properties properties = new Properties();
            properties.loadFromXML(iStream);
            property = properties.getProperty(propertyRef);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }finally {
            return property;
        }
    }

    /**
     * Read all properties form given Xml property file in given file path
     *
     * @param propertyFilePath as Properties Object with path of xml property file
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file from given path and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this for resource path we can use getClass().getClassLoader().getResource(propertyFileName).getPath()
     */
    public Properties getAllPropertiesFromXmlPropertyWithPath(String propertyFilePath){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Properties from file {}", propertyFilePath);
        Properties properties = new Properties();
        try(InputStream iStream = new FileInputStream(propertyFilePath)){
            properties.loadFromXML(iStream);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }finally {
            return properties;
        }
    }

    /**
     * Read Given property form given Xml property file in given file path
     *
     * @param propertyFilePath as String Object with path of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file from given path and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this for resource path we can use getClass().getClassLoader().getResource(propertyFileName).getPath()
     */
    public Integer setXmlPropertyFromPath(String propertyFilePath, String propertyRef, String propertyValue, String comment){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Set Property for file {} for reference {} with property value {} and comment {}", propertyFilePath, propertyRef, propertyValue, comment);
        Integer out = -1;
        Properties properties = getAllPropertiesFromXmlPropertyWithPath(propertyFilePath);
        try(OutputStream oStream = new FileOutputStream(propertyFilePath)){
            properties.setProperty(propertyRef, propertyValue);
            properties.storeToXML(
                    oStream,
                    (null == comment) ? "Store in property file" : comment,
                    "UTF-8"
            );
            out = 1;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }finally {
            return out;
        }
    }

    /**
     * Read Given property form given Xml property file in given file path
     *
     * @param propertyFilePath as String Object with path of property file
     * @param propertyRef as String Object with reference of property
     * @return String Object will return with requested property or null
     * @throws IOException with file notfound or compatibility issue
     * @apiNote Read property file from given path and fetch requested property value in to one String Object
     * @Note Files.lines() method doesn't include line-termination character. If we want to read all text from a file
     * in to a String we can use this for resource path we can use getClass().getClassLoader().getResource(propertyFileName).getPath()
     */
    public Integer createXmlPropertyFromPath(String propertyFilePath, String propertyRef, String propertyValue, String comment){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Set Property for file {} for reference {} with property value {} and comment {}", propertyFilePath, propertyRef, propertyValue, comment);
        Integer out = -1;
        try(OutputStream oStream = new FileOutputStream(propertyFilePath)){
            Properties properties = new Properties();
            properties.setProperty(propertyRef, propertyValue);
            properties.storeToXML(
                    oStream,
                    null,
                    "UTF-8");
            out = 1;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }finally {
            return out;
        }
    }
}
