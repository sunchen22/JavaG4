package com.dinerinfo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.businesshours.entity.BusinessHours;
import com.dinerinfo.entity.DinerInfo;

public interface DinerInfoService {

	Integer addDinerInfo(DinerInfo dinerInfo);

	DinerInfo updateDinerInfo(DinerInfo dinerInfo);

	// 這個方法是註冊的時候用來分辨哪些是可以註冊、哪些是要記錄到session讓註冊者重填資料比較不麻煩的
	DinerInfo registerCheckDinerInfo(DinerInfo dinerInfo) ;
	
	//這個方法才是真正的註冊，寫入資料庫
	DinerInfo registerDinerInfo(DinerInfo dinerInfo) ;
	

	Integer deleteDinerID(Integer dinerID);

	DinerInfo getDinerInfoByDinerID(Integer dinerID);

	DinerInfo getDinerInfoByDinerTaxID(String dinerTaxID);

	DinerInfo getDinerInfoByDinerPhone(String dinerPhone);

	DinerInfo getDinerInfoByDinerEmail(String dinerEmail);
	

	//***************** 此區專門給商家修改使用***************************************
	
	// 在錯誤紀錄區，先比對出來資料庫有無此筆資料
	String checkDinerTaxID(String dinerTaxID , Integer dinerID);
	
	String checkDinerPhone(String dinerPhone , Integer dinerID);
	
	String checkDinerEmail(String dinerEmail , Integer dinerID);

	// 確認商家修改後的資訊
	// dinerTaxID、dinerPhone、dinerEmail 和資料庫裡其他商家沒有重複
	DinerInfo checkEditDinerInfo(DinerInfo oldInfo, DinerInfo editInfo);

	// 用來比對新舊資料後，送出JSON格式資料到dinerUpdate欄位，
	// 返回夾帶更改申請的舊 DinerInfo
	DinerInfo compareDinerInfo(DinerInfo oldInfo, DinerInfo newInfo);

	
	//*************************************************************************

	
	// 用來設定成團訂單金額的方法
	DinerInfo setDinerOrderThreshold(Integer dinerID , String dinerOrderThreshold);
	
	// 用來讀取圖片的方法
	byte[] getDinerBlob(Integer dinerID);
	
	// 用來新增圖片的方法
	DinerInfo setDinerBlob(byte[] dinerBlob , Integer dinerID);
	
	// 用來查廣告
	Set<BusinessHours> getBusinessHoursByDinerID(DinerInfo dinerInfo);
	
	List<DinerInfo> getAllDinerInfos(int currentPage);

	int getPageTotal();

	List<DinerInfo> getDinerInfosByCompositeQuery(Map<String, String[]> map);
}
