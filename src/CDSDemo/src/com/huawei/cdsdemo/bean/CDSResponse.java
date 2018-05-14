package com.huawei.cdsdemo.bean;

import com.huawei.cdsdemo.util.ErrorCode;

public class CDSResponse<T>
{
    private int retcode;
    
    private String retdesc;
    
    private T data;
    
    public int getRetcode()
    {
        return retcode;
    }
    
    public void setRetcode(int retcode)
    {
        this.retcode = retcode;
    }
    
    public String getRetDesc()
    {
        return retdesc;
    }
    
    public void setRetDesc(String retDesc)
    {
        this.retdesc = retDesc;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(T data)
    {
        this.data = data;
    }
    
    public CDSResponse()
    {
        this.retcode = ErrorCode.SUCCESS.getCode();
        this.retdesc = ErrorCode.SUCCESS.getDesc(ErrorCode.SUCCESS);
    }
}
