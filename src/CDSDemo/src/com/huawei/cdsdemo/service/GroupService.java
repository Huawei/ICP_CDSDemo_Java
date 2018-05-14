package com.huawei.cdsdemo.service;

import java.util.Map;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.Group;
import com.huawei.cdsdemo.request.Request;
import com.huawei.cdsdemo.session.UserSession;

public class GroupService
{
    public static CDSResponse<Object> groupGet(UserSession userSession, String groupNumber)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/" + groupNumber;
        
        Map<String, Object> result = Request.get(userSession.getToken(), url);
        
        if (result != null)
        {
            response.setData(result.get("data"));
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        return response;
    }
    
    public static CDSResponse<Object> groupSearch(UserSession userSession, String pageNumber, String pageSize,
        String queryType)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/talkGroup/?pageNumber=" + pageNumber + "&pageSize=" + pageSize
            + "&queryType=" + queryType;
            
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
