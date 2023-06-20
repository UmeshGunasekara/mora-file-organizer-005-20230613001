/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/14/2023 1:58 PM
 */
package com.slmora.learn.common.ds.hibernate;

import com.slmora.learn.common.property.util.MoraAccessProperties;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

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
public class HibernateHikariAnoUtil {
    final static Logger LOGGER = LogManager.getLogger(HibernateHikariAnoUtil.class);

    private static String CONNECTION_HOST;
    private static String CONNECTION_PORT;
    private static String CONNECTION_DATABASE;
    private static String CONNECTION_USER;
    private static String CONNECTION_PASSWORD;
    private static String CONNECTION_CLASS_NAME;
    private static String CONNECTION_PROPERTY_STRING;

    private static String HIBERNATE_DIALECT;
    private static String HIBERNATE_SHOW_SQL;
    private static String HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS;
    private static String HIBERNATE_HBM2DDL_AUTO;

    private static String HIBERNATE_HIKARI_CONNECTION_PROVIDER_CLASS;
    private static String HIBERNATE_HIKARI_MAXIMUM_POOL_SIZE;
    private static String HIBERNATE_HIKARI_MINIMUM_IDLE;
    private static String HIBERNATE_HIKARI_CACHE_PREP_STMTS;
    private static String HIBERNATE_HIKARI_PREP_STMT_CACHE_SIZE;
    private static String HIBERNATE_HIKARI_PREP_STMT_CACHE_SQL_LIMIT;
    private static String HIBERNATE_HIKARI_USE_SERVER_PREP_STMTS;
    private static String HIBERNATE_HIKARI_IDLE_TIMEOUT;

    private static String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
    private static String HIBERNATE_CACHE_USE_QUERY_CACHE;
    private static String HIBERNATE_CACHE_REGION_FACTORY_CLASS;

    private static StandardServiceRegistry REGISTRY;
    private static SessionFactory SESSION_FACTORY;

    private HibernateHikariAnoUtil(){}

    /**
     * Assign property values from datasource.properties and create JDBC connection in class initialization
     *
     * @Note don't want include this key word with constant because of they are static
     */
    static{
        setDtaSourceAttributes();
        setHibernateAttributes();
        setHikariAttributes();
    }

