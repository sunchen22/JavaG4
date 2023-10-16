package com.dinerinfo.dao;

import java.util.List;
import java.util.Map;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

public class DinerInfoDAOHibernateImpl implements DinerInfoDAO{

	@Override
	public int add(DinerInfo dinerInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(dinerInfo);
			session.getTransaction().commit();
			return id;  //成功的話，回傳id
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;  //不成功的話，回傳-1
	}

	@Override
	public int update(DinerInfo dinerInfo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(dinerInfo);
			session.getTransaction().commit();
			return 1;  //成功的話，回傳1
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;  //不成功的話，回傳-1
	}

	@Override
	public int delete(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			DinerInfo dinerInfo = session.get(DinerInfo.class, dinerID);
			if (dinerInfo != null) {
				session.delete(dinerInfo);
			}
			session.getTransaction().commit();
			return 1;   //成功的話，回傳1
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;  //不成功的話，回傳-1
	}

	@Override
	public DinerInfo findByPK(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			DinerInfo dinerInfo = session.get(DinerInfo.class, dinerID);
			session.getTransaction().commit();
			return dinerInfo;   //成功的話，回傳 dinerInfo 物件
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;   //不成功的話，回傳null
	}

	
	@Override
	public DinerInfo findByTaxID(String dinerTaxID) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    try {
	        session.beginTransaction();
	        String hql = "FROM DinerInfo WHERE dinerTaxID  = :dinerTaxID";
	        Query<DinerInfo> query = session.createQuery(hql, DinerInfo.class);
	        query.setParameter("dinerTaxID", dinerTaxID);
	        DinerInfo dinerInfo = query.uniqueResult(); // 因為dinerTaxID應該是唯一的，所以使用uniqueResult()取得單一結果
	        session.getTransaction().commit();
	        return dinerInfo;   //成功的話，回傳 dinerInfo 物件
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    return null;   //不成功的話，回傳null
	}


	@Override
	public List<DinerInfo> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<DinerInfo> list = session.createQuery("from DinerInfo", DinerInfo.class).list();
			session.getTransaction().commit();
			return list;   //成功的話，回傳Emp的list
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;   //不成功的話，回傳null
	

	}

	@Override
	public List<DinerInfo> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DinerInfo> getAll(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
