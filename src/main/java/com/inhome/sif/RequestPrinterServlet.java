package com.inhome.sif;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: liang-vmwin7-64
 * Date: 13-8-11
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 * Test by Jaman
 */
public class RequestPrinterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestPrinterServlet.print(request, "Sensitive Request");
    }

    public static void print(HttpServletRequest request, String title){
        System.out.println("---------------"+title+"------------------");
        System.out.println("Time: "+ new Date().toString() );
        System.out.println("URI: "+request.getRequestURI());
        try {
            System.out.println("QUERY: "+ URLDecoder.decode(request.getQueryString(), "UTF-8") );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        for(Enumeration headerNames = request.getHeaderNames();  headerNames.hasMoreElements(); ){
            String header = (String)headerNames.nextElement();
            String value = request.getHeader(header);
            System.out.println("(header)"+header+": "+value);
        }

        System.out.println("------------------------------------------------");
    }
}
