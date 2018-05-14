package com.huawei.cdsdemo.servlet;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.Record;
import com.huawei.cdsdemo.service.CDSService;
import com.huawei.cdsdemo.session.UserSession;
import com.huawei.cdsdemo.util.ErrorCode;
import com.huawei.cdsdemo.util.StringUtils;

/**
 * Servlet implementation class GroupServlet
 */
public class RecordServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(RecordServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet()
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
            CDSResponse<Object> result = new CDSResponse<Object>();
            
            String dispatchNum = request.getParameter("userId");
            String confer = request.getParameter("confer");
            String startTime = request.getParameter("startTime");
            String stopTime = request.getParameter("stopTime");
            String pageNumber = request.getParameter("pageNumber");
            String pageSize = request.getParameter("pageSize");
            String audioflag = request.getParameter("audioflag");
            String confflag = request.getParameter("confflag");
            boolean audiofl = true;
            if ("false".equals(audioflag))
            {
                audiofl = false;
            }
            boolean conffl = false;
            if ("true".equals(confflag))
            {
                conffl = true;
            }
            
            UserSession usersession = CDSService.getUserSession(dispatchNum);
            if (null == usersession || null == usersession.getToken())
            {
                result.setRetcode(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getCode());
                result.setRetDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN));
            }
            else
            {
                Record record = new Record("default", confer, startTime, stopTime, audiofl, conffl,
                    Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
                result = usersession.recordGet(record);
            }
            
            PrintWriter out = null;
            try
            {
                out = response.getWriter();
                out.println(StringUtils.beanToJson(result));
            }
            catch(RuntimeException e )
            {
                log.error("RuntimeException :"+e.getMessage());  
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
