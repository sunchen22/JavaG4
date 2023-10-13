package com.dinerratingcomment.dao;

import java.util.List;


import com.dinerratingcomment.entity.DinerRatingComment;



public interface DinerRatingCommentDAOC {
	
	int delete(Integer commentID);
	
	DinerRatingComment findByPK(Integer commentID);

	List<DinerRatingComment> getAll(Integer dinerID);

}
