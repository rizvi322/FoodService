package com.foodservice.service;

import com.foodservice.model.Food;

import java.util.List;
import com.foodservice.dao.FoodDao;
import com.foodservice.dao.FoodDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/22/13
 * Time: 10:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class FoodListImpl implements FoodList {

    private FoodDao foodDao;

    public FoodListImpl() {

        foodDao = new FoodDaoImpl();
    }

    @Override
    public void add(Food food) {
        foodDao.add(food);
    }

    @Override
    public List<Food> showAll() {
        return foodDao.showAll();
    }

    @Override
    public void remove(int id) {
        foodDao.remove(id);
    }
}