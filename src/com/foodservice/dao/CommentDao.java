package com.foodservice.dao;

import com.foodservice.model.Comment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/24/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommentDao {

    public void addComment(Comment comment);
    public List<Comment> showComments();
}
