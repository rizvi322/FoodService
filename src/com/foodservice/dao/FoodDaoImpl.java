package com.foodservice.dao;

import com.foodservice.model.Food;
import com.foodservice.util.DatabaseTemplate;
import com.foodservice.util.ObjectRowMapper;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class FoodDaoImpl implements FoodDao {

    static Logger log = Logger.getLogger(FoodDaoImpl.class.getName());

    @Override
    public void add(Food food) {

        String sql = "INSERT INTO foodlist(`name`,`description`) VALUES(?,?)";
        DatabaseTemplate.executeInsertQuery(sql, food.getName(), food.getDescription());
        log.info(new Date() + " : Successfully added new food.");
    }

    @Override
    public List<Food> showAll() {

        List<Food> foods = new ArrayList<Food>();
        String sql = "SELECT * FROM foodlist";
        foods = DatabaseTemplate.queryForObject(sql, new ObjectRowMapper<Food>() {
            @Override
            public Food mapRowToObject(ResultSet resultSet) throws SQLException {

                Food food = new Food();
                food.setId(resultSet.getInt("id"));
                food.setName(resultSet.getString("name"));
                food.setDescription(resultSet.getString("description"));

                return food;
            }
        });

        if(foods.size() != 0)
        {
            log.info(new Date() + " : Successfully returned the list of foods.");
            return foods;
        }
        log.info(new Date() + " : Food list is empty.");
        return null;
    }

    @Override
    public void remove(int id) {

        String sql = "DELETE FROM foodlist WHERE id=" + id;
        DatabaseTemplate.execute(sql);
        log.info(new Date() + " : Successfully deleted food.");
    }
}
