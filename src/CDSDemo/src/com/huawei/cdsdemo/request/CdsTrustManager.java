package com.huawei.cdsdemo.request;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * <p>Title: 默认信任服务端证书 </p>
 * <p>Description: </p>
 * <pre></pre>
 * <p>Copyright: Copyright (c) 2016</p>
 * @version V1.0 
 * @since 2017-2-24
 */
public class CdsTrustManager implements X509TrustManager 
{
    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException
    {
    }
    
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException
    {
    }
    
    @Override
    public X509Certificate[] getAcceptedIssuers()
    {
        return new X509Certificate[0];
    }
}
