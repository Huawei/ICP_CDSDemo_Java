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
import com.huawei.cdsdemo.util.ErrorCode;
import com.huawei.cdsdemo.util.StringUtils;

/**
 * Servlet implementation class LogoutFromUportalServlet
 */
public class LogoutFromUportalServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(LogoutFromUportalServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutFromUportalServlet()
    {
        super();
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
            CDSResponse<Object> result = new CDSResponse<Object>();
            String dispatchNum = request.getParameter("userId");
            
            if (dispatchNum == null || dispatchNum.isEmpty())
            {
                result.setRetcode(ErrorCode.PARAM_ILLEGAL.getCode());
                result.setRetDesc(ErrorCode.PARAM_ILLEGAL.getDesc(ErrorCode.PARAM_ILLEGAL));
            }
            else
            {
                result = CDSService.logoutFromUportal(dispatchNum);
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
