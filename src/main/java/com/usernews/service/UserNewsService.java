package com.usernews.service;

import java.util.List;

import com.usernews.entity.UserNews;

public interface UserNewsService {
	
	void  updateUserNews(UserNews usernews);
	
	void deleteUserNews(Integer usernewsID);
	
	UserNews getUserNewsByID(Integer usernewsID);
	
	List<UserNews> getAllUserNews();
	
	
//	List<UserInfo> getUserInfoByCompositeQuery(Map<String, String[]> map);

}
