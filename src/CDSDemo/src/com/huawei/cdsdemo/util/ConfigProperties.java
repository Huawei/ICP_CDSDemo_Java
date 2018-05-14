package com.huawei.cdsdemo.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: read configuration parameters </p>
 * <p>Description: read configuration parameters  </p>
 */
public final class ConfigProperties
{
	private static final Logger LOG = LoggerFactory.getLogger(ConfigProperties.class);
        
    /**
     * configuration parameters
     */
    private static Map<String, Properties> propsMap = new ConcurrentHashMap<String, Properties>();
    
    private ConfigProperties()
    {
    
    }
    
    /**
     * load configuration files 
     */
    public static boolean loadConfig()
    {
        Field[] configListFields = ConfigList.class.getFields();
        
        String filePath = getConfigFilePath();
        
        try
        {
            for (Field field : configListFields)
            {
                /**
                 * file name
                 */
                String fileName;				
				fileName = String.valueOf(field.get(null));	
                File fileConf = new File(filePath + fileName);
                
                if (fileConf.exists())
                {
                    readFileProperties(fileConf);
                }
                else
                {
                	LOG.info("File is not exist");
                    propsMap.put(fileName, new Properties());
                }
                
            }
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
            LOG.info("Load properties from config file has exception");
            return false;
        }
        
        return true;
    }
    
    public static String getConfigFilePath()
    {
        /**
         * get classes dir
         */
    	ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    	if (null == contextClassLoader) 
    	{
    		return null;
		}
    	URL url = contextClassLoader.getResource("");
    	if (null == url) 
        {
   			return null;
   		}
        
        /**
         * get WEB-INF dir
         */
        String filePath = url.getPath().substring(0, url.getPath().lastIndexOf("/classes") + 1);
                
        try
        {
            filePath = URLDecoder.decode(filePath, "UTF-8") + "/config/";
        }
        catch (UnsupportedEncodingException e)
        {
            filePath = "";
        }
        
        return filePath;
    }
    
    private static void readFileProperties(File file)
    {
        InputStream inputFile = null;
        try
        {
            inputFile = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
            Properties props = new Properties();
            props.load(inputFile);
            propsMap.put(file.getName(), props);
        }
        catch ( IOException e)
        {
            LOG.error("Load config file failed.");
        }
        finally
        {
            closeFile(inputFile);
        }
    }
    public static String getKey(String configFile, String key)
    {
        if (propsMap.containsKey(configFile))
        {
            String result = propsMap.get(configFile).getProperty(key);
            if (result != null)
            {
                return result.trim();
            }
        }
        return "";
    }
    

    
    public static void setKey(String configFile, String key, String value)
    {
        if (propsMap.containsKey(configFile))
        {
            if (value != null)
            {
                propsMap.get(configFile).setProperty(key, value);
            }
            else
            {
                propsMap.remove(key);
            }
        }
    }
    
    public static Properties getProperties(String configFile)
    {
        if (propsMap.containsKey(configFile))
        {
            return propsMap.get(configFile);
        }
        return null;
    }
    
    
   
    
    public static void setProperties(String configFile, Properties prop)
    {
        propsMap.put(configFile, prop);
    }
    private static void closeFile(InputStream inputFile)
    {
        try
        {
            if (null != inputFile)
            {
                inputFile.close();
            }
        }
        catch (IOException e)
        {
            LOG.error("Close file failed.");
        }
    }
    
    public static void reLoadConfig(String configFile)
    {
        String filePath = getConfigFilePath() + configFile;
        
        File file = new File(filePath);
        if (!file.exists())
        {
        	LOG.info("File is not exist");
            propsMap.put(configFile, new Properties());
            return;
        }
        
        InputStream inputFile = null;
        try
        {
            inputFile = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
            Properties props = new Properties();
            props.load(inputFile);
            propsMap.put(configFile, props);
        }
        catch (IOException e)
        {           
            LOG.error("Load config file failed.");
        }
        finally
        {
            closeFile(inputFile);
        }
    }
}
