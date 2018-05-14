package com.huawei.cdsdemo.service;

import java.util.Map;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.request.Request;
import com.huawei.cdsdemo.session.UserSession;
import com.huawei.cdsdemo.util.ErrorCode;

public class MixedGroupCallService
{
    
    public CDSResponse<Object> mixGroupStart(UserSession userSession, String groupNumber, String dto,boolean isMute,String groupTempName,boolean isReservedSwith)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        Map<String, Object> map = Request.jsonToMap(dto);
        if (null == map)
        {
            response.setRetcode(ErrorCode.PARAM_ILLEGAL.getCode());
            response.setRetDesc(ErrorCode.PARAM_ILLEGAL.getDesc(ErrorCode.PARAM_ILLEGAL));
            return response;
        }
        map.put("mute", isMute);
        map.put("groupTempName", groupTempName);
        map.put("reservedSwitch", isReservedSwith);
        
        String url = CDSService.getIp() + "/CDS/mixedGroupcall/" + groupNumber;
        Map<String, Object> result = Request.post(userSession.getToken(), url, map);
            
        if (result != null)
        {
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        return response;
    }
    
    public CDSResponse<Object> mixGroupChange(UserSession userSession, String groupNumber, String dto)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        Map<String, Object> map = Request.jsonToMap(dto);
        
        if (null == map)
        {
            response.setRetcode(ErrorCode.PARAM_ILLEGAL.getCode());
            response.setRetDesc(ErrorCode.PARAM_ILLEGAL.getDesc(ErrorCode.PARAM_ILLEGAL));
            return response;
        }
        
        String url = CDSService.getIp() + "/CDS/mixedGroupcall/" + groupNumber;
        
        Map<String, Object> result = Request.put(userSession.getToken(), url, map);
            
        if (result != null)
        {
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        return response;
    }
    
    public CDSResponse<Object> mixGroupEnd(UserSession userSession, String groupNumber)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/mixedGroupcall/" + groupNumber;
        
        Map<String, Object> result = Request.delete(userSession.getToken(), url);
        
        if (result != null)
        {
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        return response;
    }
    
}
