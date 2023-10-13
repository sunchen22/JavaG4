package com.userinfo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.userinfo.entity.UserInfo;




public class UserInfoDAO implements UserInfoDAO_Interface {
	private SessionFactory factory;
	
	public UserInfoDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(UserInfo userInfo) {
		System.out.println("開始insert");
		return (Integer) getSession().save(userInfo);
	}

	@Override
	public UserInfo update(UserInfo userInfo) {
		try {
			getSession().update(userInfo);
			return userInfo;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int delete(Integer userID) {
		UserInfo userInfo = getSession().get(UserInfo.class, userID);
		if (userInfo != null) {
			getSession().delete(userInfo);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public UserInfo findByPrimaryKey(Integer userID) {
		return getSession().get(UserInfo.class, userID);
	}

	@Override
	public List<UserInfo> getAll() {
		return getSession().createQuery("from UserInfo", UserInfo.class).list();
	}
	
	
	public UserInfo findByUserAccount(String userAccount) {
	    return getSession()
	            .createQuery("from UserInfo where useraccount = :userAccount", UserInfo.class)
	            .setParameter("userAccount", userAccount)
	            .uniqueResult();
	}

	
}
