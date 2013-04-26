package com.foodservice.service;

import com.foodservice.model.Meal;
import com.foodservice.model.MealAssign;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/22/13
 * Time: 10:26 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MealList{

    public void add(Meal meal);
    public List<Meal> showAll();
    public void remove(int id);
    public void assign_meal(MealAssign mealAssign);
    public List<MealAssign> showMealAssign();
    public void removeAssign(int id);

}
