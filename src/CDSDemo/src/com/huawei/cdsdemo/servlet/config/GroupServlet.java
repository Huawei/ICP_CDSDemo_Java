package com.huawei.cdsdemo.servlet.config;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.Group;
import com.huawei.cdsdemo.service.CDSService;
import com.huawei.cdsdemo.session.UserSession;
import com.huawei.cdsdemo.util.ErrorCode;
import com.huawei.cdsdemo.util.StringUtils;

/**
 * Servlet implementation class GroupServlet
 */
public class GroupServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(GroupServlet.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupServlet()
    {
        super();
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        doPost(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {                      
            try 
            {
                request.setCharacterEncoding("utf-8");
            }
            catch (UnsupportedEncodingException e1) 
            {
            	log.error("request set character encoding error");
            }
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html");
                       
            String reqMethod = request.getParameter("method");
            String dispatchNum = request.getParameter("userId");
            String talkGroupNumber = request.getParameter("talkGroupNumber");
            String talkGroupType = request.getParameter("talkGroupType");
            if (talkGroupType == null || talkGroupType.trim().isEmpty())
            {
                talkGroupType = "0";
            }
                        
            String radioToRadio = request.getParameter("radioToRadio");
            if (null == radioToRadio || radioToRadio.trim().isEmpty()) 
            {
                radioToRadio = "false";
            }
            String configureUser = request.getParameter("configureUser");
            if (null == configureUser || configureUser.trim().isEmpty()) 
            {
                configureUser = "false";
            }
            String pageNumber = request.getParameter("pageNumber");
            String pageSize = request.getParameter("pageSize");
            String queryType = request.getParameter("queryType");
            
            CDSResponse<Object> result = new CDSResponse<Object>();
            UserSession usersession = CDSService.getUserSession(dispatchNum);
            if (null == usersession || null == usersession.getToken())
            {
                result.setRetcode(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getCode());
                result.setRetDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN));
            }
            else
            {
                if ("GET".equals(reqMethod))
                {
                    result = usersession.groupGet(talkGroupNumber);
                }
                else 
                {
                    result = usersession.groupSearch(pageNumber, pageSize, queryType);
                }
            }
            
            
            PrintWriter out = null;
            try{
                out = response.getWriter();
                out.println(StringUtils.beanToJson(result));
            }
            catch(Exception e)
            {
                log.error("PrintWriter println Error");
            }
            finally
            {
                if (null != out)
                {
                    try{
                        out.flush();
                        out.close();
                    }
                    catch(Exception e)
                    {
                    	log.error("Close PrintWriter error");
                    }
                }
            }
        }
        catch (Throwable throwable)
        {
            log.info("Throwable " + throwable);
        }
    }
    
}
