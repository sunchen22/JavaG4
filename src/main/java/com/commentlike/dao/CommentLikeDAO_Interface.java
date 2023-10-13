package com.commentlike.dao;

import java.util.List;

import com.commentlike.entity.CommentLike;


public interface CommentLikeDAO_Interface {
	public int insert(CommentLike commentLike);
    public int update(CommentLike commentLike);
    public int delete(Integer commentLikeID);
    public CommentLike findByPrimaryKey(Integer commentLikeID);
    public List<CommentLike> getAll();

}
