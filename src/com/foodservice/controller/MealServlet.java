package com.foodservice.controller;

import com.foodservice.model.Food;
import com.foodservice.model.Meal;
import com.foodservice.model.MealAssign;
import com.foodservice.service.FoodListImpl;
import com.foodservice.service.MealList;
import com.foodservice.service.MealListImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/22/13
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class MealServlet extends HttpServlet {

    private MealList mealList;

    public MealServlet() {

        mealList = new MealListImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        if(type.equals("add"))
        {
            String items_arr[] = request.getParameterValues("items");
            if(items_arr == null || items_arr.length == 0)
            {
                request.setAttribute("error","Please correctly fill up the form.");
                request.setAttribute("title","Set Meal Page");
                request.getRequestDispatcher("/WEB-INF/jsps/mealform.jsp").forward(request, response);
            }
            else
            {
                String items = "";
                for(int i = 0; i < items_arr.length; i++)
                {
                    items += items_arr[i] + ",";
                }
                Meal meal = new Meal();
                meal.setItems(items);
                mealList.add(meal);
                response.sendRedirect("mealservlet?type=show_meallist");
            }
        }
        else if(type.equals("assign"))
        {
            int meal_id = Integer.parseInt(request.getParameter("meal_id"));
            String meal_time = request.getParameter("meal_time");
            String description = request.getParameter("description");
            if(meal_id == 0 || meal_time == null || meal_time == "")
            {
                request.setAttribute("error","Please correctly fill up the form.");
                request.setAttribute("title","Assign Meal Form Page");
                request.getRequestDispatcher("/WEB-INF/jsps/assignmeal.jsp").forward(request, response);
            }
            else
            {
                if(description == null || description.isEmpty())
                {
                    description = " ";
                }
                String for_date = new SimpleDateFormat("dd-MMM-yy").format(new Date());
                MealAssign mealAssign = new MealAssign();
                mealAssign.setMeal_id(meal_id);
                mealAssign.setMeal_time(meal_time);
                mealAssign.setDescription(description);
                mealAssign.setFor_date(for_date);
                mealList.assign_meal(mealAssign);
                response.sendRedirect("welcomeservlet");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        if(type.equals("add"))
        {
            List<Food> foods = new FoodListImpl().showAll();
            request.setAttribute("foods",foods);
            request.setAttribute("title","Set Meal Page");
            request.getRequestDispatcher("/WEB-INF/jsps/mealform.jsp").forward(request, response);
        }
        else if(type.equals("show_meallist"))
        {
            List<Meal> meals = mealList.showAll();
            if(meals == null) meals = new ArrayList<Meal>();
            request.setAttribute("meals",meals);
            request.setAttribute("title","Show Meal Page");
            request.getRequestDispatcher("/WEB-INF/jsps/meallist.jsp").forward(request, response);
        }
        else if(type.equals("remove"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            mealList.remove(id);
            response.sendRedirect("mealservlet?type=show");
        }
        else if(type.equals("assign"))
        {
            List<Meal> meals = mealList.showAll();
            if(meals == null) meals = new ArrayList<Meal>();
            request.setAttribute("meals",meals);
            request.setAttribute("title","Assign Meal Form Page");
            request.getRequestDispatcher("/WEB-INF/jsps/assignmeal.jsp").forward(request, response);
        }

        else if(type.equals("remove_assign"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            mealList.removeAssign(id);
            response.sendRedirect("welcomeservlet");
        }
    }
}
