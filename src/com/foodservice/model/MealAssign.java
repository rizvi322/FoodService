package com.foodservice.model;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/22/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class MealAssign {

    private int id;
    private int meal_id;
    private String meal_items;
    private String meal_time;
    private String description;
    private String for_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    public String getMeal_time() {
        return meal_time;
    }

    public void setMeal_time(String meal_time) {
        this.meal_time = meal_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeal_items() {
        return meal_items;
    }

    public void setMeal_items(String meal_items) {
        this.meal_items = meal_items;
    }

    public String getFor_date() {
        return for_date;
    }

    public void setFor_date(String for_date) {
        this.for_date = for_date;
    }
}
