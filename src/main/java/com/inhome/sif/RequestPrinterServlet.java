package com.inhome.sif;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: liang-vmwin7-64
 * Date: 13-8-11
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
public class RequestPrinterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---------------Request Printer------------------");
        System.out.println("URI:"+request.getRequestURI());
        System.out.println("QUERY:"+request.getQueryString());

        for(Enumeration headerNames = request.getHeaderNames();  headerNames.hasMoreElements(); ){
            String header = (String)headerNames.nextElement();
            String value = request.getHeader(header);
            System.out.println("(header)"+header+":"+value);
        }

        System.out.print("------------------------------------------------");
    }
}
