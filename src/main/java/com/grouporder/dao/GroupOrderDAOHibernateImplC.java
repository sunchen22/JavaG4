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
//			session.getTransaction().rollback();
		}

		return null;
	}
	

	
	public List<Tuple> getAllOrderPrice(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            String sql = "SELECT DATE(groupOrderSubmitTime) AS orderDate, SUM(groupTotalPrice) AS totalSales " +
                    "FROM grouporder " +
                    "WHERE dinerId = :dinerID " + " and orderStatus = :orderStatus " +
                    "AND DATE(groupOrderSubmitTime) BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) " +
                    "AND DATE_SUB(CURDATE(), INTERVAL 1 DAY) " +
                    "GROUP BY DATE(groupOrderSubmitTime) " +
                    "ORDER BY DATE(groupOrderSubmitTime)";

            NativeQuery<Tuple> query = session.createNativeQuery(sql, Tuple.class);
            query.setParameter("dinerID", dinerID); 
            query.setParameter("orderStatus", 7); 
            List<Tuple> results = query.getResultList();



            return results;
        } catch (Exception e) {
            e.printStackTrace();
//            session.getTransaction().rollback();
        }

        return null;
    }
	
	@Override
	public List<Object[]> getOrderDetail(Integer dinerID) {
	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

//			session.beginTransaction();
			String sql = "SELECT go.groupOrderID, p.productName, uo.productQuantity, go.orderStatus, bf.buildingName, uo.userItemPrice , go.groupOrderSubmitTime, "
					+"(SELECT productVaryDes FROM productvary  pv WHERE pv.productVaryID = uod.productVaryID1), "
					+"(SELECT productVaryDes FROM productvary  pv WHERE pv.productVaryID = uod.productVaryID2), "
					+"(SELECT productVaryDes FROM productvary  pv WHERE pv.productVaryID = uod.productVaryID3), "
					+"(SELECT productVaryDes FROM productvary  pv WHERE pv.productVaryID = uod.productVaryID4) "
					+"FROM grouporder go "
					+"LEFT JOIN userorderdetail  uo ON go.groupOrderID = uo.groupOrderID "					
					+"LEFT JOIN product p ON uo.productID = p.productID "	
					+"LEFT JOIN userorderdetailvary  uod ON uo.userOrderItemID = uod.userOrderItemID " 
					+" LEFT JOIN buildinginfo as bf on go.buildingID = bf.buildingID where go.dinerid = ?0 ";	
				
			        @SuppressWarnings("unchecked")
					List<Object[]> list = session.createNativeQuery(sql).setParameter(0, dinerID).list();

			        		
			
//					session.getTransaction().commit();


			        return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		
	
		
		return null;
	}
}