package com.userinfo.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.hibernate.Session;

import com.buildinginfo.entity.BuildingInfo;
import com.userinfo.entity.UserInfo;

public class UserInfoDAO_Test {

	public static void main(String[] args) {
		
		UserInfoDAO_Interface dao = new UserInfoDAO(null);
		
			
			//新增會員資料
//          UserInfo userInfo = new UserInfo();
//			userInfo.setUserAccount("TestAccount1");
//			userInfo.setUserPassword("testPassword");
//			userInfo.setUserPhone("0934567890");
//			userInfo.setUserName("TestName");
//			userInfo.setUserRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
//			userInfo.setUserNickName("TestNick");
//			BuildingInfo buildingInfo = new BuildingInfo();
//			buildingInfo.setBuildingID (1);
//			userInfo.setBuildingInfo(buildingInfo);
//			userInfo.setUserBirthday(java.sql.Date.valueOf("2005-01-01"));
//			try {
//			    InputStream is = new FileInputStream("C:/Pictures/natalie_portman_1.jpg");
//			    byte[] userBlobData = is.readAllBytes();
//			    userInfo.setUserBlob(userBlobData);
//			} catch(IOException e) {
//			    e.printStackTrace();
//			}
//			dao.insert(userInfo);
			
			//更新會員資料
//			UserInfo userInfo = new UserInfo();
//			userInfo.setUserID(12);
//			userInfo.setUserAccount("newAccount1");
//			userInfo.setUserPassword("newPassword");
//			userInfo.setUserPhone("09345678900");
//			userInfo.setUserName("newName");
//			userInfo.setUserRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
//			userInfo.setUserNickName("newNick");
//			//關聯BuildinInfo
//	        BuildingInfo buildingInfo2 = new BuildingInfo(); 
//	        buildingInfo2.setBuildingID(2); 
//			userInfo.setBuildingInfo(buildingInfo2);
//			
//			userInfo.setUserBirthday(java.sql.Date.valueOf("2006-01-01"));
//			try {
//			    InputStream is = new FileInputStream("C:/Pictures/natalie_portman_2.jpg");
//			    byte[] userBlobData = is.readAllBytes();
//			    userInfo.setUserBlob(userBlobData);
//			} catch(IOException e) {
//			    e.printStackTrace();
//			}
//			dao.update(userInfo);
			
			//用PK查詢
//			UserInfo userInfo = dao.findByPrimaryKey(12);
//			System.out.print(userInfo);
			
			//刪除資料
//			dao.delete(12);
			
		
			//getAll
			List<UserInfo> list = dao.getAll();
			for (UserInfo userInfo : list) {
				System.out.print(userInfo.getUserID() + ",");
				System.out.print(userInfo.getUserAccount() + ",");
				System.out.print(userInfo.getUserPassword() + ",");
				System.out.print(userInfo.getUserPhone() + ",");
				System.out.print(userInfo.getUserName() + ",");
				System.out.print(userInfo.getUserRegisterTime() + ",");
				System.out.print(userInfo.getBuildinginfo().getBuildingID());
				System.out.println();
			}
			
			
			
	}

}
