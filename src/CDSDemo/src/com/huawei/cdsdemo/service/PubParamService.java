package com.huawei.cdsdemo.service;

import java.util.HashMap;
import java.util.Map;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.PubParamters;
import com.huawei.cdsdemo.request.Request;
import com.huawei.cdsdemo.session.UserSession;

public class PubParamService
{
    
    public CDSResponse<Object> pubParamEdit(UserSession userSession, PubParamters pubParam)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/pubparam";
        
        Map<String, Object> result = Request.put(userSession.getToken(), url, pubParam);
            
        if (result != null)
        {
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        return response;
    }
    
    public CDSResponse<Object> pubParamGet(UserSession userSession)
    {
        CDSResponse<Object> response = new CDSResponse<Object>();
        
        String url = CDSService.getIp() + "/CDS/pubparam";
        
        Map<String, Object> result = Request.get(userSession.getToken(), url);
        
        if (result != null)
        {
            
            if (result.toString().contains("isPttDependsSelect")) 
            {
                response.setData(result.get("data"));
            }
            else
            {
                //接口返回内容和接口文档不一致
                response = setData(response, result);
            }
            
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        return response;
    }

    //使查询返回的内容保持和接口文档一致
    private CDSResponse<Object> setData(CDSResponse<Object> response, Map<String, Object> result) {
        Object object = result.get("data");
        String beanToJson = Request.beanToJson(object);
        HashMap<String, Object> resultMap = Request.jsonToMap(beanToJson);
        Integer maxGrpMonCount =Integer.parseInt(resultMap.get("maxGrpMonCount").toString());
        Integer maxGrpSubCount =Integer.parseInt(resultMap.get("maxGrpSubCount").toString());
        Integer maxDisMonCount =Integer.parseInt(resultMap.get("maxDisMonCount").toString());
        Integer maxDisSubCount =Integer.parseInt(resultMap.get("maxDisSubCount").toString());
        Integer maxTempGrpMemberCount =Integer.parseInt(resultMap.get("maxTempGrpMemberCount").toString());
        Integer maxPttTimeoutInterval =Integer.parseInt(resultMap.get("maxPttTimeoutInterval").toString());
        boolean pttDependsSelect ="false".equals(resultMap.get("pttDependsSelect").toString())?false:true;
        String recordPath = resultMap.get("recordPath").toString();
        
        HashMap<String, Object> hashMap = new HashMap<String,Object>();
        hashMap.put("maxGrpMonCount", maxDisMonCount);
        hashMap.put("maxGrpSubCount", maxDisSubCount);
        hashMap.put("maxDisMonCount", maxGrpMonCount);
        hashMap.put("maxDisSubCount", maxGrpSubCount);
        hashMap.put("maxTempGrpMemberCount", maxPttTimeoutInterval);
        hashMap.put("maxPttTimeoutInterval", maxTempGrpMemberCount);
        hashMap.put("isPttDependsSelect", pttDependsSelect);
        hashMap.put("recordPath", recordPath);
        
        response.setData(hashMap);
        return response;
    }
    
}
