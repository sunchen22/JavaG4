package com.businesshours.dao;

import java.sql.Time;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.businesshours.entity.BusinessHours;
import com.dinerinfo.entity.DinerInfo;

public interface BusinessHoursDAO {
	// 此介面定義對資料庫的相關存取抽象方法
	int add(BusinessHours dinerOpenHours);
	int update(BusinessHours dinerOpenHours);
//	BusinessHours update(BusinessHours dinerOpenHours);
	int delete(Integer dinerOpenHoursID);
	int deleteByDinerID(Integer dinerID);
	BusinessHours findByPK(Integer dinerOpenHoursID);
	
	DinerInfo findByPKJoinDinerInfo(Integer dinerOpenHoursID);
	
	BusinessHours findByDayAndDinerId(Session session,String dayOfWeek, Integer dinerID);
	
	DinerInfo getDinerInfoByDinerID(Integer dinerID);
	
	int isValidBusinessHours(Time openTime , Time closeTime);
    
	BusinessHours setOpenStatus(Integer dinerID, String dayOfWeek, String openStatus);
	
	//判定是否已有該營業日
	String isExistDayOfWeek(Integer dinerID , String dayOfWeek);
	
	BusinessHours setInitialBusinessHours(Integer dinerID);
	
	List<BusinessHours> getAll();
	
//	===============連環方法=============================
	
	// 用 dinerID 去查詢該店家所有的營業時間
	List<BusinessHours> getBusinessHoursByDinerID(Integer dinerID);

	// 用 dayOfWeek 去判斷每天的營業時間， 把每天的營業時間變成 星期幾:營業時間
	Map<String,BusinessHours> getBusinessHoursByDay(List<BusinessHours> businessHoursList);

	// 判定傳進來的Map和dayOfWeek的字串，如果該 dayOfWeek 有營業，回傳1，沒有回傳-1
	// 判斷營業日目前有設定過哪幾天
	int getDayOfWeek(Map<String,BusinessHours> businessHoursByDay , String dayOfWeek);
	
	Time getOpentime(Map<String,BusinessHours> businessHoursByDay);
	Time getClosetime(Map<String,BusinessHours> businessHoursByDay);
//  ====================================================

}
