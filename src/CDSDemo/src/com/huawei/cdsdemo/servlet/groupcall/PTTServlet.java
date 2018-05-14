package com.huawei.cdsdemo.servlet.groupcall;

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
 * Servlet implementation class PTTServlet
 */
public class PTTServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(PTTServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PTTServlet()
    {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        doPost(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     * subscribe
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
            String groupNumber = request.getParameter("groupNumber");
            String isPtt = request.getParameter("isPtt");
            
            UserSession usersession = CDSService.getUserSession(dispatchNum);
            
            if (null == usersession || null == usersession.getToken())
            {
                result.setRetcode(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getCode());
                result.setRetDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN));
            }
            else
            {
                if ("true".equals(isPtt))
                {
                    result = usersession.ptt(groupNumber);
                }
                else
                {
                    result = usersession.dePtt(groupNumber);
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
        catch (Throwable t)
        {
            log.error("Throwable " + t);
        }
        
    }
    
}
