package com.foodservice.dao;

import com.foodservice.model.Comment;
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
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentDaoImpl implements CommentDao {

    static Logger log = Logger.getLogger(FoodDaoImpl.class.getName());

    @Override
    public void addComment(Comment comment) {

        String sql = "INSERT INTO comments(`name`,`comment`,`comment_time`) VALUES(?,?,?)";
        DatabaseTemplate.executeInsertQuery(sql, comment.getName(),comment.getComment(),comment.getTime());
        log.info(new Date() + " : Successfully added new comment.");
    }

    @Override
    public List<Comment> showComments() {
        List<Comment> comments = new ArrayList<Comment>();
        String sql = "SELECT * FROM comments ORDER BY id DESC LIMIT 5";
        comments = DatabaseTemplate.queryForObject(sql, new ObjectRowMapper<Comment>() {
            @Override
            public Comment mapRowToObject(ResultSet resultSet) throws SQLException {

                Comment comment = new Comment();
                comment.setName(resultSet.getString("name"));
                comment.setComment(resultSet.getString("comment"));
                comment.setTime(resultSet.getString("comment_time"));

                return comment;
            }
        });

        if(comments.size() != 0)
        {
            log.info(new Date() + " : Successfully returned the list of comments.");
            return comments;
        }
        log.info(new Date() + " : comments list is empty.");
        return null;
    }
}
