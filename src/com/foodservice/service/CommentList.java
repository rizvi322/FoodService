package com.foodservice.service;

import com.foodservice.model.Comment;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/25/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CommentList {

    public void addComment(Comment comment);
    public List<Comment> showComments();

}
