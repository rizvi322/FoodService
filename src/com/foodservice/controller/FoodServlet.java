package com.foodservice.controller;
import com.foodservice.model.Food;
import com.foodservice.service.FoodList;
import com.foodservice.service.FoodListImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/22/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class FoodServlet extends HttpServlet {

    private FoodList foodList;

    public FoodServlet() {

        foodList = new FoodListImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        if(type.equals("add"))
        {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            if(name == null || name == "")
            {
                request.setAttribute("title","Food Add Page");
                request.setAttribute("error","Please correctly fill up the form.");
                request.getRequestDispatcher("/WEB-INF/jsps/addfood.jsp").forward(request, response);
            }
            else
            {
                Food food = new Food();
                food.setName(name);
                food.setDescription(description);
                foodList.add(food);
                response.sendRedirect("foodservlet?type=show");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        if(type.equals("show"))
        {
            List<Food> foods = foodList.showAll();
            request.setAttribute("title","Food List Page");
            request.setAttribute("foods",foods);
            request.getRequestDispatcher("/WEB-INF/jsps/foodlist.jsp").forward(request, response);
        }
        else if(type.equals("add"))
        {
            request.setAttribute("title","Food Add Page");
            request.getRequestDispatcher("/WEB-INF/jsps/addfood.jsp").forward(request, response);
        }
        else if(type.equals("remove"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            foodList.remove(id);
            response.sendRedirect("foodservlet?type=show");
        }
    }

}
