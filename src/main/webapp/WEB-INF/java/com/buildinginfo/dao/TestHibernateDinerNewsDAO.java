package com.buildinginfo.dao;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dinernews.dao.DinerNewsDAO;
import com.dinernews.dao.DinerNewsDAOHibernateImpl;
import com.dinernews.entity.DinerNews;

import java.text.Format;

public class TestHibernateDinerNewsDAO {
	public static void main(String[] args){
		DinerNewsDAO dnd = new DinerNewsDAOHibernateImpl();
		DinerNews dns = new DinerNews();
		
		//新增
		dns.setDinerNewsContent("AAAAAAAAAAAAAAAAA");
		dns.setEmpID(1);
		Date d = new Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(d.getTime());
		dns.setDinerNewsReleaseTime(timestamp);
		dns.setDinerNewsReviseTime(timestamp);
		dnd.add(dns);
		
		//修改
		
//		dns.setDinerNewsID(18);
//		dns.setEmpID(2);
//		dnd.update(dns);
	}
}
