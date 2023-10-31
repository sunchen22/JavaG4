package com.usernews.service;

import java.util.List;

import com.usernews.entity.UserNews;

public interface UserNewsService {
	UserNews addUserNews(UserNews usernews);
	
	void  updateUserNews(UserNews usernews);
	
	void deleteUserNews(Integer usernewsID);
	
	UserNews getUserNewsByID(Integer usernewsID);
	
	List<UserNews> getAllUserNews();
	
	// add by tz
	List<UserNews> getAllUserNewsByStatus();
	
	
//	List<UserInfo> getUserInfoByCompositeQuery(Map<String, String[]> map);

}
