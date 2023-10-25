package com.businesshours.service;

import java.sql.Time;
import java.util.List;

import com.businesshours.entity.BusinessHours;
import com.dinerinfo.entity.DinerInfo;

public interface BusinessHoursService {
	BusinessHours setBusinessHours(Time openTime ,Time closeTime ,String dayOfWeek ,Integer dinerID);
	String getDayOfWeekByDinerID(Integer dinerID);

	List<BusinessHours> getBusinessHoursByDinerID(Integer dinerID);
	
	void setBusinessHours(Integer dinerID, String dayOfWeek, String openTime, String closeTime);

}
