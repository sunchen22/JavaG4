package com.businesshours.dao;

import java.sql.Time;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import org.hibernate.query.Query;


import com.businesshours.entity.BusinessHours_Tz;

import util.HibernateUtil;




public class BusinessHoursDAOImpl_Tz{
	

	
	public BusinessHours_Tz getTimeByDinerIDDayWeek(Integer dinerID, String dayOfWeek) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession(); 
	    try{
	        CriteriaBuilder cb = session.getCriteriaBuilder();
	        CriteriaQuery<BusinessHours_Tz> cq = cb.createQuery(BusinessHours_Tz.class);
	        Root<BusinessHours_Tz> root = cq.from(BusinessHours_Tz.class);
	        
	        Predicate dinerIdPredicate = cb.equal(root.get("dinerInfo").get("dinerID"), dinerID);
	        Predicate dayOfWeekPredicate = cb.equal(root.get("dayOfWeek"), dayOfWeek);
	        cq.where(cb.and(dinerIdPredicate, dayOfWeekPredicate));
	        Query<BusinessHours_Tz> query = session.createQuery(cq);
	        return query.uniqueResult();
	    } finally {
	    	
	    }
	}

	
}