    /**
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    public static SessionFactory getHibernateSessionFactory() throws Throwable{
        if(SESSION_FACTORY == null){
            try {
                Configuration configuration=new Configuration();

                Properties settings=new Properties();
                settings.put(Environment.DRIVER,CONNECTION_CLASS_NAME);
                settings.put(Environment.URL,getFullConnectionURL());
                settings.put(Environment.USER,CONNECTION_USER);
                settings.put(Environment.PASS,CONNECTION_PASSWORD);
                settings.put(Environment.DIALECT,HIBERNATE_DIALECT);
                settings.put(Environment.SHOW_SQL,HIBERNATE_SHOW_SQL);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS);
                settings.put(Environment.HBM2DDL_AUTO,HIBERNATE_HBM2DDL_AUTO);
                settings.put(Environment.CONNECTION_PROVIDER,HIBERNATE_HIKARI_CONNECTION_PROVIDER_CLASS);
                settings.put(Environment.USE_SECOND_LEVEL_CACHE,HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE);
                settings.put(Environment.USE_QUERY_CACHE,HIBERNATE_CACHE_USE_QUERY_CACHE);
//                settings.put(Environment.CACHE_REGION_FACTORY,HIBERNATE_CACHE_REGION_FACTORY_CLASS);
                settings.put("hibernate.javax.cache.provider","org.ehcache.jsr107.EhcacheCachingProvider");

//                settings.put("hikari.dataSource.cachePrepStmts", HIBERNATE_HIKARI_CACHE_PREP_STMTS);
//                settings.put("hikari.dataSource.prepStmtCacheSize", HIBERNATE_HIKARI_PREP_STMT_CACHE_SIZE);
//                settings.put("hikari.dataSource.prepStmtCacheSqlLimit", HIBERNATE_HIKARI_PREP_STMT_CACHE_SQL_LIMIT);
//                settings.put("hikari.dataSource.useServerPrepStmts", HIBERNATE_HIKARI_USE_SERVER_PREP_STMTS);
//                settings.put("hikari.maximumPoolSize", HIBERNATE_HIKARI_MAXIMUM_POOL_SIZE);
//                settings.put("hikari.idleTimeout", "");
//                settings.put("hibernate.hikari.minimumIdle", HIBERNATE_HIKARI_MINIMUM_IDLE);

                settings.put("hibernate.hikari.maximumPoolSize", HIBERNATE_HIKARI_MAXIMUM_POOL_SIZE);
                settings.put("hibernate.hikari.idleTimeout", HIBERNATE_HIKARI_IDLE_TIMEOUT);
                settings.put("hibernate.hikari.minimumIdle", HIBERNATE_HIKARI_MINIMUM_IDLE);

                configuration.setProperties(settings);
//                configuration.addAnnotatedClass(MFODirectory.class);
                entityConfiguration(configuration);

                REGISTRY = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                SESSION_FACTORY=configuration.buildSessionFactory(REGISTRY);

            }catch (Exception e){
                LOGGER.error(ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
                if(REGISTRY != null){
                    StandardServiceRegistryBuilder.destroy(REGISTRY);
                }
            }
        }
        return SESSION_FACTORY;
    }

    private static void entityConfiguration(Configuration configuration){
        configuration.addAnnotatedClass(EMFODirectory.class);
        configuration.addAnnotatedClass(EMFOFile.class);
    }

    /**
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    public static void shutdown(){
        if(REGISTRY != null){
            StandardServiceRegistryBuilder.destroy(REGISTRY);
        }
    }

    /**
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    private static void setDtaSourceAttributes(){
        try {
            Properties dataSourceProperties = new MoraAccessProperties().getAllPropertiesFromResource(
                    "datasource.properties");
            if (CONNECTION_HOST == null || CONNECTION_HOST.isBlank()) {
                CONNECTION_HOST = dataSourceProperties.getProperty("MORA.DATASOURCE.HOST");
            }
            if (CONNECTION_PORT == null || CONNECTION_PORT.isBlank()) {
                CONNECTION_PORT = dataSourceProperties.getProperty("MORA.DATASOURCE.PORT");
            }
            if (CONNECTION_DATABASE == null || CONNECTION_DATABASE.isBlank()) {
                CONNECTION_DATABASE = dataSourceProperties.getProperty("MORA.DATASOURCE.DATABASE");
            }
            if (CONNECTION_USER == null || CONNECTION_USER.isBlank()) {
                CONNECTION_USER = dataSourceProperties.getProperty("MORA.DATASOURCE.USER");
            }
            if (CONNECTION_PASSWORD == null || CONNECTION_PASSWORD.isBlank()) {
                CONNECTION_PASSWORD = dataSourceProperties.getProperty("MORA.DATASOURCE.PASSWORD");
            }
            if (CONNECTION_CLASS_NAME == null || CONNECTION_CLASS_NAME.isBlank()) {
                CONNECTION_CLASS_NAME = dataSourceProperties.getProperty("MORA.DATASOURCE.CLASS");
            }
            if (CONNECTION_PROPERTY_STRING == null || CONNECTION_PROPERTY_STRING.isBlank()) {
                CONNECTION_PROPERTY_STRING = dataSourceProperties.getProperty("MORA.DATASOURCE.PROPERTY_STRING");
            }
        }catch (Exception e){
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    private static void setHibernateAttributes(){
        try {
            Properties hibernateProperties = new MoraAccessProperties().getAllPropertiesFromResource("datasource.hibernate.properties");
            if(HIBERNATE_DIALECT == null || HIBERNATE_DIALECT.isBlank()){
                HIBERNATE_DIALECT = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.DIALECT");
            }
            if(HIBERNATE_SHOW_SQL == null || HIBERNATE_SHOW_SQL.isBlank()){
                HIBERNATE_SHOW_SQL = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.SHOW_SQL");
            }
            if(HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS == null || HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS.isBlank()){
                HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.CURRENT_SESSION_CONTEXT_CLASS");
            }
            if(HIBERNATE_HBM2DDL_AUTO == null || HIBERNATE_HBM2DDL_AUTO.isBlank()){
                HIBERNATE_HBM2DDL_AUTO = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HBM2DDL_AUTO");
            }
            if(HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE == null || HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE.isBlank()){
                HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.CACHE.USE_SECOND_LEVEL_CACHE");
            }
            if(HIBERNATE_CACHE_USE_QUERY_CACHE == null || HIBERNATE_CACHE_USE_QUERY_CACHE.isBlank()){
                HIBERNATE_CACHE_USE_QUERY_CACHE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.CACHE.USE_QUERY_CACHE");
            }
            if(HIBERNATE_CACHE_REGION_FACTORY_CLASS == null || HIBERNATE_CACHE_REGION_FACTORY_CLASS.isBlank()){
                HIBERNATE_CACHE_REGION_FACTORY_CLASS = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.CACHE.REGION.FACTORY_CLASS");
            }
        }catch (Exception e){
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * Read datasource.properties and assign datasource values
     *
     * @return String Object will return with requested property or null
     * @apiNote Read datasource.properties property file and fetch datasource property values and set before create connection
     * @Note don't want include this key word with constant because of they are static
     */
    private static void setHikariAttributes(){
        try {
            Properties hibernateProperties = new MoraAccessProperties().getAllPropertiesFromResource("datasource.hibernate.hikari.properties");
            if(HIBERNATE_HIKARI_CONNECTION_PROVIDER_CLASS == null || HIBERNATE_HIKARI_CONNECTION_PROVIDER_CLASS.isBlank()){
                HIBERNATE_HIKARI_CONNECTION_PROVIDER_CLASS = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HIKARI.CONNECTION_PROVIDER_CLASS");
            }
            if(HIBERNATE_HIKARI_MAXIMUM_POOL_SIZE == null || HIBERNATE_HIKARI_MAXIMUM_POOL_SIZE.isBlank()){
                HIBERNATE_HIKARI_MAXIMUM_POOL_SIZE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HIKARI.MAXIMUM_POOL_SIZE");
            }
            if(HIBERNATE_HIKARI_MINIMUM_IDLE == null || HIBERNATE_HIKARI_MINIMUM_IDLE.isBlank()){
                HIBERNATE_HIKARI_MINIMUM_IDLE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HIKARI.MINIMUM_IDLE");
            }
            if(HIBERNATE_HIKARI_CACHE_PREP_STMTS == null || HIBERNATE_HIKARI_CACHE_PREP_STMTS.isBlank()){
                HIBERNATE_HIKARI_CACHE_PREP_STMTS = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HIKARI.CACHE_PREP_STMTS");
            }
            if(HIBERNATE_HIKARI_PREP_STMT_CACHE_SIZE == null || HIBERNATE_HIKARI_PREP_STMT_CACHE_SIZE.isBlank()){
                HIBERNATE_HIKARI_PREP_STMT_CACHE_SIZE = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HIKARI.PREP_STMT_CACHE_SIZE");
            }
            if(HIBERNATE_HIKARI_PREP_STMT_CACHE_SQL_LIMIT == null || HIBERNATE_HIKARI_PREP_STMT_CACHE_SQL_LIMIT.isBlank()){
                HIBERNATE_HIKARI_PREP_STMT_CACHE_SQL_LIMIT = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HIKARI.PREP_STMT_CACHE_SQL_LIMIT");
            }
            if(HIBERNATE_HIKARI_USE_SERVER_PREP_STMTS == null || HIBERNATE_HIKARI_USE_SERVER_PREP_STMTS.isBlank()){
                HIBERNATE_HIKARI_USE_SERVER_PREP_STMTS = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HIKARI.USE_SERVER_PREP_STMTS");
            }
            if(HIBERNATE_HIKARI_IDLE_TIMEOUT == null || HIBERNATE_HIKARI_IDLE_TIMEOUT.isBlank()){
                HIBERNATE_HIKARI_IDLE_TIMEOUT = hibernateProperties.getProperty("MORA.DATASOURCE.HIBERNATE.HIKARI.IDLE_TIMEOUT");
            }
        }catch (Exception e){
            LOGGER.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * create full connection URL using assigned attributes
     *
     * @return String Object will return with full URl
     * @apiNote Rcreate full connection URL using assigned attributes
     * @Note If CONNECTION_PORT is null set default port
     */
    private static String getFullConnectionURL(){
        if(null == CONNECTION_PORT){
            return "jdbc:mysql://"+ CONNECTION_HOST +":3306/"+CONNECTION_DATABASE+"?"+CONNECTION_PROPERTY_STRING;
        }else{
            return "jdbc:mysql://"+ CONNECTION_HOST +":"+CONNECTION_PORT+"/"+CONNECTION_DATABASE+"?"+CONNECTION_PROPERTY_STRING;
        }
    }
}
