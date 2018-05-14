package com.huawei.cdsdemo.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class UserStatusNotify
{
    private String action;
    
    private String subscribeID;
    
    private List<UserStatus> userStateList;
    
    @JsonIgnore
    private String callBackUrlCDS;
        
    public String getCallBackUrlCDS() {
		return callBackUrlCDS;
	}

	public void setCallBackUrlCDS(String callBackUrlCDS) {
		this.callBackUrlCDS = callBackUrlCDS;
	}

	public String getAction()
    {
        return action;
    }
    
    public void setAction(String action)
    {
        this.action = action;
    }
    
    public List<UserStatus> getUserStateList()
    {
        return userStateList;
    }
    
    public void setUserStateList(List<UserStatus> userStateList)
    {
        this.userStateList = userStateList;
    }
    
    public String getSubscribeID()
    {
        return subscribeID;
    }
    
    public void setSubscribeID(String subscribeID)
    {
        this.subscribeID = subscribeID;
    }
    
}
