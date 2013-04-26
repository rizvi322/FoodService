package com.foodservice.dao;

import com.foodservice.model.Meal;
import com.foodservice.model.MealAssign;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MealDao {

    public void add(Meal meal);
    public List<Meal> showAll();
    public void remove(int id);
    public void assign_meal(MealAssign mealAssign);
    public List<MealAssign> showMealAssign();
    public void removeAssign(int id);

}
