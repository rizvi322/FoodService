package com.foodservice.dao;

import com.foodservice.model.Meal;
import com.foodservice.model.MealAssign;
import com.foodservice.util.DatabaseTemplate;
import com.foodservice.util.ObjectRowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 5:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class MealDaoImpl implements MealDao {

    static Logger log = Logger.getLogger(FoodDaoImpl.class.getName());

    @Override
    public void add(Meal meal) {


        String sql = "INSERT INTO meallist(`items`) VALUES(?)";
        DatabaseTemplate.executeInsertQuery(sql,meal.getItems());
        log.info(new Date() + " : Successfully added a meal package.");
    }

    @Override
    public List<Meal> showAll() {

        List<Meal> meals = new ArrayList<Meal>();
        String sql = "SELECT * FROM meallist ";

        meals = DatabaseTemplate.queryForObject(sql, new ObjectRowMapper<Meal>() {
            @Override
            public Meal mapRowToObject(ResultSet resultSet) throws SQLException {

                Meal meal = new Meal();
                meal.setId(resultSet.getInt("id"));
                meal.setItems(resultSet.getString("items"));

                return meal;
            }
        });

        if(meals.size() != 0)
        {
            log.info(new Date() + " : Successfully returned the list of meals for the day.");
            return meals;
        }
        log.info(new Date() + " : Meal list is empty.");
        return null;
    }

    @Override
    public void remove(int id) {

        String sql = "DELETE FROM meallist WHERE id=" + id;
        DatabaseTemplate.execute(sql);
        log.info(new Date() + " : Successfully deleted meal package.");
    }

    @Override
    public void assign_meal(MealAssign mealAssign) {

        String sql = "INSERT INTO assign_meal(`meal_id`,`meal_time`,`description`,`for_date`) VALUES(?,?,?,?)";
        DatabaseTemplate.executeInsertQuery(sql, mealAssign.getMeal_id(),mealAssign.getMeal_time(),mealAssign.getDescription(), mealAssign.getFor_date());
        log.info(new Date() + " : Successfully assigned a meal.");
    }

    @Override
    public List<MealAssign> showMealAssign() {

        List<MealAssign> mealAssigns = new ArrayList<MealAssign>();
        String for_date = new SimpleDateFormat("dd-MMM-yy").format(new Date());
        String sql = "SELECT a.id, m.items, a.meal_time, a.description ";
                sql += "FROM assign_meal a JOIN meallist m ";
                sql += "ON(a.meal_id = m.id) ";
                sql += "WHERE a.for_date='" + for_date + "' ";
                sql += "ORDER BY a.id DESC";
        mealAssigns = DatabaseTemplate.queryForObject(sql, new ObjectRowMapper<MealAssign>() {
            @Override
            public MealAssign mapRowToObject(ResultSet resultSet) throws SQLException {

                MealAssign mealAssign = new MealAssign();
                mealAssign.setId(resultSet.getInt("id"));
                mealAssign.setMeal_items(resultSet.getString("items"));
                mealAssign.setMeal_time(resultSet.getString("meal_time"));
                mealAssign.setDescription(resultSet.getString("description"));
                return mealAssign;
            }
        });

        if(mealAssigns.size() != 0)
        {
            log.info(new Date() + " : Successfully returned the list of meals for the day.");
            return mealAssigns;
        }
        log.info(new Date() + " : Meal list is empty.");
        return null;
    }

    @Override
    public void removeAssign(int id) {

        String sql = "DELETE FROM assign_meal WHERE id=" + id;
        DatabaseTemplate.execute(sql);
        log.info(new Date() + " : Successfully deleted assigned meal.");
    }
}
