package com.grouporder.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import com.grouporder.entity.GroupOrder;

import util.HibernateUtil;

public class GroupOrderDAOHibernateImplC implements GroupOrderDAOC {

	@Override
	public List<GroupOrder> getAll(Integer dinerID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
//			session.beginTransaction();
			List<GroupOrder> list = session.createQuery("from GroupOrder where dinerid = ?0 ", GroupOrder.class)
					.setParameter(0, dinerID).list();

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
			GroupOrder gor = session.get(GroupOrder.class, groupOrderID);
			byte[] img = gor.getDeliveredBlob();

//			session.getTransaction().commit();
			return img;
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

			GroupOrder gor = session.get(GroupOrder.class, groupOrderID);
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
			String sql = "SELECT DATE(groupOrderSubmitTime) AS orderDate, SUM(groupTotalPrice) AS totalSales "
					+ "FROM grouporder " + "WHERE dinerId = :dinerID " + " and orderStatus = :orderStatus "
					+ "AND DATE(groupOrderSubmitTime) BETWEEN DATE_SUB(CURDATE(), INTERVAL 7 DAY) "
					+ "AND DATE_SUB(CURDATE(), INTERVAL 1 DAY) " + "GROUP BY DATE(groupOrderSubmitTime) "
					+ "ORDER BY DATE(groupOrderSubmitTime)";

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
			String sql = "SELECT go.groupOrderID, " + "GROUP_CONCAT(COALESCE(p.productName, 'NA')) AS productNames, "
					+ "GROUP_CONCAT(COALESCE(uo.productQuantity, 'NA')) AS productQuantities, "
					+ "GROUP_CONCAT(DISTINCT COALESCE(go.orderStatus, 'NA')) AS orderStatuses, "
					+ "GROUP_CONCAT(DISTINCT COALESCE(bf.buildingName, 'NA')) AS buildingNames, "
					+ "GROUP_CONCAT(COALESCE(uo.userItemPrice, 'NA')) AS userItemPrices, "
					+ "GROUP_CONCAT(DISTINCT COALESCE(go.groupOrderSubmitTime, 'NA')) AS groupOrderSubmitTimes, "
					+ "GROUP_CONCAT(COALESCE(pv1.productVaryDes, 'NA')) AS productVaryDes1, "
					+ "GROUP_CONCAT(COALESCE(pv2.productVaryDes, 'NA')) AS productVaryDes2, "
					+ "GROUP_CONCAT(COALESCE(pv3.productVaryDes, 'NA')) AS productVaryDes3, "
					+ "GROUP_CONCAT(COALESCE(pv4.productVaryDes, 'NA')) AS productVaryDes4, "
					+ "GROUP_CONCAT(COALESCE(ue.username, 'NA')) AS username " + "FROM grouporder go "
					+ "LEFT JOIN userorderdetail uo ON go.groupOrderID = uo.groupOrderID "
					+ "LEFT JOIN product p ON uo.productID = p.productID "
					+ "LEFT JOIN userorderdetailvary uod ON uo.userOrderItemID = uod.userOrderItemID "
					+ "LEFT JOIN buildinginfo as bf ON go.buildingID = bf.buildingID "
					+ "LEFT JOIN productvary pv1 ON pv1.productVaryID = uod.productVaryID1 "
					+ "LEFT JOIN productvary pv2 ON pv2.productVaryID = uod.productVaryID2 "
					+ "LEFT JOIN productvary pv3 ON pv3.productVaryID = uod.productVaryID3 "
					+ "LEFT JOIN productvary pv4 ON pv4.productVaryID = uod.productVaryID4 "
					+ "LEFT JOIN userinfo ue ON ue.userID = uo.userID " + "WHERE go.dinerid = ?0 "
					+ "GROUP BY go.groupOrderID;";

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
	@Override
	public List<Object[]> getOrderDetail2(Integer dinerID ,String orderStatus) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

//			session.beginTransaction();
			String sql = "SELECT go.groupOrderID, go.dinerID,go.groupTotalPrice," + "GROUP_CONCAT(COALESCE(p.productName, 'NA')) AS productNames, "
					+ "GROUP_CONCAT(COALESCE(uo.productQuantity, 'NA')) AS productQuantities, "
					+ "GROUP_CONCAT(DISTINCT COALESCE(go.orderStatus, 'NA')) AS orderStatuses, "
					+ "GROUP_CONCAT(DISTINCT COALESCE(bf.buildingName, 'NA')) AS buildingNames, "
					+ "GROUP_CONCAT(COALESCE(uo.userItemPrice, 'NA')) AS userItemPrices, "
					+ "GROUP_CONCAT(DISTINCT COALESCE(go.groupOrderSubmitTime, 'NA')) AS groupOrderSubmitTimes, "
					+ "GROUP_CONCAT(COALESCE(pv1.productVaryDes, 'NA')) AS productVaryDes1, "
					+ "GROUP_CONCAT(COALESCE(pv2.productVaryDes, 'NA')) AS productVaryDes2, "
					+ "GROUP_CONCAT(COALESCE(pv3.productVaryDes, 'NA')) AS productVaryDes3, "
					+ "GROUP_CONCAT(COALESCE(pv4.productVaryDes, 'NA')) AS productVaryDes4, "
					+ "GROUP_CONCAT(COALESCE(ue.username, 'NA')) AS username " + "FROM grouporder go "
					+ "LEFT JOIN userorderdetail uo ON go.groupOrderID = uo.groupOrderID "
					+ "LEFT JOIN product p ON uo.productID = p.productID "
					+ "LEFT JOIN userorderdetailvary uod ON uo.userOrderItemID = uod.userOrderItemID "
					+ "LEFT JOIN buildinginfo as bf ON go.buildingID = bf.buildingID "
					+ "LEFT JOIN productvary pv1 ON pv1.productVaryID = uod.productVaryID1 "
					+ "LEFT JOIN productvary pv2 ON pv2.productVaryID = uod.productVaryID2 "
					+ "LEFT JOIN productvary pv3 ON pv3.productVaryID = uod.productVaryID3 "
					+ "LEFT JOIN productvary pv4 ON pv4.productVaryID = uod.productVaryID4 "
					+ "LEFT JOIN userinfo ue ON ue.userID = uo.userID " 
//					+ "WHERE go.dinerID = ?0 and go.orderStatus=?1"
					+ "WHERE go.dinerID = :dinerID and go.orderStatus= :orderStatus "
					+ "GROUP BY go.groupOrderID";


//			@SuppressWarnings("unchecked")
//			List<Object[]> list = session.createNativeQuery(sql).setParameter(0, dinerID).setParameter(1, orderStatus).list();
			List<Object[]> list = new ArrayList<>();
			
			Query query = session.createNativeQuery(sql);
			
        	query.setParameter("dinerID", dinerID);
        	query.setParameter("orderStatus", orderStatus);
        	list = query.getResultList();
//					session.getTransaction().commit();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}

		return null;
	}


}