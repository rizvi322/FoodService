package com.foodservice.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ObjectRowMapper<E> {

       E mapRowToObject(ResultSet resultSet) throws SQLException;

}
