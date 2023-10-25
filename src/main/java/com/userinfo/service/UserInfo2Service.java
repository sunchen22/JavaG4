package com.userinfo.service;

import java.util.List;
import java.util.Map;

import com.userinfo.entity.UserInfo;

public interface UserInfo2Service {
	
	void  updateUserInfo(UserInfo userInfo);
	
	void deleteUserInfo(Integer userID);
	
	UserInfo getUserInfoByuserID(Integer userID);
	
	List<UserInfo> getAllUserInfo();
	
	List<UserInfo> getAllUserInfo(int currentPage);

	int getPageTotal();
	
	byte[] getImage(int img);
	
	List<UserInfo> getUserInfoByCompositeQuery(Map<String, String[]> map);

}
