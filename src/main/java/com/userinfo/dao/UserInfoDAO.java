package com.userinfo.dao;

import java.io.FileInputStream;
import java.io.*;
import java.util.List;

import org.hibernate.Session;

import com.buildinginfo.entity.BuildingInfo;
import com.userinfo.entity.UserInfo;

import util.HibernateUtil;

public class UserInfoDAO implements UserInfoDAO_Interface {

	@Override
	public int insert(UserInfo userInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer insertID = (Integer) session.save(userInfo);
			session.getTransaction().commit();
			return insertID;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(UserInfo userInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(userInfo);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer userInfono) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			UserInfo userInfo = session.get(UserInfo.class, userInfono);
			if (userInfo != null) {
				session.delete(userInfo);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public UserInfo findByPrimaryKey(Integer userID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			UserInfo userInfo = session.get(UserInfo.class, userID);
			session.getTransaction().commit();
			return userInfo;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<UserInfo> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<UserInfo> list = session.createQuery("from UserInfo", UserInfo.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			//關聯BuildinInfo
            BuildingInfo buildingInfo = session.get(BuildingInfo.class, 1); 
            if (buildingInfo == null) {
                buildingInfo = new BuildingInfo();
                session.save(buildingInfo);
            }
            
            
			
            UserInfo userInfo = new UserInfo();
			userInfo.setUserAccount("TestAccount1");
			userInfo.setUserPassword("testPassword");
			userInfo.setUserPhone("0934567890");
			userInfo.setUserName("TestName");
			userInfo.setUserRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
			userInfo.setUserNickName("TestNick");
			userInfo.setBuildingInfo(buildingInfo);
			userInfo.setUserBirthday(java.sql.Date.valueOf("2005-01-01"));
			try {
			    InputStream is = new FileInputStream("C:/Pictures/natalie_portman_1.jpg");
			    byte[] userBlobData = is.readAllBytes();
			    userInfo.setUserBlob(userBlobData);
			} catch(IOException e) {
			    e.printStackTrace();
			}
			
			session.save(userInfo);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
		
		
	}

}
