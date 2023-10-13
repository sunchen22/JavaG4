package com.favorite.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;

import com.dinerinfo.entity.DinerInfo;
import com.favorite.entity.Favorite;
import com.userinfo.entity.UserInfo;

import util.HibernateUtil;

public class FavoriteDAO_Test {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			//關聯UserID
            UserInfo userInfo = session.get(UserInfo.class, 3); 
            if (userInfo == null) {
            	userInfo = new UserInfo();
                session.save(userInfo);
            }
            
            //關聯DinerID
            DinerInfo dinerInfo = session.get(DinerInfo.class, 1); 
            if (dinerInfo == null) {
            	dinerInfo = new DinerInfo();
                session.save(dinerInfo);
            } 
			
            Favorite favorite = new Favorite();
			favorite.setUserInfo(userInfo);
			favorite.setDinerInfo(dinerInfo);
			favorite.setFavoriteTime(new Timestamp(new Date().getTime()));
			session.save(favorite);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
		
		
	}

}
