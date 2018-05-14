package com.huawei.cdsdemo.service;

import java.util.Map;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.Proxy;
import com.huawei.cdsdemo.request.Request;
import com.huawei.cdsdemo.session.UserSession;

public class ProxyService
{
	
    public CDSResponse<Object> proxyGet(UserSession userSession, String proxyNumber)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/jasProxy/" + proxyNumber;
        
        Map<String, Object> result = Request.get(userSession.getToken(), url);
        
        if (result != null)
        {
            response.setData(result.get("data"));
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        return response;
    }
    
    public CDSResponse<Object> proxySearch(UserSession userSession)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/jasProxy/all";
        
        Map<String, Object> result = Request.get(userSession.getToken(), url);
        
        if (result != null)
        {
            response.setData(result.get("data"));
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        return response;
    }
}
