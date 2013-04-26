package com.foodservice.service;

import com.foodservice.model.Comment;
import com.foodservice.dao.CommentDao;
import com.foodservice.dao.CommentDaoImpl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/25/13
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class CommentListImpl implements CommentList {

    private CommentDao commentDao;

    public CommentListImpl() {

        commentDao = new CommentDaoImpl();
    }

    @Override
    public void addComment(Comment comment) {

        commentDao.addComment(comment);
    }

    @Override
    public List<Comment> showComments() {

        return commentDao.showComments();
    }
}
