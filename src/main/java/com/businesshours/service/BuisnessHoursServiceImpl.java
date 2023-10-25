package com.businesshours.service;

import java.sql.Time;
import java.util.List;

import com.businesshours.dao.BusinessHoursDAOImpl;
import com.businesshours.entity.BusinessHours;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

public class BuisnessHoursServiceImpl implements BusinessHoursService{

	private BusinessHoursDAOImpl dao;
	
	public BuisnessHoursServiceImpl() {
		dao = new BusinessHoursDAOImpl(HibernateUtil.getSessionFactory());
	}
	
	
	@Override
	public BusinessHours setBusinessHours(Time openTime, Time closeTime, String dayOfWeek, Integer dinerID) {
		if(dao.isValidBusinessHours(openTime, closeTime) != 1) {
			return null;
		} else {
			BusinessHours bh = new BusinessHours();
			bh.setOpenTime(openTime);
			bh.setCloseTime(closeTime);
			bh.setDayOfWeek(dayOfWeek);
			
			DinerInfo dinerInfo = dao.findByPKJoinDinerInfo(dinerID);
			bh.setDinerInfo(dinerInfo);
			
			return bh ;
		}
	}


	@Override
	public String getDayOfWeekByDinerID(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setBusinessHours(Integer dinerID, String dayOfWeek, String openTime, String closeTime) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<BusinessHours> getBusinessHoursByDinerID(Integer dinerID) {
		return dao.getBusinessHoursByDinerID(dinerID);
	}

    
	
	
	

}
