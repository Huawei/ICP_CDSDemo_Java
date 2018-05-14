package com.huawei.cdsdemo.bean;

public class HeartBeat
{
    
    private int sessionID;
    
    /** 
     * @Title:HeartBeat
     * @Description:TODO 
     * @param sessionID 
     */
    public HeartBeat(int sessionID)
    {
        super();
        this.sessionID = sessionID;
    }
    
    /**
     * @return the sessionID
     */
    public int getSessionID()
    {
        return sessionID;
    }
    
    /**
     * @param sessionID the sessionID to set
     */
    public void setSessionID(int sessionID)
    {
        this.sessionID = sessionID;
    }
    
}
