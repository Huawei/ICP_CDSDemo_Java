package com.huawei.cdsdemo.bean;

public class RegisterRequest
{
    
    private String requestType;
    
    private String callBackURL;
    
    private String registerServerType;
    
    public RegisterRequest(String registerServerType, String callBackUrl, int isRegister)
    {
        this.registerServerType = registerServerType;
        this.callBackURL = callBackUrl;
        this.requestType = String.valueOf(isRegister);
    }
    
    public String getRequestType()
    {
        return requestType;
    }
    
    public void setRequestType(String requestType)
    {
        this.requestType = requestType;
    }
    
    public String getCallBackURL()
    {
        return callBackURL;
    }
    
    public void setCallBackURL(String callBackURL)
    {
        this.callBackURL = callBackURL;
    }
    
    public String getRegisterServerType()
    {
        return registerServerType;
    }
    
    public void setRegisterServerType(String registerServerType)
    {
        this.registerServerType = registerServerType;
    }
    
}
