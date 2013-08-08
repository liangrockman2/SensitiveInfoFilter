package com.inhome.sif;

import org.apache.http.examples.client.ClientConnectionRelease;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

        try {
            ClientConnectionRelease.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
