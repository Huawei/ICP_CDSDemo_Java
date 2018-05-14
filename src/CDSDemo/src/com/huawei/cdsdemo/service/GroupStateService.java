package com.huawei.cdsdemo.service;

import java.util.Map;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.request.Request;
import com.huawei.cdsdemo.session.UserSession;

public class GroupStateService
{
    
    public CDSResponse<Object> groupState(UserSession userSession, String groupNumber)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber + "/state";
        
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
