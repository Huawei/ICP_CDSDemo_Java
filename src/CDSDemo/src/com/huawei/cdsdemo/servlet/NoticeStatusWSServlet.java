package com.huawei.cdsdemo.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@SuppressWarnings("deprecation")
public class NoticeStatusWSServlet extends WebSocketServlet
{
    private static Map<String, NoticeStatusInbound> msgBounders;
    
    static
    {
        msgBounders = new HashMap<String, NoticeStatusInbound>();
    }
    
    /**
     * 
     */
    private static final long serialVersionUID = 4340105676746770648L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeStatusWSServlet()
    {
        super();
    }
    
    
    public static void broadcastMsgTo(List<String> userIds, String msg)
    {
        for (Entry<String, NoticeStatusInbound> msgBounder : msgBounders.entrySet())
        {
            //if (userIds.contains(msgBounder.getKey())) {
            msgBounder.getValue().sendMsgToBrownser(msg);
            //}
        }
    }
    
    public static Map<String, NoticeStatusInbound> getMsgBounders()
    {
		return msgBounders;
	}

	@Override
    protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request)
    {
        String userId = request.getParameter("userId");
        //System.out.println("Will create a new msg bound for websocket. userId=" + userId);
        
        NoticeStatusInbound msgBounder = null;
        if (msgBounders.containsKey(userId))
        {
            msgBounder = msgBounders.get(userId);
        }
        else
        {
            msgBounder = new NoticeStatusInbound(userId);
            msgBounders.put(userId, msgBounder);
        }
        
        return msgBounder;
    }
}
