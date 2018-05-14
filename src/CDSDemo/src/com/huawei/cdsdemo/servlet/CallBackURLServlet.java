package com.huawei.cdsdemo.servlet;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.LineIterator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.cdsdemo.bean.CDSResponse;
import com.huawei.cdsdemo.bean.UserStatusNotify;
import com.huawei.cdsdemo.util.StringUtils;

/**
 * Servlet implementation class CallBackURLServlet
 */
@WebServlet(name = "CallBackURLServlet", urlPatterns = "/callback")
public class CallBackURLServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(CallBackURLServlet.class);
    
    public CallBackURLServlet()
    {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            BufferedReader reader = request.getReader();
            List<String> userIds = new ArrayList<String>();
            StringBuffer sb = new StringBuffer();            
            LineIterator lineItr = new LineIterator(reader);
           
            while (lineItr.hasNext())
            {
                sb.append(lineItr.next());
            }
            
            reader.close();
            ObjectMapper om = new ObjectMapper();
            
            UserStatusNotify notify = om.readValue(sb.toString(), UserStatusNotify.class);
            
            if (notify == null)
            {
                return;
            }
            
            userIds.add(notify.getSubscribeID());
            
            NoticeStatusWSServlet.broadcastMsgTo(userIds, sb.toString());
            
            CDSResponse<Object> result = new CDSResponse<Object>();
            
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
            log.error("throwable" + t);
        }

    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    {
        this.doGet(request, response);
    }
}
