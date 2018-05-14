package com.huawei.cdsdemo.bean;

public class Proxy
{
    
    private String proxyNumber;
    
    private Integer proxyType;
    
    private String proxyDesc;
    
    public Proxy()
    {
    
    }
    
    public Proxy(String proxyNumber, Integer proxyType, String proxyDesc)
    {
        super();
        this.proxyNumber = proxyNumber;
        this.proxyType = proxyType;
        this.proxyDesc = proxyDesc;
    }
    
    /**
     * @return the proxyNumber
     */
    public String getProxyNumber()
    {
        return proxyNumber;
    }
    
    /**
     * @param proxyNumber the proxyNumber to set
     */
    public void setProxyNumber(String proxyNumber)
    {
        this.proxyNumber = proxyNumber;
    }
    
    /**
     * @return the proxyType
     */
    public Integer getProxyType()
    {
        return proxyType;
    }
    
    /**
     * @param proxyType the proxyType to set
     */
    public void setProxyType(Integer proxyType)
    {
        this.proxyType = proxyType;
    }
    
    /**
     * @return the proxyDesc
     */
    public String getProxyDesc()
    {
        return proxyDesc;
    }
    
    /**
     * @param proxyDesc the proxyDesc to set
     */
    public void setProxyDesc(String proxyDesc)
    {
        this.proxyDesc = proxyDesc;
    }
    
}
