package com.dinerinfo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dinerinfo.entity.DinerInfo;

public interface DinerInfoService {

	Integer addDinerInfo(DinerInfo dinerInfo);

	DinerInfo updateDinerInfo(DinerInfo dinerInfo);



//	DinerInfo registerCheckDinerInfo(String dinerName, String dinerPassword, Timestamp dinerRegisterTime,
//			String dinerTaxID, String dinerContact, String dinerPhone, String dinerEmail, String dinerAddress,
//			String dinerBank, String dinerAccount, String dinerAccountName, String dinerType , String dinerStatus) ;

	// 這個方法是註冊的時候用來分辨哪些是可以註冊、哪些是要記錄到session讓註冊者重填資料比較不麻煩的
	DinerInfo registerCheckDinerInfo(DinerInfo dinerInfo) ;
	
	//這個方法才是真正的註冊，寫入資料庫
	DinerInfo registerDinerInfo(DinerInfo dinerInfo) ;
	
	
	Integer deleteDinerID(Integer dinerID);

	DinerInfo getDinerInfoByDinerID(Integer dinerID);

	DinerInfo getDinerInfoByDinerTaxID(String dinerTaxID);

	DinerInfo getDinerInfoByDinerPhone(String dinerPhone);

	DinerInfo getDinerInfoByDinerEmail(String dinerEmail);

	String compareDinerInfo(DinerInfo oldInfo, DinerInfo newInfo);

	List<DinerInfo> getAllDinerInfos(int currentPage);

	int getPageTotal();

	List<DinerInfo> getDinerInfosByCompositeQuery(Map<String, String[]> map);
}
