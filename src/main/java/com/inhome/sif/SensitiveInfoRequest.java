package com.inhome.sif;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liang-vmwin7-64
 * Date: 13-8-11
 * Time: 上午9:04
 * To change this template use File | Settings | File Templates.
 */
public class SensitiveInfoRequest {
    String uri;
    String queryStr;
    Map<String, Object> mapHeader;

    public SensitiveInfoRequest(String uri, String queryStr, Map mapHeader ){
        this.uri = uri;
        this.queryStr = queryStr;
        this.mapHeader = mapHeader;
    }

    public void send(){
        HttpClient httpclient = new DefaultHttpClient();

        try {
            URIBuilder builder  = new URIBuilder(this.uri);
            builder.setQuery(this.queryStr);
            builder.setScheme("http");

            URI uri = builder.build();
            HttpGet get = new HttpGet(uri);

            //添加HTTP Header
            for(String key :  mapHeader.keySet()){
                String value = (String)mapHeader.get(key);
                get.setHeader(key, value);
            }

            HttpResponse response = httpclient.execute(get) ;

            System.out.println("-----------Sensitive Response-----------");
            System.out.println(response.getStatusLine());
            System.out.println("----------------------------------------");

        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            httpclient.getConnectionManager().shutdown();
        }


    }
}
