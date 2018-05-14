package com.huawei.cdsdemo.request;

import java.net.URI;
import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * 
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: Huawei Tech. Co. Ltd.</p>
 * @version
 */
public class MyHttpDelete extends HttpEntityEnclosingRequestBase {
	
	public static final String METHOD_NAME ="DELETE";

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return METHOD_NAME;
	}

	public MyHttpDelete(final String uri) {
        super();
        String normalize = Normalizer.normalize(uri, Form.NFKC);
        setURI(URI.create(normalize));
    }

    public MyHttpDelete(final URI uri) {
        super();
        setURI(uri);
    }

    public MyHttpDelete() {
        super();
    }

}
