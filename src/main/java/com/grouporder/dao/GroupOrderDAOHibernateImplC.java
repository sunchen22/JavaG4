package com.grouporder.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.grouporder.entity.GroupOrder;

import util.HibernateUtil;

public class GroupOrderDAOHibernateImplC implements GroupOrderDAOC{

	@Override
	public List<GroupOrder> getAll(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			List<GroupOrder> list = session.createQuery
					("from GroupOrder where dinerid = ?0 ", GroupOrder.class)
					.setParameter(0,dinerID)
					.list();
			
//			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
	}
	@Override
	public byte[] getImg(Integer groupOrderID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
//			session.beginTransaction();
			GroupOrder gor = session.get(GroupOrder.class,groupOrderID);
			byte[] img = gor.getDeliveredBlob();
			
//			session.getTransaction().commit();
			return img ;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
		return null;
		
	}
	
	
	
	@Override
	public GroupOrder findByPK(Integer groupOrderID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

//			session.beginTransaction();

			GroupOrder gor = session.get(GroupOrder.class,groupOrderID);
//			session.getTransaction().commit();


			


			return gor;
		} catch (Exception e) {
			e.printStackTrace();
//			getSession().getTransaction().rollback();
		}

		return null;
	}
	
	
	
	
	
	
	public List<Tuple> getAllOrderPrice(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            String sql = "SELECT DATE(groupOrderSubmitTime) AS orderDate, SUM(groupTotalPrice) AS totalSales " +
                    "FROM grouporder " +
                    "WHERE dinerId = :dinerID " +
                    "AND DATE(groupOrderSubmitTime) BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
                    "AND DATE_SUB(CURDATE(), INTERVAL 1 DAY) " +
                    "GROUP BY DATE(groupOrderSubmitTime) " +
                    "ORDER BY DATE(groupOrderSubmitTime)";

            NativeQuery<Tuple> query = session.createNativeQuery(sql, Tuple.class);
            query.setParameter("dinerID", dinerID); 
            List<Tuple> results = query.getResultList();

//            for (Tuple result : results) {
//                Date orderDate = result.get("orderDate", Date.class);
//                BigDecimal totalSales = result.get("totalSales", BigDecimal.class);
//
//                System.out.println("Order Date: " + orderDate);
//                System.out.println("Total Sales: " + totalSales);
//            }

            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
	
}