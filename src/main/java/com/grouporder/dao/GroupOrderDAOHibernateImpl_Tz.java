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
			List<GroupOrder> list = session.createQuery("from GroupOrder g where g.dinerInfo.dinerID = :dinerID", GroupOrder.class)
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
}
