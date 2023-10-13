package com.businesshours.dao;

import java.util.List;

import com.businesshours.entity.BusinessHours;

public interface BusinessHoursDAO {
	// 此介面定義對資料庫的相關存取抽象方法
	int add(BusinessHours dinerOpenHours);
	int update(BusinessHours dinerOpenHours);
	int delete(Integer dinerOpenHoursID);
	int deleteByDinerID(Integer dinerID);
	
	BusinessHours findByPK(Integer dinerOpenHoursID);
	List<BusinessHours> getAll();
}
