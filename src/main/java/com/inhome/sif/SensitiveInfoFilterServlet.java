package com.inhome.sif;

import org.apache.http.examples.client.ClientConnectionRelease;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.*;
import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 13-8-6
 * Time: 上午11:10
 * To change this template use File | Settings | File Templates.
 */
public class SensitiveInfoFilterServlet extends HttpServlet {
    FilterList filterList = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);    //To change body of overridden methods use File | Settings | File Templates.
        filterList = new FilterList();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String trackingServer = this.getInitParameter("trackingServer");

        RequestPrinterServlet.print(request, "Original Request");

        String uri = request.getRequestURI();
        String queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
        queryString = filterQueryCookieString(queryString, "&");
        queryString = URLEncoder.encode(queryString, "UTF-8");

        //Query String参数
        Map<String, Object> mapparam = new HashMap<String, Object>();
        for( Enumeration e = request.getParameterNames(); e.hasMoreElements(); ){
             String paramName = (String)e.nextElement();
             String paramValue = request.getParameter(paramName);
             //out.println("<div>" + paramName+ ":" + request.getParameter(paramName) + "</div>");
             mapparam.put(paramName,paramValue);
        }

        //遍历Query String参数
        //System.out.println("Query String参数：");
        /*
        Set paramKeys = mapparam.keySet();
        Iterator paramItr = paramKeys.iterator();
        while ( true ){
            if ( false == paramItr.hasNext() ){
                break;
            }
            String key = ( String )paramItr.next();
            String value = ( String )mapparam.get( key );
            System.out.println( key + " ： " + value );
        }
        */

        //Http报头
        Map mapHeader = new HashMap();
        //System.out.println("Http报头内容：");
        for(Enumeration e = request.getHeaderNames(); e.hasMoreElements(); ){
            String headerName = (String)e.nextElement();
            String headerValue = request.getHeader(headerName);

            //过滤cookie
            if(  "cookie".equalsIgnoreCase( headerName ) ){
                headerValue = this.filterQueryCookieString(headerValue, "; ");
            }else if( "referer".equalsIgnoreCase( headerName ) ){
                String[] parts = headerValue.split("\\?");
                if(parts.length > 1 && parts[1].trim().length() > 0){
                    parts[1] = URLDecoder.decode(parts[1], "UTF-8");
                    parts[1] = filterQueryCookieString(parts[1], "&");
                    parts[1] = URLEncoder.encode(parts[1], "UTF-8");

                    if(parts[1].length() > 0){
                        headerValue = parts[0]+"?"+parts[1];
                    }
                }
            } else if( "host".equalsIgnoreCase( headerName ) ){
                continue;
            }
           // System.out.println(headerName + ":" + headerValue);
            mapHeader.put(headerName,headerValue);
        }

        //遍历Http报头
        Set headerKeys = mapHeader.keySet();
        Iterator headerItr = headerKeys.iterator();
        while ( true ){
            if ( false == headerItr.hasNext() ){
                break;
            }
            String key = ( String )headerItr.next();
            String value = ( String )mapHeader.get( key );
            //System.out.println( key + " ： " + value );
        }



        FilterList fl = new FilterList();
        for(Iterator<String> it = fl.list.iterator(); it.hasNext(); ){
            String s = it.next();
           /* String newRef = referer.replace(s, "123");
            System.out.println(newRef);*/
        }

        //发送Http请求
        String requestUri =  request.getScheme()+ "://" + trackingServer + uri;
        SensitiveInfoRequest sensitiveRequest = new SensitiveInfoRequest(requestUri, queryString, mapHeader);
        sensitiveRequest.send();

        response.getWriter().write("request send");
    }

    //对 Query 或Cookie进行过滤
    private String filterQueryCookieString(String queryString, String delimiter){
        List<String>  resultList = new ArrayList<String>();
        for( String param : queryString.split(delimiter) ){
            if( !matchesFilterList(param) ){
                resultList.add(param);
            }
        }

        //进行重新拼接
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<resultList.size(); i++){
            String p =  resultList.get(i);
            if(p.trim().length() > 0){
                if(i > 0){
                    stringBuilder.append(delimiter);
                }
                stringBuilder.append(p);
            }
        }

        return   stringBuilder.toString();
    }

    private boolean matchesFilterList(String str){
        boolean matches = false;
        for(String filter : this.filterList.list){
            if( str.matches(filter) ){
                matches = true;
                break;
            }
        }

        return  matches;
    }
}
