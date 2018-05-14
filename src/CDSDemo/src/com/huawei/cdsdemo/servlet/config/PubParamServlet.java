package com.huawei.cdsdemo.servlet.config;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.PubParamters;
import com.huawei.cdsdemo.service.CDSService;
import com.huawei.cdsdemo.session.UserSession;
import com.huawei.cdsdemo.util.ErrorCode;
import com.huawei.cdsdemo.util.StringUtils;

/**
 * Servlet implementation class RadioServlet
 */
public class PubParamServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(PubParamServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PubParamServlet()
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
            String maxGrpMonCount = request.getParameter("maxGrpMonCount");
            String maxGrpSubCount = request.getParameter("maxGrpSubCount");
            String maxDisMonCount = request.getParameter("maxDisMonCount");
            String maxDisSubCount = request.getParameter("maxDisSubCount");
            String maxTempGrpMemberCount = request.getParameter("maxTempGrpMemberCount");
            String isPttDependsSelect = request.getParameter("isPttDependsSelect");
            boolean isPttDepend = true;
            if ("false".equals(isPttDependsSelect))
            {
                isPttDepend = false;
            }
            String isAutoDownCall = request.getParameter("isAutoDownCall");
            boolean isAutoCall = false;
            if ("true".equals(isAutoDownCall))
            {
                isAutoCall = true;
            }
            String maxPttTimeoutInterval = request.getParameter("maxPttTimeoutInterval");
            String recordPath = request.getParameter("recordPath");
            CDSResponse<Object> result = new CDSResponse<Object>();
            UserSession usersession = CDSService.getUserSession(dispatchNum);
            if (null == usersession || null == usersession.getToken())
            {
                result.setRetcode(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getCode());
                result.setRetDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN.getDesc(ErrorCode.ERR_SDK_UPORTAL_NOT_LOGIN));
            }
            else
            {
                if ("PUT".equals(reqMethod))
                {       
                   try 
                   {
                	    Integer maxGrpmonCounts = Integer.valueOf(maxGrpMonCount);
 	                    Integer maxGrpSubCounts = Integer.valueOf(maxGrpSubCount);
 	                    Integer maxDisMonCounts = Integer.valueOf(maxDisMonCount);
 	                    Integer maxDisSubCounts = Integer.valueOf(maxDisSubCount);
 	                    Integer maxTempGrpMemberCounts = Integer.valueOf(maxTempGrpMemberCount);
 	                    Integer maxPttTimeoutIntervals = Integer.valueOf(maxPttTimeoutInterval);
 	                    
 	                    PubParamters pubParam = new PubParamters(maxGrpmonCounts, maxGrpSubCounts, maxDisMonCounts, maxDisSubCounts, maxTempGrpMemberCounts, isPttDepend, maxPttTimeoutIntervals, recordPath);
 	                    result = usersession.pubParamEdit(pubParam); 
                    } 
	                catch (NumberFormatException e) 
	                {
	                    result.setRetcode(ErrorCode.FAILED.getCode());
	                    result.setRetDesc(ErrorCode.FAILED.getDesc(ErrorCode.FAILED));
				    }     
                }
                else
                {
                    result = usersession.pubParamGet();
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
