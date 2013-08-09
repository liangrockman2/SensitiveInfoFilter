package com.inhome.sif;

import org.apache.http.examples.client.ClientConnectionRelease;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liang
 * Date: 13-8-6
 * Time: 上午11:10
 * To change this template use File | Settings | File Templates.
 */
public class SensitiveInfoFilterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //Query String参数
        Map mapparam = new HashMap();
        for( Enumeration e = request.getParameterNames(); e.hasMoreElements(); ){
             String paramName = (String)e.nextElement();
             String paramValue = request.getParameter(paramName);
             out.println("<div>" + paramName+ ":" + request.getParameter(paramName) + "</div>");
             mapparam.put(paramName,paramValue);
        }

        //遍历Query String参数
        System.out.println("Query String参数：");
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

        //Http报头
        Map mapHeader = new HashMap();
        System.out.println("Http报头内容：");
        for(Enumeration e = request.getHeaderNames(); e.hasMoreElements(); ){
            String headerName = (String)e.nextElement();
            String headerValue = request.getHeader(headerName);
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
            System.out.println( key + " ： " + value );
        }



        FilterList fl = new FilterList();
        for(Iterator<String> it = fl.list.iterator(); it.hasNext(); ){
            String s = it.next();
           /* String newRef = referer.replace(s, "123");
            System.out.println(newRef);*/
        }

        try {
            ClientConnectionRelease.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
