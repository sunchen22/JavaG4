package com.dinerinfo.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.businesshours.entity.BusinessHours;
import com.dinerinfo.entity.DinerInfo;



public interface DinerInfoDAO {
	// 此介面定義對資料庫的相關存取抽象方法
	int add(DinerInfo dinerInfo);
	
	DinerInfo register(DinerInfo dinerInfo);
	
//	DinerInfo register(String dinerName, String dinerPassword, Timestamp dinerRegisterTime ,String dinerTaxID, String dinerContact, String dinerPhone, String dinerEmail, String dinerAddress,
//			String dinerBank, String dinerAccount, String dinerAccountName, String dinerType, String dinerStatus);
	
	
	
	
	DinerInfo update(DinerInfo dinerInfo);
	
	int delete(Integer dinerID);
	
	DinerInfo findByPK(Integer  dinerID);
	
	DinerInfo findByTaxID(String  dinerTaxID);

	DinerInfo findByPhone(String  dinerPhone);
	
	DinerInfo findByEmail(String  dinerEmail);
	
	//============ 專用於註冊 : 以下三個方法用來比對資料庫不能重複的選項 =============
	
	String isExistTaxID(String dinerTaxID);
	String isExistEmail(String dinerEmail);
	String isExistPhone(String dinerPhone);
		
	//===================================================================

	//============ 專用於商家修改 ===========================================
	
	String isExistTaxID(String dinerTaxID, Integer dinerID);
	String isExistEmail(String dinerEmail, Integer dinerID);
	String isExistPhone(String dinerPhone, Integer dinerID);
	
	//===================================================================
	

//	DinerInfo change(DinerInfo dinerInfoChange , DinerInfo oldDinerInfo);
	
	String compare(DinerInfo oldInfo, DinerInfo newInfo);
	
//	public byte[] getBlob(Integer dinerID);
	
//	DinerInfo isValueExist(String column , String value);  // 註冊驗證 : 資料庫比對
	
	List<DinerInfo> getAll();
	
	List<DinerInfo> getByCompositeQuery(Map<String, String> map);
	
	List<DinerInfo> getAll(int currentPage);
	
	long getTotal();
	
//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  List<DinerInfo> getAll(Map<String, String[]> map); 

//  查詢某商家的營業時間(一對多)(回傳 Set)	
  Set<BusinessHours> getBusinessHoursByDinerID(Integer dinerID);

////  查詢某商家的廣告(一對多)(回傳 Set)	
//  Set<Advertisement> getAdvertisementByDinerID(Integer dinerID);

//	查詢某商家的商店評分(一對多)(回傳 Set)	
//  Set<DinerRatingComment> getDinerRatingCommentByDinerID(Integer dinerID);

//	查詢某商家的團購訂單(一對多)(回傳 Set)	
//  Set<GroupOrder> getGroupOrderByDinerID(Integer dinerID);

//  查詢某商家的商品資料(一對多)(回傳 Set)	
//  Set<Product> getProductByDinerID(Integer dinerID);


//  查詢某商家的商家問卷留言(一對多)(回傳 Set)	
//  Set<DinerForm> getDinerFormByDinerInfoID(Integer dinerID);
  
//  查詢某商家的最愛商家(一對多)(回傳 Set)	
//  Set<Favorite> getFavoriteByDinerInfoID(Integer dinerID);	
	
}
