package com.huawei.cdsdemo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Md5Util
{
    private static Logger log = LoggerFactory.getLogger(Md5Util.class);
    
    private static final String MD5_ALGORAITHM = "MD5";
    
    private static MessageDigest md5Digest;
    
    private static final char[] hexadecimal =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        
    static
    {
        try
        {
            md5Digest = MessageDigest.getInstance(MD5_ALGORAITHM);
        }
        catch (NoSuchAlgorithmException e)
        {
        	log.error("md5Digest is faile");
        }
    }
    
    public static String caculateStringMd5Value(String str) throws UnsupportedEncodingException
    {
        md5Digest.reset();
        md5Digest.update(str.getBytes("utf-8"));
        byte[] result = md5Digest.digest();
        
        return encode(result);
    }
    
    public static String encode(byte[] binaryData)
    {
        
        if (binaryData.length != 16)
            return null;
            
        char[] buffer = new char[32];
        
        for (int i = 0; i < 16; i++)
        {
            int low = binaryData[i] & 0x0f;
            int high = (binaryData[i] & 0xf0) >> 4;
            buffer[i * 2] = hexadecimal[high];
            buffer[i * 2 + 1] = hexadecimal[low];
        }
        
        return new String(buffer);
    }
}
