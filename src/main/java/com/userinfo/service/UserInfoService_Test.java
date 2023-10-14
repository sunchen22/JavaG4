package com.userinfo.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.buildinginfo.entity.BuildingInfo;
import com.userinfo.entity.UserInfo;

public class UserInfoService_Test {

	public static void main(String[] args) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserAccount("TestAccount1");
		userInfo.setUserPassword("testPassword");
		userInfo.setUserPhone("0934567890");
		userInfo.setUserName("TestName");
		userInfo.setUserRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
		userInfo.setUserNickName("TestNick");
		BuildingInfo buildingInfo = new BuildingInfo();
		buildingInfo.setBuildingID (1);
		userInfo.setBuildinginfo(buildingInfo);
		userInfo.setUserBirthday(java.sql.Date.valueOf("2005-01-01"));
		try {
		    InputStream is = new FileInputStream("C:/Pictures/natalie_portman_1.jpg");
		    byte[] userBlobData = is.readAllBytes();
		    userInfo.setUserBlob(userBlobData);
		} catch(IOException e) {
		    e.printStackTrace();
		}
		UserInfoService userInfoService = new UserInfoService();
		userInfoService.addUser(userInfo);

	}

}
