package com.huawei.cdsdemo.servlet;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.service.CDSService;
import com.huawei.cdsdemo.session.UserSession;
import com.huawei.cdsdemo.util.ErrorCode;
import com.huawei.cdsdemo.util.StringUtils;

/**
 * <p>Title: online agent interface servlet </p>
 * <p>Description: online agent interface servlet </p>
 */
public class LoginToUportalServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(LoginToUportalServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginToUportalServlet()
    {
        super();
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        this.doPost(request, response);
    }
    
    /**
     * login to uportal
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
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
            
            CDSResponse<String> result = new CDSResponse<String>();
            
            String dispatchNum = request.getParameter("userId");
            String password = request.getParameter("password");
            String phonenum = request.getParameter("userNumber");
            
            if (dispatchNum == null || dispatchNum.isEmpty() || password == null || password.isEmpty()
                || phonenum == null || phonenum.isEmpty())
            {
                result.setRetcode(ErrorCode.PARAM_ILLEGAL.getCode());
                result.setRetDesc(ErrorCode.PARAM_ILLEGAL.getDesc(ErrorCode.PARAM_ILLEGAL));
            }
            else
            {
                UserSession userSession = CDSService.getUserSession(dispatchNum);
                if (userSession != null)
                {
                    result.setRetcode(ErrorCode.ERR_SDK_UPORTAL_REPEAT_LOGIN.getCode());
                    result.setRetDesc(ErrorCode.ERR_SDK_UPORTAL_REPEAT_LOGIN.getDesc(ErrorCode.ERR_SDK_UPORTAL_REPEAT_LOGIN));
                }
                else
                {
                	result = CDSService.loginToUportal(dispatchNum, password, phonenum);
                }
                
            }
            
            PrintWriter out = null;
            try
            {
                out = response.getWriter();
                out.println(StringUtils.beanToJson(result));
            }
            catch (Exception e)
            {
                log.error("PrintWriter println Error");
            }
            finally
            {
                if (null != out)
                {
                    try
                    {
                        out.flush();
                        out.close();
                    }
                    catch (Exception e)
                    {
                        log.error("Close PrintWriter error");
                    }
                }
            }
        }
        catch (Throwable t)
        {
            log.error("Throwable " + t);
        }
        
    }
    
}
