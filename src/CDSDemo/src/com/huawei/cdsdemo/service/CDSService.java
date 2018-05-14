package com.huawei.cdsdemo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.HeartBeat;
import com.huawei.cdsdemo.bean.RegisterRequest;
import com.huawei.cdsdemo.bean.RegisterResult;
import com.huawei.cdsdemo.request.Request;
import com.huawei.cdsdemo.session.UserSession;
import com.huawei.cdsdemo.util.CommonConstants;
import com.huawei.cdsdemo.util.ConfigList;
import com.huawei.cdsdemo.util.ConfigProperties;
import com.huawei.cdsdemo.util.ErrorCode;

/**
 * <p>
 * Title: CDSService
 * </p>
 * <p>
 * Description: Agent Service
 * </p>
 */
@SuppressWarnings("restriction")
public class CDSService
{
    
    private static Logger log = LoggerFactory.getLogger(CDSService.class);
    
    private static String ip;
    
    private static int uportal_server_port;
    
    private static int uportal_web_port;
    
    private static String localIP;
    
    private static String localPORT;
       
    private static Map<String, UserSession> userSession;
    
    private static String uportal_ip;
    

	public static String getIp() {
		return ip;
	}

	public static String getLocalIP() {
		return localIP;
	}

	public static String getLocalPORT() {
		return localPORT;
	}

	public static void initConfig()
    {      
        uportal_ip = ConfigProperties.getKey(ConfigList.BASIC, "UportalServer_IP");
        localIP = ConfigProperties.getKey(ConfigList.BASIC, "Local_IP");
        localPORT = ConfigProperties.getKey(ConfigList.BASIC, "Local_PORT");
        
        try 
        {
        	 uportal_server_port = Integer.parseInt(ConfigProperties.getKey(ConfigList.BASIC, "Server_PORT"));
             uportal_web_port = Integer.parseInt(ConfigProperties.getKey(ConfigList.BASIC, "Web_PORT"));
		} 
        catch (NumberFormatException e)
        {
            uportal_server_port = CommonConstants.uportal_server_port;
            uportal_web_port = CommonConstants.uportal_web_port;
        } 

       log.info("load properties success!");
       ip = "https://" + uportal_ip + ":" + uportal_server_port;
       //在此处初始化
       userSession = new HashMap<String, UserSession>();
    }
    
    public static CDSResponse<String> loginToUportal(String userId, String password, String userNumber)
    {
    	if (userId.isEmpty() ||password.isEmpty() ||userNumber.isEmpty()) 
    	{
			return null;
		}    	
            
        CDSResponse<String> response = new CDSResponse<String>();
        
        String url = CDSService.getIp() + "/login/sc";
        
        Map<String, Object> result = Request.loginPost(userId, password, url, userNumber);
        
        if (result != null)
        {
            if (result.get("data") != null)
            {
                response.setData(result.get("data").toString());
            }
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        
        log.info("login to uportal end!");
        
        return response;
    }
    
    public static CDSResponse<Object> logoutFromUportal(String userId)
    {	
    	if ( "".equals(userId) || userId.isEmpty())
    	{
    		return null;
		}

        CDSResponse<Object> response = new CDSResponse<Object>();
        UserSession usersession = CDSService.getUserSession(userId);
        
        if (null == usersession || null == usersession.getToken())
        {
            response.setRetcode(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getCode());
            response.setRetDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN));
            
            return response;
        }
        
        String url = CDSService.getIp() + "/logout/sc";
        
        Map<String, Object> result = Request.post(usersession.getToken(), url, null);
        
        if (result != null)
        {
            response.setRetcode(Integer.parseInt((String)result.get("returnCode")));
            response.setRetDesc(result.get("returnDesc").toString());
        }
        else
        {
            CDSService.clearSession(userId);
        }
        log.info("logout to uportal end!");
        
        return response;
    }
    
    public static CDSResponse<RegisterResult> register(String userId, int isRegister, String callBackUrl)
    {
    	if (userId == null || userId.isEmpty())
    	{
			return null;
		}
    	        
        CDSResponse<RegisterResult> response = new CDSResponse<RegisterResult>();
        UserSession usersession = CDSService.getUserSession(userId);
        RegisterResult rspRet = new RegisterResult();
        if (null == usersession || null == usersession.getToken())
        {
            response.setRetcode(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getCode());
            response.setRetDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN));           
            return response;
        }
        
        RegisterRequest reqBody = new RegisterRequest("1", callBackUrl, isRegister);
        
        String url = CDSService.getIp() + "/CDS/user/register";
        
        Map<String, Object> result = Request.post(usersession.getToken(), url, reqBody);
        
        if (result != null)
        {
            Map<String, Object> results = ((Map<String, Object>)result.get("data"));
            if (results != null)
            {
                rspRet.setUcResult((Integer)results.get("ucResult"));
                rspRet.setOldSessionID((Integer)results.get("oldSessionID"));
                rspRet.setNewSessionID((Integer)results.get("newSessionID"));
                rspRet.setOldCallBackURL(results.get("oldCallBackURL").toString());
            }
            
            response.setData(rspRet);
            response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
            response.setRetDesc(result.get("returnDesc").toString());
            usersession.setRegister(isRegister == 1 ? true : false);
        }
        
        log.info("register to CDS end!");
        return response;
    }
    
    /**
     * heartbeat CDS heartbeat
     * 
     * @param userId
     * @param sessionID
     * @return
     */
    public static CDSResponse<Object> heartbeat(String userId, int sessionID)
    {
    	if (userId == null || userId.isEmpty())
    	{
			return null;
		}
    	
        log.info("heartbeat proc!");
        CDSResponse<Object> response = new CDSResponse<Object>();
        UserSession usersession = CDSService.getUserSession(userId);
        if (null == usersession || null == usersession.getToken())
        {
            response.setRetcode(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getCode());
            response.setRetDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN));
            
            return response;
        }
        
        HeartBeat session = new HeartBeat(sessionID);
        
        String url = CDSService.getIp() + "/CDS/user/heartBeat";
        
        Map<String, Object> result = Request.post(usersession.getToken(), url, session);
        
        if (result != null)
        {
        	try
        	{
                response.setRetcode(Integer.parseInt(result.get("returnCode").toString()));
                response.setRetDesc(result.get("returnDesc").toString());
			} 
        	catch (NumberFormatException e)
        	{
                log.info("result is faile!"); 
			}       
        }
        
        return response;
    }
    
    public static UserSession getUserSession(String userID)
    {
        return userSession.get(userID);
    }
    
    public static boolean setUserSession(String userID, UserSession session)
    {
        if (userID.isEmpty() || null == session)
        {
            return false;
        }
        userSession.put(userID, session);
        return true;
    }
    
    public static String match(Matcher matcher)
    {
        while (matcher.find())
        {
            return matcher.group(1);
        }
        return "";
    }
    
    private static void clearSession(String userId)
    {
        if (userId.isEmpty())
        {
            return;
        }
        
        userSession.remove(userId);
    }
}
