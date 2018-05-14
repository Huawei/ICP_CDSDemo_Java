package com.huawei.cdsdemo.service;

import java.util.Map;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.RadioGroup;
import com.huawei.cdsdemo.request.Request;
import com.huawei.cdsdemo.session.UserSession;

public class RadioService
{
    public CDSResponse<Object> radioGroupGet(UserSession userSession, String radioNumber)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/radioGroup/" + radioNumber;
        
        Map<String, Object> result = Request.get(userSession.getToken(), url);
        
        if (result != null)
        {
            response.setData(result.get("data"));
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        return response;
    }
    
    public CDSResponse<Object> radioGroupSearch(UserSession userSession, String pageNumber, String pageSize)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/radioGroup/?pageNumber=" + pageNumber + "&pageSize=" + pageSize;
        
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
