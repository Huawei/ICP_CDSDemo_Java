package com.huawei.cdsdemo.bean;

import java.util.ArrayList;
import java.util.List;

public class Group
{
    
    private String talkGroupName;
    
    private String talkGroupNumber;
    
    private int talkGroupType;
    
    private List<String> radioGroupNumber = new ArrayList<String>();
    
    private List<String> dispatchNumber = new ArrayList<String>();
    
    private List<String> otherUserNumber = new ArrayList<String>();
    
    private String receiveOnly = "false";
    
    private int pttTimeoutInterval = 300;
    
    private int noPttTimeoutInterval = 600;
    
    private String talkGroupDesc;
    
    private String radioToRadio = "false";
    
    private String configureUser = "false";

    public Group()
    {
    
    }
    
    public Group(String talkGroupName, String talkGroupNumber, int talkGroupType, List<String> radioGroupNumber,
            List<String> dispatchNumber, List<String> otherUserNumber, String receiveOnly, int pttTimeoutInterval,
            int noPttTimeoutInterval, String talkGroupDesc, String radioToRadio, String configureUser) 
    {
        super();
        this.talkGroupName = talkGroupName;
        this.talkGroupNumber = talkGroupNumber;
        this.talkGroupType = talkGroupType;
        this.radioGroupNumber = radioGroupNumber;
        this.dispatchNumber = dispatchNumber;
        this.otherUserNumber = otherUserNumber;
        this.receiveOnly = receiveOnly;
        this.pttTimeoutInterval = pttTimeoutInterval;
        this.noPttTimeoutInterval = noPttTimeoutInterval;
        this.talkGroupDesc = talkGroupDesc;
        this.radioToRadio = radioToRadio;
        this.configureUser = configureUser;
    }

    public Group(String talkGroupName, String talkGroupNumber, int talkGroupType, List<String> radioGroupNumber,
        List<String> dispatchNumber, List<String> otherUserNumber, String talkGroupDesc, String receiveOnly)
    {
        super();
        this.talkGroupName = talkGroupName;
        this.talkGroupNumber = talkGroupNumber;
        this.talkGroupType = talkGroupType;
        this.radioGroupNumber = radioGroupNumber;
        this.dispatchNumber = dispatchNumber;
        this.otherUserNumber = otherUserNumber;
        this.talkGroupDesc = talkGroupDesc;
        this.receiveOnly = receiveOnly;
    }
    
    /**
     * @return the talkGroupName
     */
    public String getTalkGroupName()
    {
        return talkGroupName;
    }
    
    /**
     * @param talkGroupName the talkGroupName to set
     */
    public void setTalkGroupName(String talkGroupName)
    {
        this.talkGroupName = talkGroupName;
    }
    
    /**
     * @return the talkGroupNumber
     */
    public String getTalkGroupNumber()
    {
        return talkGroupNumber;
    }
    
    /**
     * @param talkGroupNumber the talkGroupNumber to set
     */
    public void setTalkGroupNumber(String talkGroupNumber)
    {
        this.talkGroupNumber = talkGroupNumber;
    }
    
    /**
     * @return the talkGroupType
     */
    public int getTalkGroupType()
    {
        return talkGroupType;
    }
    
    /**
     * @param talkGroupType the talkGroupType to set
     */
    public void setTalkGroupType(int talkGroupType)
    {
        this.talkGroupType = talkGroupType;
    }
    
    /**
     * @return the radioGroupNumber
     */
    public List<String> getRadioGroupNumber()
    {
        return radioGroupNumber;
    }
    
    /**
     * @param radioGroupNumber the radioGroupNumber to set
     */
    public void setRadioGroupNumber(List<String> radioGroupNumber)
    {
        this.radioGroupNumber = radioGroupNumber;
    }
    
    /**
     * @return the dispatchNumber
     */
    public List<String> getDispatchNumber()
    {
        return dispatchNumber;
    }
    
    /**
     * @param dispatchNumber the dispatchNumber to set
     */
    public void setDispatchNumber(List<String> dispatchNumber)
    {
        this.dispatchNumber = dispatchNumber;
    }
    
    /**
     * @return the otherUserNumber
     */
    public List<String> getOtherUserNumber()
    {
        return otherUserNumber;
    }
    
    /**
     * @param otherUserNumber the otherUserNumber to set
     */
    public void setOtherUserNumber(List<String> otherUserNumber)
    {
        this.otherUserNumber = otherUserNumber;
    }
    
    /**
     * @return the talkGroupDesc
     */
    public String getTalkGroupDesc()
    {
        return talkGroupDesc;
    }
    
    /**
     * @param talkGroupDesc the talkGroupDesc to set
     */
    public void setTalkGroupDesc(String talkGroupDesc)
    {
        this.talkGroupDesc = talkGroupDesc;
    }
    
    /**
     * @return the receiveOnly
     */
    public String getReceiveOnly()
    {
        return receiveOnly;
    }
    
    /**
     * @param receiveOnly the receiveOnly to set
     */
    public void setReceiveOnly(String receiveOnly)
    {
        this.receiveOnly = receiveOnly;
    }
    
    /**
     * @return the pttTimeoutInterval
     */
    public int getPttTimeoutInterval() 
    {
        return pttTimeoutInterval;
    }
    
    /**
     * @param pttTimeoutInterval the pttTimeoutInterval to set
     */
    public void setPttTimeoutInterval(int pttTimeoutInterval)
    {
        this.pttTimeoutInterval = pttTimeoutInterval;
    }
    
    /**
     * @return the noPttTimeoutInterval
     */
    public int getNoPttTimeoutInterval() 
    {
        return noPttTimeoutInterval;
    }

    /**
     * @param noPttTimeoutInterval the noPttTimeoutInterval to set
     */
    public void setNoPttTimeoutInterval(int noPttTimeoutInterval) 
    {
        this.noPttTimeoutInterval = noPttTimeoutInterval;
    }
    
    /**
     * @return radioToRadio
     */
    public String getRadioToRadio() 
    {
        return radioToRadio;
    }

    /**
     * @param radioToRadio the radioToRadion to set
     */
    public void setRadioToRadio(String radioToRadio) 
    {
        this.radioToRadio = radioToRadio;
    }

    /**
     * @return configureUser
     */
    public String getConfigureUser() 
    {
        return configureUser;
    }
    
    /**
     * @param configureUser the configureUser to set
     */
    public void setConfigureUser(String configureUser) 
    {
        this.configureUser = configureUser;
    }
    
}
