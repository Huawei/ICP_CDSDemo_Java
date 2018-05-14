package com.huawei.cdsdemo.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.cert.X509Certificate;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;

import org.apache.commons.io.LineIterator;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.util.encoders.Base64;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.cdsdemo.service.CDSService;
import com.huawei.cdsdemo.session.UserSession;
import com.huawei.cdsdemo.util.CommonConstants.HeaderType;
import com.huawei.cdsdemo.util.LogUtils;
import com.huawei.cdsdemo.util.Md5Util;
import com.huawei.cdsdemo.util.StringUtils;

/**
 * 
 * <p>Title: </p>
 * <p>Description: http request method</p>
 * <p>Company: Huawei Tech. Co. Ltd.</p>
 * @version
 * @since 2017年10月13日
 */
public class Request
{
    /**
     * log
     */
    private static Logger log = LoggerFactory.getLogger(Request.class);
    
    /*
     * Max connections of connection pool
     */
    private static final int MAXCONNECTION = 500;
    
    /*
     * Connections of every route
     */
    private static final int MAXPERROUTE = 500;
    
    /*
     * Max request time of getting a connection from connection pool
     */
    private static final int REQUESTTIMEOUT = 2000;
    
    /*
     * Max time of a request
     */
    private static final int CONNECTIMEOUT = 2000;
    
    /*
     * Max time of waiting for response message
     */
    private static final int SOCKETIMEOUT = 11 * 1000;
    
    private static PoolingHttpClientConnectionManager connManager = null;
    
	private static CloseableHttpClient client = null;
    
