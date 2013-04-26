package com.foodservice.service;

import com.foodservice.model.Meal;
import com.foodservice.model.MealAssign;
import com.foodservice.dao.MealDao;
import com.foodservice.dao.MealDaoImpl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class MealListImpl implements MealList {

    private MealDao mealDao;

    public MealListImpl() {

        mealDao = new MealDaoImpl();
    }

    @Override
    public void add(Meal meal) {

        mealDao.add(meal);
    }

    @Override
    public List<Meal> showAll() {

        return mealDao.showAll();
    }

    @Override
    public void remove(int id) {
        mealDao.remove(id);
    }

    @Override
    public void assign_meal(MealAssign mealAssign) {
        mealDao.assign_meal(mealAssign);
    }

    @Override
    public List<MealAssign> showMealAssign() {

        return mealDao.showMealAssign();
    }

    @Override
    public void removeAssign(int id) {

        mealDao.removeAssign(id);
    }
}
