package com.advertisement.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.advertisement.entity.Advertisement;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

public class AdvertisementDAOHibernateImpl implements AdvertisementDAO {

	private SessionFactory factory;

	Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	public AdvertisementDAOHibernateImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(Advertisement advertisement) {

		try {
//			session.beginTransaction();

			Integer advertisementID = (Integer) getSession().save(advertisement);
//			session.getTransaction().commit();
			return advertisementID;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}

		return -1;
	}

	@Override
	public void addAdvertisement(Advertisement advertisement) {
//		// 設定廣告狀態
//		advertisement.setAdvertisementStatus("Submitted");
//		advertisement.setDinerid(dinerInfo);
		
		
		Session session = getSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction(); // 開始事務
			
			session.save(advertisement);
			tx.commit(); // 提交事務			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // 如果出現錯誤，則回滾事務
			}
			throw e; // 或者選擇其他的錯誤處理方式
		}
		
//		return advertisement;
	}

	@Override
	public int update(Advertisement advertisement) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
//			session.beginTransaction();
			session.update(advertisement);
//			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}

		return -1;
	}

	@Override
	public int delete(Integer advertisementId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
//			session.beginTransaction();
			Advertisement advertisement = session.get(Advertisement.class, advertisementId);
			if (advertisement != null) {
				session.delete(advertisement);
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
	public Advertisement findByPK(Integer advertisementId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
//			session.beginTransaction();
			Advertisement advertisement = session.get(Advertisement.class, advertisementId);
//			session.getTransaction().commit();
			return advertisement;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}

		return null;
	}

	
	
	
	@Override
	public DinerInfo getDinerInfoByDinerID(Integer dinerID) {
		 DinerInfo dinerInfo = null;
	        Session session = null;
	        Transaction tx = null;
	        try {
	            session = getSession();
	            tx = session.beginTransaction();

	            Query<DinerInfo> query = session.createQuery("from DinerInfo where dinerID=:dinerID", DinerInfo.class);
	            query.setParameter("dinerID", dinerID);
	            dinerInfo = query.getSingleResult();
	            
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null && tx.isActive()) {
	                tx.rollback();
	            }
	            e.printStackTrace();
	            // TODO: Add more detailed error logging or throw a custom exception
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return dinerInfo;
	    }



	@Override
	public List<Advertisement> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
//			session.beginTransaction();
			List<Advertisement> list = session.createQuery("from Advertisement", Advertisement.class).list();
//			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}

		return null;
	}

	@Override
	public List<Advertisement> getSubmitted(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			String hql = "FROM DinerInfo d JOIN d.advertisements a WHERE a.advertisementStatus = 'Submitted' AND d.dinerid = :dinerID";
			Query<Advertisement> query = session.createQuery(hql, Advertisement.class);
			List<Advertisement> list = query.list();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	@Override
	public List<Advertisement> getActive(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			String hql = "FROM DinerInfo d JOIN d.advertisements a WHERE a.advertisementStatus = 'Active' AND d.dinerid = :dinerID";
			Query<Advertisement> query = session.createQuery(hql, Advertisement.class);
			List<Advertisement> list = query.list();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Advertisement> getDeactivated(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			String hql = "FROM DinerInfo d JOIN d.advertisements a WHERE a.advertisementStatus = 'Deactivated' AND d.dinerid = :dinerID";
			Query<Advertisement> query = session.createQuery(hql, Advertisement.class);
			List<Advertisement> list = query.list();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Advertisement> getAdvertisementsByDinerID(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			String hql = "FROM DinerInfo d JOIN d.advertisements a WHERE d.dinerid = :dinerID";
			Query<Advertisement> query = session.createQuery(hql, Advertisement.class);
			List<Advertisement> list = query.list();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<Advertisement> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> getAll(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}
