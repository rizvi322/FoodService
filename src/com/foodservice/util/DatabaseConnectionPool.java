package com.foodservice.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseConnectionPool {

    //static Logger log = Logger.getLogger(DatabaseConnectionPool.class.getName());

    private static DataSource dataSource;
    private static final String DRIVER_NAME;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {

        final ResourceBundle config = ResourceBundle.getBundle("db");
        DRIVER_NAME = config.getString("jdbc.driver");
        URL = config.getString("jdbc.url.address");
        USERNAME = config.getString("db.user");
        PASSWORD = config.getString("db.password");

        try{
            dataSource = setUpDataSource();
        }
        catch (PropertyVetoException pve)
        {
            throw new RuntimeException();
        }

    }

    public static Connection getConnection() throws SQLException
    {

        return dataSource.getConnection();
    }

    public static ComboPooledDataSource setUpDataSource() throws PropertyVetoException
    {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(DRIVER_NAME);
        comboPooledDataSource.setJdbcUrl(URL);
        comboPooledDataSource.setUser(USERNAME);
        comboPooledDataSource.setPassword(PASSWORD);

        comboPooledDataSource.setMinPoolSize(5);
        comboPooledDataSource.setAcquireIncrement(5);
        comboPooledDataSource.setMaxPoolSize(20);

        return comboPooledDataSource;
    }

}
