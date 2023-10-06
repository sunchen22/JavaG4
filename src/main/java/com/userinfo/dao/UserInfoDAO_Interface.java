package com.userinfo.dao;

import java.util.List;

import com.userinfo.entity.UserInfo;

public interface UserInfoDAO_Interface {
	public int insert(UserInfo userInfo);
    public int update(UserInfo userInfo);
    public int delete(Integer userID);
    public UserInfo findByPrimaryKey(Integer userID);
    public List<UserInfo> getAll();
}
