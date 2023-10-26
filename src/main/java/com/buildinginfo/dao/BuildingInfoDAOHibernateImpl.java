package com.buildinginfo.dao;



import java.util.List;

import org.hibernate.Session;

import com.buildinginfo.entity.BuildingInfo;

import util.HibernateUtil;



public class BuildingInfoDAOHibernateImpl implements BuildingInfoDAO {
	@Override
	public int add(String name , String address) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

//			session.beginTransaction();
			BuildingInfo b = new BuildingInfo();
			b.setBuildingName(name);
			b.setBuildingAddress("台北市" + address);
			b.setbuildingStatus(1);
			session.save(b);
//			session.getTransaction().commit();
			

			return 0;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(BuildingInfo bif) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			session.update(bif);
//			session.getTransaction().commit();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return -1;
	}
	@Override
	public int down(Integer buildingID) {//更新狀態(刪除但只是更新狀態)
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			BuildingInfo bif = session.get(BuildingInfo.class, buildingID);
			if (bif != null) {
				bif.setbuildingStatus(2);
			}
//			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return -1;

	}
	@Override
	public BuildingInfo findByPK(Integer buildingID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();
		BuildingInfo bif = session.get(BuildingInfo.class, buildingID);
		//狀態為1的才查的到
		if(bif.getbuildingStatus() == 1) {
//		session.getTransaction().commit();
		return bif;
		
		}																	
		}catch(Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
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
	
	
	
	@Override
	public List<BuildingInfo> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();//狀態為1的才查的到
		List<BuildingInfo> list = session.createQuery("from BuildingInfo where buildingstatus = ?0", BuildingInfo.class)
				.setParameter(0, 1)
				.list();
		
//		session.getTransaction().commit();
		return list;
	}catch(Exception e) {
		e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}

}
