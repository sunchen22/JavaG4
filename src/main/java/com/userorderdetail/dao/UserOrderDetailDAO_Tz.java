package com.userorderdetail.dao;

import org.hibernate.*;

import util.HibernateUtil;

import javax.persistence.Query;
import java.util.*;

public class UserOrderDetailDAO_Tz {

    public List<Object[]> getUserOrderDetails(Integer userId, Integer groupOrderId) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        List<Object[]> details = new ArrayList<>();
        
        try {
        	
        	String sql = "SELECT g.groupOrderID, " +
        		    "DATE(g.groupordersubmittime) as submit_date, " +
        		    "d.dinerID as d_dinerID, " +
        		    "d.dinerName, " +
        		    "u.productId as u_productId, " +
        		    "p.productName, " +
        		    "p.productPrice, " +
        		    "u.userOrderItemID, " +
        		    "u.productquantity, " +
        		    "v1.productVaryID as v1_productVaryID, " +
        		    "v1.productvarydes as v1_productvarydes, " +
        		    "v1.productvaryprice as v1_productvaryprice, " +
        		    "v2.productVaryID as v2_productVaryID, " +
        		    "v2.productvarydes as v2_productvarydes, " +
        		    "v2.productvaryprice as v2_productvaryprice, " +
        		    "v3.productVaryID as v3_productVaryID, " +
        		    "v3.productvarydes as v3_productvarydes, " +
        		    "v3.productvaryprice as v3_productvaryprice, " +
        		    "v4.productVaryID as v4_productVaryID, " +
        		    "v4.productvarydes as v4_productvarydes, " +
        		    "v4.productvaryprice as v4_productvaryprice, " +
        		    "g.orderstatus " +
        		    "FROM userOrderDetail u " +
        		    "JOIN product p ON u.productId = p.productId " +
        		    "JOIN groupOrder g ON u.groupOrderId = g.groupOrderId " +
        		    "JOIN DinerInfo d ON g.dinerId = d.dinerId " +
        		    "JOIN userOrderDetailVary uv ON u.userOrderItemId = uv.userOrderItemId " +
        		    "LEFT JOIN productVary v1 ON uv.productVaryID1 = v1.productVaryId " +
        		    "LEFT JOIN productVary v2 ON uv.productVaryID2 = v2.productVaryId " +
        		    "LEFT JOIN productVary v3 ON uv.productVaryID3 = v3.productVaryId " +
        		    "LEFT JOIN productVary v4 ON uv.productVaryID4 = v4.productVaryId " +
        		    "WHERE u.userId = :userId AND g.groupOrderId = :groupOrderId";

        	Query query = session.createNativeQuery(sql);
        	query.setParameter("userId", userId);
        	query.setParameter("groupOrderId", groupOrderId);
            details = query.getResultList();
            return details;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }

    public List<Integer> getUserGroupOrdersID(Integer userId) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        List<Integer> groupOrders = new ArrayList<>();
        
        try {
            String sql = "SELECT distinct u.groupOrderId FROM userOrderDetail u " +
                         "WHERE u.userId = :userId";
                         
            Query query = session.createNativeQuery(sql);
            query.setParameter("userId", userId);
            groupOrders = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return groupOrders;
    }
}
