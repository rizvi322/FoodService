package com.foodservice.util;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseTemplate {

    //static Logger log = Logger.getLogger(DatabaseConnectionPool.class.getName());

    public static void execute(String query){

        Connection connection = null;
        Statement statement = null;

        try
        {

            connection = DatabaseConnectionPool.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException();
        }
        finally
        {

            try
            {
                statement.close();
            }
            catch (SQLException e1)
            {
                closeConnection(connection);
                throw new RuntimeException();
            }
        }
    }

    public static <E> List<E> queryForObject(String query, ObjectRowMapper<E> eObjectRowMapper){

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<E> listOfE = new ArrayList<E>();

        try
        {

            connection = DatabaseConnectionPool.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                listOfE.add(eObjectRowMapper.mapRowToObject(resultSet));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException();
        }
        finally
        {
            try
            {
                resultSet.close();
                statement.close();
            }
            catch (SQLException e1)
            {
                closeConnection(connection);
                throw new RuntimeException();
            }
        }
        return listOfE;
    }

    public static void executeInsertQuery(String query, Object... parameters)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try
        {
            connection = DatabaseConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(query);

            int i = 1;
            for(Object parameter : parameters)
            {
                if(parameter instanceof String)
                {
                    preparedStatement.setString(i, (String) parameter);
                }
                else if(parameter instanceof Integer)
                {
                    preparedStatement.setInt(i, (Integer) parameter);
                }
                else if(parameter instanceof Long)
                {
                    preparedStatement.setLong(i, (Long) parameter);
                }
                else if(parameter instanceof Float)
                {
                    preparedStatement.setFloat(i, (Float) parameter);
                }
                else if(parameter instanceof Double)
                {
                    preparedStatement.setDouble(i, (Double) parameter);
                }
                else if(parameter instanceof Date)
                {
                    preparedStatement.setDate(i, (Date) parameter);
                }
                else if(parameter instanceof Blob)
                {
                    preparedStatement.setBlob(i, (Blob) parameter);
                }
                i++;
            }
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException();
        }
        finally
        {

            try
            {
                preparedStatement.close();
            }
            catch (SQLException e1)
            {
                closeConnection(connection);
                throw new RuntimeException();
            }
        }
    }

    private static void closeConnection(Connection connection)
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException();
        }
    }

}
