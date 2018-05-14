package com.huawei.cdsdemo.util;

/**
 * 
 * <p>Title: Log Output Tools</p>
 * <p>Description: Log Output Tools</p>
 */
public class LogUtils
{
    
    /**
     * agent ID
     */
    public static final String WORKNO = "WorkNo = {} : ";
    
    /**
     * NUM length 3
     */
    private final static int PHONE_LEN_3 = 3;
    
    /**
     * NUM length 6
     */
    private final static int PHONE_LEN_6 = 6;
    
    /**
     * NUM format
     * @param phoneNumber phoneNumber
     * @return      phone.length<8 : phone, phone.length>=8 : replace the middle 4 NUM with '*'
     */
    public static String formatPhoneNumber(String phoneNumber)
    {
        String phoneNumResult = "";
        if (null == phoneNumber || phoneNumber.isEmpty())
        {
            return phoneNumResult;
        }
        
        if (phoneNumber.length() < PHONE_LEN_3)
        {
            phoneNumResult = "***";
        }
        else if (phoneNumber.length() >= PHONE_LEN_3 && phoneNumber.length() <= PHONE_LEN_6)
        {
            String strTmp = phoneNumber.substring(1, phoneNumber.length() - 1);
            phoneNumResult = phoneNumber.replace(strTmp, strToStar(strTmp));
        }
        else if (phoneNumber.length() > PHONE_LEN_6)
        {
            String strTmp = phoneNumber.substring(2, phoneNumber.length() - 2);
            phoneNumResult = phoneNumber.replace(strTmp, strToStar(strTmp));
        }
        
        return phoneNumResult;
    }
    
    /**
     * replace all string with *
     * @param strTmp
     * @return
     */
    private static String strToStar(String strTmp)
    {
        if (null == strTmp || strTmp.isEmpty())
        {
            return "";
        }
        
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < strTmp.length(); i++)
        {
            bf.append("*");
        }
        
        return bf.toString();
    }
    
    /**
     * format input content
     * @param obj obj
     * @return    result
     */
    public static String encodeForLog(Object obj)
    {
        if (obj == null)
        {
            return "null";
        }
        String msg = obj.toString();
        int length = msg.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
        {
            char ch = msg.charAt(i);
            
            // replace \r\n with '_'
            if (ch == '\r' || ch == '\n')
            {
                ch = '_';
            }
            sb.append(Character.valueOf(ch));
        }
        return sb.toString();
    }
}