    public static void init()
    {
        SSLContext sslContext;
        try
        {
            sslContext = SSLContext.getInstance("TLSv1.2");
            
            sslContext.init(null, new TrustManager[] {new CdsTrustManager()}, null);
            
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier()
            {
                public boolean verify(String arg0, SSLSession arg1)
                {
                    return true;
                }
                
                public void verify(String host, SSLSocket ssl)
                    throws IOException
                {
                }
                
                public void verify(String host, X509Certificate cert)
                    throws SSLException
                {
                }
                
                public void verify(String host, String[] cns, String[] subjectAlts)
                    throws SSLException
                {
                }
            });
            
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslsf)
                .build();
                
            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            
            connManager.setMaxTotal(MAXCONNECTION);
            connManager.setDefaultMaxPerRoute(MAXPERROUTE);
        }
        catch (RuntimeException e)
        {
            log.error("init connection pool failed");
            return;
        }
        catch (Exception e)
        {
            log.error("init connection pool failed");
            return;
        }
		client = getConnection();
    }
    
    private static CloseableHttpClient getConnection()
    {
    	RequestConfig restConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUESTTIMEOUT)
    			.setConnectTimeout(CONNECTIMEOUT)
    			.setSocketTimeout(SOCKETIMEOUT).build();
    	HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler()
        {   
            public boolean retryRequest(IOException exception, int executionCount,
                    HttpContext context)
            {
                if (executionCount >= 3)
                {
                   return false; 
                }
                if (exception instanceof NoHttpResponseException) 
                {
                    return true;  
                } 
                if (exception instanceof InterruptedIOException) 
                {
                    return false;
                }
                if (exception instanceof SSLHandshakeException) 
                {
                    return false;  
                }  
                if (exception instanceof UnknownHostException) 
                {
                    return false;  
                }  
                if (exception instanceof ConnectTimeoutException) 
                {
                    return false;  
                }  
                if (exception instanceof SSLException) 
                {
                    return false;  
                }
                
                HttpClientContext clientContext = HttpClientContext.adapt(context);  
                HttpRequest request = clientContext.getRequest();  
                if (!(request instanceof HttpEntityEnclosingRequest)) 
                {  
                    return true;  
                }  
                return false;  
            }
        };
    	CloseableHttpClient httpClient = HttpClients.custom()
    			.setConnectionManager(connManager).setDefaultRequestConfig(restConfig).setRetryHandler(retryHandler).build();
    	return httpClient;
    
    }
    
    /**
     * Send http's GET request
     * @param url:the address of the request
     * @param headers:the field is used to set the header of http request
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public static Map<String, Object> delete(String token, String url)
    {
    	
    	if (token==null || url== null || token.isEmpty() || url.isEmpty())
    	{
    		log.error("token or url is null.");
    		return null;
		}
    	
      //CloseableHttpClient client = getConnection();
        CloseableHttpResponse response = null;
        MyHttpDelete delete = null;
        Map<String, Object> result = null;
        try
        {
        	url = Normalizer.normalize(url, Form.NFKC);
            delete = new MyHttpDelete(url);
            
            setHeaders(token,delete,HeaderType.token);
            delete.setHeader("Content-Type", "application/json;charset=UTF-8");			
            response = client.execute(delete);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {               
                HttpEntity entity = response.getEntity();
                if (null != entity)
                {
                    String entityContent = EntityUtils.toString(entity,"UTF-8");
                    result = jsonToMap(entityContent);
                }   
            }
            else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            {
                String resultStr = "{\"returnDesc\":\"Authentication failure.\",\"returnCode\":\"300088\"}";
                result = jsonToMap(resultStr);
            }
            else
            {
                String resultStr = "{\"returnDesc\":\"Operation failed.\",\"returnCode\":\"1\"}";
                result = jsonToMap(resultStr);
            }
        }
        catch (UnsupportedEncodingException e)
    	{
    	    result = returnConnectError(e);
        } 
    	catch (ClientProtocolException e) 
    	{
    	   result =  returnConnectError(e);
        }
    	catch (IOException e) 
    	{
    	    result = returnConnectError(e);
        }
        finally
        {
            if (response != null)
            {
                try
                {
                    EntityUtils.consume(response.getEntity());
                }
                catch (IOException e)
                {
                    log.error("release connection failed");
                }
            }
        }
        return result;
        
    }
    
    /**
     * Send http's GET request
     * @param url:the address of the request
     * @param headers:the field is used to set the header of http request
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public static Map<String, Object> get(String token, String url)
    {
    	if (token==null || url== null || token.isEmpty() || url.isEmpty())
    	{
    		log.error("token or url is null.");
    		return null;
		}
    	
        //CloseableHttpClient client = getConnection();
        CloseableHttpResponse response = null;
        HttpGet get = null;
        Map<String, Object> result = null;
        
        try
        {                       
            url = Normalizer.normalize(url, Form.NFKC);
            get = new HttpGet(url);
          
            setHeaders(token,get,HeaderType.token);
            get.setHeader("Content-Type", "application/json");
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
            	 HttpEntity entity = response.getEntity();
                 if (null != entity)
                 {
                     String entityContent = EntityUtils.toString(entity,"UTF-8");
                     result = jsonToMap(entityContent);
                 }
            }
            else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            {
                String resultStr = "{\"returnDesc\":\"Authentication failure.\",\"returnCode\":\"300088\"}";
                result = jsonToMap(resultStr);
            }
            else
            {
                String resultStr = "{\"returnDesc\":\"Operation failed.\",\"returnCode\":\"1\"}";
                result = jsonToMap(resultStr);
            }
        }
        catch (SSLHandshakeException | SocketException | NoHttpResponseException e)
    	{
        	 result = returnConnectError(e);
    	}
        catch (Exception e)
        {
        	 result = returnConnectError(e);
        }
        finally
        {
            if (response != null)
            {
                try
                {
                    EntityUtils.consume(response.getEntity());
                }
                catch (IOException e)
                {
                    log.error("release connection failed");
                }
            }
        }
        
        return result;
    }
    
    /**
     * Send http's POST request
     * @param url:the address of the request
     * @param entityParams:the paramters of entity
     * @param headers:the field is used to set the header of http request
     * @return
     */
    public static Map<String, Object> post(String token, String url, Object entityParams)
    {   	
    	if (token==null || url== null || token.isEmpty() || url.isEmpty())
    	{
    		log.error("token or url is null.");
    		return null;
		}
        
        Map<String, Object> result = null;
        HttpPost post = null;
        //CloseableHttpClient client = getConnection();
        CloseableHttpResponse response = null;
        try
        {            
            url = Normalizer.normalize(url, Form.NFKC);
            post = new HttpPost(url);
            	
            setHeaders(token,post,HeaderType.token);
            post.setHeader("Content-Type", "application/json;charset=UTF-8");    		            
            if (null != entityParams)
            {
                String jsonString = beanToJson(entityParams);
                HttpEntity entity = new StringEntity(jsonString);
                post.setEntity(entity);
            }
            
            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                HttpEntity entity = response.getEntity();
                if (null != entity)
                {
                    String entityContent = EntityUtils.toString(entity,"UTF-8");
                    result = jsonToMap(entityContent);
                }  
            }
            else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            {
                String resultStr = "{\"returnDesc\":\"Authentication failure.\",\"returnCode\":\"300088\"}";
                result = jsonToMap(resultStr);
            }
            else
            {
                String resultStr = "{\"returnDesc\":\"Operation failed.\",\"returnCode\":\"1\"}";
                result = jsonToMap(resultStr);
            }
        }
    	catch (UnsupportedEncodingException e) 
    	{
            result = returnConnectError(e);
        } catch (ClientProtocolException e)
    	{
            result = returnConnectError(e);
        }
    	catch (IOException e) 
    	{
    	    result =  returnConnectError(e);
        }
        finally
        {
            if (response != null)
            {
                try
                {
                    EntityUtils.consume(response.getEntity());
                }
                catch (IOException e)
                {
                    log.error("release connection failed");
                }
            }
        }
        return result;
    }
    
    /**
     * Send http's POST request
     * @param url:the address of the request
     * @param entityParams:the paramters of entity
     * @param headers:the field is used to set the header of http request
     * @return
     */
    public static Map<String, Object> oncePost(String token, String url)
    {
    	if (token==null || url== null || token.isEmpty() || url.isEmpty())
    	{
    		log.error("token or url is null.");
    		return null;
		}
    	
        Map<String, Object> result = null;
        HttpPost post = null;
      //CloseableHttpClient client = getConnection();
        CloseableHttpResponse response = null;
        try
        {   
            url = Normalizer.normalize(url, Form.NFKC);
            post = new HttpPost(url);

            setHeaders(token,post,HeaderType.token);
            post.setHeader("Content-Type", "application/json;charset=UTF-8");    		
            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                Header[] authH = response.getHeaders("WWW-Authenticate");
                
                String authHeader = authH[0].getValue();
                
                Matcher matcher = Pattern.compile("nonce=\"(.+?)\"").matcher(authHeader);
                
                String nonce = CDSService.match(matcher);
                
                result = new HashMap<>();
                result.put("data", nonce);
                result.put("returnCode", 0);
                result.put("returnDesc", "Successful operation.");
            }
            else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            {
                String resultStr = "{\"returnDesc\":\"Authentication failure.\",\"returnCode\":\"300088\"}";
                result = jsonToMap(resultStr);
            }
            else
            {
                String resultStr = "{\"returnDesc\":\"Operation failed.\",\"returnCode\":\"1\"}";
                result = jsonToMap(resultStr);
            }
        }
        catch (UnsupportedEncodingException e) 
    	{
            result = returnConnectError(e);
        } catch (ClientProtocolException e)
    	{
            result = returnConnectError(e);
        }
    	catch (IOException e) 
    	{
    	    result =  returnConnectError(e);
        }
        finally
        {
            if (response != null)
            {
                try
                {
                    EntityUtils.consume(response.getEntity());
                }
                catch (IOException e)
                {
                    log.error("release connection failed");
                }
            }
        }
        return result;
    }
    
    /**
     * Send http's POST request
     * @param url:the address of the request
     * @param entityParams:the paramters of entity
     * @param headers:the field is used to set the header of http request
     * @return
     */
    public static Map<String, Object> loginPost(String userId, String password, String url, String userNumber)
    {
    	if (userId==null || url== null || password== null || userNumber== null)
    	{
    		log.error("para is null.");
    		return null;
		}
    	
        Map<String, Object> result = null;
        HttpPost post = null;
        //CloseableHttpClient client = getConnection();
        //CloseableHttpClient clients = getConnection();
        CloseableHttpResponse response = null;
        try
        {
            url = Normalizer.normalize(url, Form.NFKC);
        	post = new HttpPost(url);
        	
            setHeaders(userId, post, HeaderType.userId);                
            post.setHeader("Content-Type", "application/json;charset=UTF-8");   
            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                HttpEntity entity = response.getEntity();
                if (null != entity)
                {
                    String entityContent = EntityUtils.toString(entity,"UTF-8");
                    result = jsonToMap(entityContent);
                }  
            }
            else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            {
                String resultStr = "{\"returnDesc\":\"Authentication failure.\",\"returnCode\":\"300088\"}";
                result = jsonToMap(resultStr);
            }
            else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED)
            {
                Header[] authH = response.getHeaders("WWW-Authenticate");
                
                String authHeader = authH[0].getValue();
                
                Matcher matcher = Pattern.compile("auth-name=\"(.+?)\"").matcher(authHeader);
                String auth_name = match(matcher);
                matcher = Pattern.compile("realm=\"(.+?)\"").matcher(authHeader);
                String realm = match(matcher);
                
                matcher = Pattern.compile("nonce=\"(.+?)\"").matcher(authHeader);
                
                String nonce = match(matcher);
                
                String challenge = authHeader.indexOf("Digest") < 0 ? "Basic" : "Digest";
                
                String namePastr = auth_name + ":" + password;
                String namRealmPasStr = auth_name + ":" + realm + ":" + password;
                String methodUri = "POST" + ":" + "/login/sc";
                String HA1 = Md5Util.caculateStringMd5Value(namRealmPasStr);
                String HA2 = Md5Util.caculateStringMd5Value(methodUri);
                String secondRequest = Md5Util.caculateStringMd5Value(HA1 + ":" + nonce + ":" + HA2);
                
                String credentials = new String(Base64.encode(namePastr.getBytes("utf-8")), "utf-8");
                String authorizationStr =
                    challenge.equals("Digest")
 ? "Digest username=" + auth_name + ", realm=" + realm + ", nonce="
                        + nonce + ", uri=/login/sc, response=" + secondRequest
                        : "Basic " + credentials;
                        
                post = new HttpPost(url);  
                
                setHeaders(authorizationStr, post, HeaderType.authorizationStr);  
                post.setHeader("Content-Type", "application/json");               
                response = client.execute(post);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                {
                    InputStream is = response.getEntity().getContent();
                    BufferedReader in = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    StringBuffer buffer = new StringBuffer();

                    LineIterator lineItr = new LineIterator(in);
                    while (lineItr.hasNext())
                    {
                        buffer.append(lineItr.next());
                    }
                    
                    
                    String strs = buffer.toString();
                    int start = strs.indexOf("<AccessToken>");
                    int end = strs.indexOf("</AccessToken>");
                    String token = strs.substring(start + 13, end);
                    UserSession user = new UserSession(auth_name);
                    user.setToken(token);
                    user.setUserNumber(userNumber);
                    
                    CDSService.setUserSession(auth_name, user);
                    
                    result = new HashMap<>();
                    result.put("data", token);
                    result.put("returnCode", 0);
                    result.put("returnDesc", "Successful operation.");
                }
                else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
                {
                    String resultStr = "{\"returnDesc\":\"Authentication failure.\",\"returnCode\":\"300088\"}";
                    result = jsonToMap(resultStr);
                }
                else
                {
                    String resultStr = "{\"returnDesc\":\"Operation failed.\",\"returnCode\":\"1\"}";
                    result = jsonToMap(resultStr);
                }
            }
            else
            {
                String resultStr = "{\"returnDesc\":\"Operation failed.\",\"returnCode\":\"1\"}";
                result = jsonToMap(resultStr);
            }
        }
        catch (UnsupportedEncodingException e) 
    	{
            result = returnConnectError(e);
        } catch (ClientProtocolException e)
    	{
            result = returnConnectError(e);
        }
    	catch (IOException e) 
    	{
    	    result =  returnConnectError(e);
        }
        finally
        {
            if (response != null)
            {
                try
                {
                    EntityUtils.consume(response.getEntity());
                }
                catch (IOException e)
                {
                    log.error("release connection failed");
                }
            }
        }
        return result;
    }
    
    /**
     * Send http's PUT request
     * @param url:the address of the request
     * @param entityParams:the paramters of entity 
     * @param headers:the field is used to set the header of http request
     * @return
     */
    public static Map<String, Object> put(String token, String url, Object entityParams)
    {
    	   	
    	if (token==null || url== null || token.isEmpty() || url.isEmpty())
    	{
    		log.error("token or url is null.");
    		return null;
		}
    	
        //CloseableHttpClient client = getConnection();
        CloseableHttpResponse response = null;
        HttpPut put = null;
        Map<String, Object> result = null;
        try
        {                      
            url = Normalizer.normalize(url, Form.NFKC);
            put = new HttpPut(url);
            
            setHeaders(token,put,HeaderType.token);
            put.setHeader("Content-Type", "application/json;charset=UTF-8");    		           
            if (null != entityParams)
            {
                String jsonString = beanToJson(entityParams);
                HttpEntity entity = new StringEntity(jsonString);
                put.setEntity(entity);
            }
            
            response = client.execute(put);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                HttpEntity entity = response.getEntity();
                if (null != entity)
                {
                    String entityContent = EntityUtils.toString(entity,"UTF-8");
                    result = jsonToMap(entityContent);
                }
                               
            }
            else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN)
            {
                String resultStr = "{\"returnDesc\":\"Authentication failure.\",\"returnCode\":\"300088\"}";
                result = jsonToMap(resultStr);
            }
            else
            {
                String resultStr = "{\"returnDesc\":\"Operation failed.\",\"returnCode\":\"1\"}";
                result = jsonToMap(resultStr);
            }
        }
        catch (UnsupportedEncodingException e)
    	{
        	 result =  returnConnectError(e);
        } 
    	catch (ClientProtocolException e)
    	{
    		 result =  returnConnectError(e);
        } 
    	catch (IOException e)
    	{
    		 result =  returnConnectError(e);
        }
        finally
        {
            if (response != null)
            {
                try
                {
                    EntityUtils.consume(response.getEntity());
                }
                catch (IOException e)
                {
                    log.error("release connection failed");
                }
            }
        }
        return result;
    }
    
    /**
     * Change object to json-string
     * @param object
     * @return
     * @throws IOException
     */
    public static String beanToJson(Object object)
    {
              
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        JsonGenerator gen = null;
        String json = "";
        try 
        {
            gen = new JsonFactory().createJsonGenerator(writer);
        
            mapper.writeValue(gen, object);
            json = writer.toString();
        } 
        catch (IOException e) 
        {
            log.error("object to json string failed");
        }
        finally
        {
            if(gen != null)
            {
                try 
                {
                    gen.close();
                } 
                catch (IOException e) 
                {
                	log.error("close Json generator failed");
                }
            }
            try {
                writer.close();
            } catch (IOException e) {
            	log.error("close StringWriter failed");
            }
            
        }
        return json;
    }
    
    /**
     * json string to map
     * @param json
     * @return
     */
    @SuppressWarnings("unchecked")
    public static HashMap<String, Object> jsonToMap(String json)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> result;
        try
        {
            result = objectMapper.readValue(json, HashMap.class);
            return result;
        }
        catch (JsonParseException e)
        {
            log.error("catch JsonParseException");
            return null;
        }
        catch (JsonMappingException e)
        {
            log.error("catch JsonMappingException");
            return null;
        }
        catch (IOException e)
        {
            log.error("catch IOException");
            return null;
        }
    }
    
    public static String match(Matcher matcher)
    {
        while (matcher.find())
        {
            return matcher.group(1);
        }
        return "";
    }
    /**
     * header set mothed abstract 
     * 
     * @param identityMark
     * @param httpMethod
     */
    private static void setHeaders(String header, HttpRequestBase httpMethod, int headerType)
    {
        if (!StringUtils.isNullOrBlank(header))
        {
        	String str = checkHeader(header);
        	if (HeaderType.token == headerType)
        	{
        		httpMethod.setHeader("Authorization", "Basic " + str);
			}
        	else if(HeaderType.userId == headerType)
			{
        		httpMethod.setHeader("Authorization", "Digest username=" + str + ", algorithm=MD5");
			}
        	else if(HeaderType.authorizationStr == headerType)
        	{
        		httpMethod.setHeader("Authorization", str);
        	}
        	else
        	{
        		 log.error("headerType error");
        	}
        }
    }
      
    //不能包含 \r \n
    public static String checkHeader (String header)
    {
       header = Normalizer.normalize(header, Form.NFKC);
       String replaceAll = header.replaceAll("\r", "")
             .replaceAll("\n", "");
       return replaceAll;
    }
    //返回ConnectotError信息 
    private static HashMap<String, Object>  returnConnectError(Exception e)
    {
        log.error("request to server failed: \r\n {}", LogUtils.encodeForLog(e.getMessage()));
        String resultStr = "{\"returnDesc\":\"Operation failed.\",\"returnCode\":\"1\"}";
        return jsonToMap(resultStr);
    }    
}
