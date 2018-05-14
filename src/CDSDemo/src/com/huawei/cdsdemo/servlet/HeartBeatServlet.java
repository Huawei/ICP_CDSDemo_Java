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
import com.huawei.cdsdemo.util.StringUtils;

/**
 * Servlet implementation class HeartBeatServlet
 */
public class HeartBeatServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(HeartBeatServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeartBeatServlet()
    {
        super();
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        this.doPost(request, response);
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
            
            int sessionID = Integer.parseInt(request.getParameter("sessionID"));
            String dispatchNum = request.getParameter("userId");
            
            CDSResponse<Object> result = CDSService.heartbeat(dispatchNum, sessionID);
            
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
