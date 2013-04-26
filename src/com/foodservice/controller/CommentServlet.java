package com.foodservice.controller;

import com.foodservice.model.Comment;
import com.foodservice.service.CommentList;
import com.foodservice.service.CommentListImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommentServlet extends HttpServlet {

    private CommentList commentList;

    public CommentServlet() {

        commentList = new CommentListImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String comm = request.getParameter("comment");
        String time = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
        if(name == null || name == "" || comm == null || comm == "")
        {
            response.sendRedirect("welcomeservlet?err=fill");
        }
        else
        {
            Comment comment = new Comment();
            comment.setName(name);
            comment.setComment(comm);
            comment.setTime(time);
            commentList.addComment(comment);
            response.sendRedirect("welcomeservlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
