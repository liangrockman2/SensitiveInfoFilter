package com.inhome.sif;

import org.apache.http.examples.client.ClientConnectionRelease;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

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
        for( Enumeration e = request.getParameterNames(); e.hasMoreElements(); ){
             String paramName = (String)e.nextElement();
             out.println("<div>" + paramName+ ":" + request.getParameter(paramName) + "</div>");
        }

        String referer = request.getHeader("Referer");
        ArrayList cookieList = new ArrayList();
        HttpServletRequest rep = request;

        Cookie[] cookies = rep.getCookies();
        for(Cookie s : cookies){
            String cookieValue = (String)s.getValue();
            cookieList.add(cookieValue);
            out.print(cookieValue);

        }

        /*
        ArrayList cookieList2 = new ArrayList();
        for(int i = 0; i < e.length; i++){


        }
               */

       /* String[] a = {"1","2"};
        for(String s : a){

        }           */


        try {
            ClientConnectionRelease.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
