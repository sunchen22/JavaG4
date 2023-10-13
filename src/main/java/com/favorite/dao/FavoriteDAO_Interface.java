package com.favorite.dao;

import java.util.List;

import com.favorite.entity.Favorite;
import com.grouporder.entity.GroupOrder;

public interface FavoriteDAO_Interface {
	int insert(Favorite favorite);
	int update(Favorite favorite);
	int delete(Integer favoriteID);
	Favorite findByPrimaryKey(Integer favoriteID);
	List<Favorite> getAll();
}