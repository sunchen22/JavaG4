package com.usernews.dao;

import java.util.List;

import com.usernews.entity.UserNews;

public interface UserNewsDAO {
	
	int insert(UserNews entity);

	int update(UserNews userNews);
	
	int delete(Integer id);
	 
	UserNews getById(Integer id);
	
	List<UserNews> getAll();
	
	// add by tz
	List<UserNews> getAllbyStatus();
	
//	List<UserInfo> getByCompositeQuery(Map<String, String> map);

	long getTotal();

}
