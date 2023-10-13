package com.buildinginfo.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.buildinginfo.entity.BuildingInfo;

import util.HibernateUtil;

public class BuildingInfoDAOHibernateImpl implements BuildingInfoDAO {
	@Override
	public int add(BuildingInfo bif) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

			session.beginTransaction();
			Integer id = (Integer) session.save(bif);
			session.getTransaction().commit();
			System.out.println(id);

			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(BuildingInfo bif) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(bif);
			session.getTransaction().commit();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	public int down(Integer buildingID) {//更新狀態
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			BuildingInfo bif = session.get(BuildingInfo.class, buildingID);
			if (bif != null) {
				bif.setBuildingState(2);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;

	}

	public BuildingInfo findByPK(Integer buildingID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		BuildingInfo bif = session.get(BuildingInfo.class, buildingID);
		//狀態為1的才查的到
		if(bif.getBuildingState() == 1) {
		session.getTransaction().commit();
		return bif;
		}																	
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	
//	public BuildingInfo getOne(String buildingName) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		
//		try {
//		session.beginTransaction();
//				
//		BuildingInfo bif = session.get(BuildingInfo.class, buildingName);
//		session.getTransaction().commit();
//		return bif;
//		}catch(Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		return null;
//	}
	
	
	

	public List<BuildingInfo> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();//狀態為1的才查的到
		List<BuildingInfo> list = session.createQuery("from BuildingInfo where buildingstate = 1", BuildingInfo.class).list();
		
		session.getTransaction().commit();
		return list;
	}catch(Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
		}
		return null;
	}

}
