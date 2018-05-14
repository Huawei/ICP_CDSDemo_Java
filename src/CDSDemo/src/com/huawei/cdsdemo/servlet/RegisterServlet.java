package com.huawei.cdsdemo.servlet;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.RegisterResult;
import com.huawei.cdsdemo.service.CDSService;
import com.huawei.cdsdemo.util.StringUtils;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(RegisterServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet()
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
            String isRegisterStr = request.getParameter("isRegister");
            String dispatchNum = request.getParameter("userId");
            if (isRegisterStr == null || dispatchNum == null)
            {
                return;
            }
            int isRegister = isRegisterStr.equals("true") ? 1 : 0;
            String callBackUrl = "http://" + CDSService.getLocalIP() + ":" + CDSService.getLocalPORT() + "/CDSDemo/callback";
            
            CDSResponse<RegisterResult> result = CDSService.register(dispatchNum, isRegister, callBackUrl);
            
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
            log.info("Throwable " + t);
        }
        
    }
    
}
