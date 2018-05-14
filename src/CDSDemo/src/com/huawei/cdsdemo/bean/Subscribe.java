package com.huawei.cdsdemo.bean;

public class Subscribe
{
    
    private String callBackURL;
    
    private String websocketID;
    
    private int expire;
    
    public String getCallBackURL()
    {
        return callBackURL;
    }
    
    public void setCallBackURL(String callBackURL)
    {
        this.callBackURL = callBackURL;
    }
    
    public String getWebsocketID()
    {
        return websocketID;
    }
    
    public void setWebsocketID(String websocketID)
    {
        this.websocketID = websocketID;
    }
    
    public int getExpire()
    {
        return expire;
    }
    
    public void setExpire(int expire)
    {
        this.expire = expire;
    }
    
    public Subscribe(String callBackURL, String websocketID, int expired)
    {
        this.callBackURL = callBackURL;
        this.websocketID = websocketID;
        this.expire = expired;
    }
    
    public Subscribe()
    {
    }
}
