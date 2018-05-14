package com.huawei.cdsdemo.session;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.Group;
import com.huawei.cdsdemo.bean.Proxy;
import com.huawei.cdsdemo.bean.PubParamters;
import com.huawei.cdsdemo.bean.RadioGroup;
import com.huawei.cdsdemo.bean.Record;
import com.huawei.cdsdemo.bean.Subscribe;
import com.huawei.cdsdemo.service.GroupCallService;
import com.huawei.cdsdemo.service.GroupService;
import com.huawei.cdsdemo.service.GroupStateService;
import com.huawei.cdsdemo.service.MixedGroupCallService;
import com.huawei.cdsdemo.service.ProxyService;
import com.huawei.cdsdemo.service.PubParamService;
import com.huawei.cdsdemo.service.RadioService;

public class UserSession
{
    private String userID;
    
    private String token;
    
    private String userNumber;
    
    private boolean isRegister;
    
    public UserSession(String userID)
    {
        this.userID = userID;
    }
    
    public CDSResponse<Object> recordGet(Record record)
    {
        GroupCallService service = new GroupCallService();
        return service.recordGet(this, record);
    }
    
    public CDSResponse<Object> getOnce()
    {
        GroupCallService service = new GroupCallService();
        return service.getOnce(this);
    }
    
    public CDSResponse<Object> subscribe(String groupNumber, Subscribe sub)
    {
        GroupCallService service = new GroupCallService();
        return service.subscribe(this, groupNumber, sub);
    }
    
    public CDSResponse<Object> monitor(String groupNumber)
    {
        GroupCallService service = new GroupCallService();
        return service.monitor(this, groupNumber);
    }
    
    public CDSResponse<Object> deMonitor(String groupNumber)
    {
        GroupCallService service = new GroupCallService();
        return service.deMonitor(this, groupNumber);
    }
    
    public CDSResponse<Object> select(String groupNumber)
    {
        GroupCallService service = new GroupCallService();
        return service.select(this, groupNumber);
    }
    
    public CDSResponse<Object> deSelect(String groupNumber)
    {
        GroupCallService service = new GroupCallService();
        return service.deSelect(this, groupNumber);
    }
    
    public CDSResponse<Object> ptt(String groupNumber)
    {
        GroupCallService service = new GroupCallService();
        return service.ptt(this, groupNumber);
    }
    
    public CDSResponse<Object> dePtt(String groupNumber)
    {
        GroupCallService service = new GroupCallService();
        return service.dePtt(this, groupNumber);
    }
    
    public CDSResponse<Object> mixGroupChange(String groupNumber, String mix)
    {
        MixedGroupCallService service = new MixedGroupCallService();
        return service.mixGroupChange(this, groupNumber, mix);
    }
    
    public CDSResponse<Object> mixGroupStart(String groupNumber, String mix,boolean isMute,String groupTempName,boolean isReservedSwith)
    {
        MixedGroupCallService service = new MixedGroupCallService();
        return service.mixGroupStart(this, groupNumber, mix, isMute, groupTempName, isReservedSwith);
    }
    
    public CDSResponse<Object> mixGroupEnd(String groupNumber)
    {
        MixedGroupCallService service = new MixedGroupCallService();
        return service.mixGroupEnd(this, groupNumber);
    }
    
    public CDSResponse<Object> emergencyAnswer(String groupNumber)
    {
        GroupCallService service = new GroupCallService();
        return service.emergencyAnswer(this, groupNumber);
    }
    
    public CDSResponse<Object> emergencyExit(String groupNumber) {
        GroupCallService service = new GroupCallService();
        return service.emergencyExit(this, groupNumber);
    }
    
    public CDSResponse<Object> radioGroupGet(String radioNumber)
    {
        RadioService radioService = new RadioService();
        return radioService.radioGroupGet(this, radioNumber);
    }
    
    public CDSResponse<Object> radioGroupSearch(String pageNumber, String pageSize)
    {
        RadioService radioService = new RadioService();
        return radioService.radioGroupSearch(this, pageNumber, pageSize);
    }
    
    public CDSResponse<Object> proxyGet(String proxyNumber)
    {
        ProxyService proxyService = new ProxyService();
        return proxyService.proxyGet(this, proxyNumber);
    }
    
    public CDSResponse<Object> proxySearch()
    {
        ProxyService proxyService = new ProxyService();
        return proxyService.proxySearch(this);
    }
    
    public CDSResponse<Object> pubParamEdit(PubParamters param)
    {
        PubParamService pubService = new PubParamService();
        return pubService.pubParamEdit(this, param);
    }
    
    public CDSResponse<Object> pubParamGet()
    {
        PubParamService pubService = new PubParamService();
        return pubService.pubParamGet(this);
    }
    
    public CDSResponse<Object> groupGet(String proxyNumber)
    {
        return GroupService.groupGet(this, proxyNumber);
    }
    
    public CDSResponse<Object> groupSearch(String pageNumber, String pageSize, String queryType)
    {
        return GroupService.groupSearch(this, pageNumber, pageSize, queryType);
    }
    
    public CDSResponse<Object> groupState(String groupNumber)
    {
        GroupStateService groupService = new GroupStateService();
        return groupService.groupState(this, groupNumber);
    }
    
    public boolean isRegister()
    {
        return isRegister;
    }
    
    public void setRegister(boolean isRegister)
    {
        this.isRegister = isRegister;
    }
    
    public String getUserID()
    {
        return userID;
    }
    
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    
    public String getToken()
    {
        return token;
    }
    
    public void setToken(String token)
    {
        this.token = token;
    }
    
    public String getUserNumber()
    {
        return userNumber;
    }
    
    public void setUserNumber(String userNumber)
    {
        this.userNumber = userNumber;
    }


    
}
