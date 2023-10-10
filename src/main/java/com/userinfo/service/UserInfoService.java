package com.userinfo.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.buildinginfo.entity.BuildingInfo;
import com.userinfo.dao.UserInfoDAO;
import com.userinfo.dao.UserInfoDAO_Interface;
import com.userinfo.entity.UserInfo;

import util.HibernateUtil;

// 搭配 JSP / Thymeleaf 後端渲染畫面，將交易動作至於 view filter
public class UserInfoService {
	// 一個 service 實體對應一個 dao 實體
	private UserInfoDAO_Interface dao;
	
	public UserInfoService() {
		dao = new UserInfoDAO(HibernateUtil.getSessionFactory());
	}
	
//	public UserInfo addUser(String userAccount, String userPassword, String userPhone,
//			String userName, String userNickName, Integer buildingID, 
//			java.sql.Date userBirthday) {
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUserAccount(userAccount);
//		userInfo.setUserPassword(userPassword);
//		userInfo.setUserPhone(userPhone);
//		userInfo.setUserName(userName);
//		userInfo.setUserRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
//		userInfo.setUserNickName(userNickName);
//		BuildingInfo buildingInfo = new BuildingInfo();
//		buildingInfo.setBuildingID (buildingID);
//		userInfo.setBuildingInfo(buildingInfo);
//		userInfo.setUserBirthday(userBirthday);
//		try {
//			InputStream is = new FileInputStream(userBlobPath);
//			byte[] userBlobData = is.readAllBytes();
//			userInfo.setUserBlob(userBlobData);
//			} catch(IOException e) {
//			    e.printStackTrace();
//			}
//		dao.insert(userInfo);
//		return userInfo;
//		}
	
	public Integer addUser(UserInfo userInfo) {
		return dao.insert(userInfo);
	}
	
	public UserInfo updateUserInfo(UserInfo userInfo) {
		return dao.update(userInfo);
	}

	
//	public UserInfo updateUserInfo(Integer userID, String userAccount, String userPassword, 
//			String userPhone, String userName, String userNickName, Integer buildingID, 
//			java.sql.Date userBirthday) {
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUserID(userID);
//		userInfo.setUserAccount(userAccount);
//		userInfo.setUserPassword(userPassword);
//		userInfo.setUserPhone(userPhone);
//		userInfo.setUserName(userName);
//		userInfo.setUserRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
//		userInfo.setUserNickName(userNickName);
//		BuildingInfo buildingInfo = new BuildingInfo();
//		buildingInfo.setBuildingID (buildingID);
//		userInfo.setBuildingInfo(buildingInfo);
//		userInfo.setUserBirthday(userBirthday);
//		try {
//			InputStream is = new FileInputStream(userBlobPath);
//			byte[] userBlobData = is.readAllBytes();
//			userInfo.setUserBlob(userBlobData);
//			} catch(IOException e) {
//			    e.printStackTrace();
//			}
//		dao.update(userInfo);
//		return userInfo;
//	}

	public Integer deleteUserInfo(Integer userID) {
		return dao.delete(userID);
	}

	
	public UserInfo getOneUserID(Integer userID) {
		return dao.findByPrimaryKey(userID);
	}

	
	public List<UserInfo> getAllUserInfos() {
		return dao.getAll();
	}
	
	public UserInfo getOneByUserAccount(String userAccount) {
	    return dao.findByUserAccount(userAccount);
	}

	

	
	

}
