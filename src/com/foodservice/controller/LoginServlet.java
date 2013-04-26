package com.foodservice.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/21/13
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("admin") && password.equals("admin"))
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", request.getParameter("username"));
            response.sendRedirect("welcomeservlet");
        }
        else
        {
            request.setAttribute("error","Invalid Username or password. Go back and try again.");
            request.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("title", "Login Page");
        request.getRequestDispatcher("/WEB-INF/jsps/login.jsp").forward(request, response);

    }
}
