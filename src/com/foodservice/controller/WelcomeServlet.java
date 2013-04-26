package com.foodservice.controller;

import com.foodservice.model.Comment;
import com.foodservice.model.MealAssign;
import com.foodservice.service.CommentList;
import com.foodservice.service.CommentListImpl;
import com.foodservice.service.MealList;
import com.foodservice.service.MealListImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/22/13
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class WelcomeServlet extends HttpServlet {

    private CommentList commentList;
    private MealList mealList;

    public WelcomeServlet() {

        mealList = new MealListImpl();
        commentList = new CommentListImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<MealAssign> mealAssignList = mealList.showMealAssign();
        if(mealAssignList == null) mealAssignList = new ArrayList<MealAssign>();
        List<Comment> comments = commentList.showComments();
        if(comments == null) comments = new ArrayList<Comment>();
        request.setAttribute("title","Meal assign list");
        request.setAttribute("meals",mealAssignList);
        request.setAttribute("commentList",comments);
        try
        {
            if(request.getParameter("err").equals("fill"))
            {
                request.setAttribute("error","Please correctly fill up the form.");
            }
        }
        catch (NullPointerException e){}
        request.getRequestDispatcher("/WEB-INF/jsps/assign.jsp").forward(request, response);

    }
}
