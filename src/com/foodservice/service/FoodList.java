package com.foodservice.service;

import com.foodservice.model.Food;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FoodList {

    public void add(Food food);
    public List<Food> showAll();
    public void remove(int id);
}
