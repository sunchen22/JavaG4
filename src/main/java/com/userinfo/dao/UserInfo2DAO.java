package com.userinfo.dao;

import java.util.List;
import java.util.Map;

import com.userinfo.entity.UserInfo;

public interface UserInfo2DAO {
	
	int insert(UserInfo entity);

	int update(UserInfo userInfo);
	
	int delete(Integer id);
	 
	UserInfo getById(Integer id);
	
	List<UserInfo> getAll();
	
	List<UserInfo> getByCompositeQuery(Map<String, String> map);
	
	List<UserInfo> getAll(int currentPage);
	
	long getTotal();

}
