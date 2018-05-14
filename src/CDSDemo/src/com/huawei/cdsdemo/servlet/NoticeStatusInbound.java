package com.huawei.cdsdemo.servlet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class NoticeStatusInbound extends MessageInbound
{
	private static Logger log = LoggerFactory.getLogger(NoticeStatusInbound.class);
    private WsOutbound wsOutbound;
    
    private String userNum;
    
    public NoticeStatusInbound(String userNum)
    {
        this.setuserNum(userNum);
    }
    
    @Override
    protected void onBinaryMessage(ByteBuffer message)
        throws IOException
    {
    
    }
    
    public void sendMsgToBrownser(String msg)
    {
        try
        {
            //System.out.println("sendMsgToBrownser : " + msg);
            wsOutbound.writeTextMessage(CharBuffer.wrap(msg));
        }
        catch (IOException e)
        {
        	log.error("IOException err");
        }
    }
    
    @Override
    protected void onTextMessage(CharBuffer message)
        throws IOException
    {
        //System.out.println(message.toString());
    }
    
    @Override
    protected void onOpen(WsOutbound outbound)
    {
        
        try
        {
            this.wsOutbound = outbound;
            outbound.writeTextMessage(CharBuffer.wrap("websocket is connected."));
        }
        catch (IOException e)
        {
        	log.error("IOException err");
        }
        //System.out.println("webSocket open. userId = " + this.userId);
    }
    
    @Override
    protected void onClose(int status)
    {
        NoticeStatusWSServlet.getMsgBounders().remove(this.userNum);
        //System.out.println("WebSocket close. userId = " + this.userId);
    }
    
    public String getuserNum()
    {
        return userNum;
    }
    
    public void setuserNum(String userNum)
    {
        this.userNum = userNum;
    }
    
}
