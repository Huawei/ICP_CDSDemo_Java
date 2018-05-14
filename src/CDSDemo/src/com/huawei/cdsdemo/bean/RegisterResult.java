package com.huawei.cdsdemo.bean;

public class RegisterResult
{
    
    private int ucResult;
    
    private int newSessionID;
    
    private int oldSessionID;
    
    private String oldCallBackURL;
    
    public int getUcResult()
    {
        return ucResult;
    }
    
    public void setUcResult(int ucResult)
    {
        this.ucResult = ucResult;
    }
    
    public int getNewSessionID()
    {
        return newSessionID;
    }
    
    public void setNewSessionID(int newSessionID)
    {
        this.newSessionID = newSessionID;
    }
    
    public int getOldSessionID()
    {
        return oldSessionID;
    }
    
    public void setOldSessionID(int oldSessionID)
    {
        this.oldSessionID = oldSessionID;
    }
    
    public String getOldCallBackURL()
    {
        return oldCallBackURL;
    }
    
    public void setOldCallBackURL(String oldCallBackURL)
    {
        this.oldCallBackURL = oldCallBackURL;
    }
}
