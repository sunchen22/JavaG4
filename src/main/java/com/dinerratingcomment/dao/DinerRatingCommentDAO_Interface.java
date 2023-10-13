package com.dinerratingcomment.dao;

import java.util.List;

import com.dinerratingcomment.entity.DinerRatingComment;

public interface DinerRatingCommentDAO_Interface {
	public int insert(DinerRatingComment dinerRatingComment);
    public int update(DinerRatingComment dinerRatingComment);
    public int delete(Integer dinerRatingCommentID);
    public DinerRatingComment findByPrimaryKey(Integer dinerRatingCommentID);
    public List<DinerRatingComment> getAll();
}
