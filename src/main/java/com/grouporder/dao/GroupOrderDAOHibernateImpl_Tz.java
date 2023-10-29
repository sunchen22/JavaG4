package com.grouporder.dao;

import java.util.List;
import org.hibernate.Session;
import com.grouporder.entity.GroupOrder;
import util.HibernateUtil;

public class GroupOrderDAOHibernateImpl_Tz {
	private static final int PAGE_MAX_RESULT = 3;
	
	
	public List<GroupOrder> getAllbyDinerID(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			getSession().beginTransaction();
			List<GroupOrder> list = session.createQuery("from GroupOrder g where g.dinerInfo.dinerID = :dinerID and (g.orderStatus='1' or g.orderStatus='2') ", GroupOrder.class)
					.setParameter("dinerID", dinerID)
					.getResultList();
//			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	public List<GroupOrder> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			getSession().beginTransaction();
			List<GroupOrder> list = session.createQuery("from GroupOrder g where (g.orderStatus='1' or g.orderStatus='2') ", GroupOrder.class)
					.getResultList();
//			getSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}
		return null;
	}
	
	
	//sun add
	public int groupOrderPriceCount(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {	
	        String sql = "SELECT SUM(groupTotalPrice) FROM grouporder WHERE orderStatus = 7 ";
	        
	        Long count = ((Number) session.createNativeQuery(sql).getSingleResult()).longValue();
	        return count.intValue();

	}catch(Exception e) {
		e.printStackTrace();
		return -1;
		}
	}
}
