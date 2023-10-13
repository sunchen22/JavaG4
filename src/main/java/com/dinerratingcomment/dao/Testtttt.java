package com.dinerratingcomment.dao;

import java.util.List;

import com.dinerratingcomment.entity.DinerRatingComment;

public class Testtttt {

	public static void main(String[] args) {
		DinerRatingCommentDAOImplC r = new DinerRatingCommentDAOImplC();
		DinerRatingComment d = new DinerRatingComment();
		
		List<DinerRatingComment> list = r.getAll(4);
		for(DinerRatingComment c : list) {
			System.out.println(c.getCommentID());
		
		}
	}

}
