package com.dinerinfo.dao;

import java.util.List;

import com.dinerinfo.entity.DinerInfo;

public interface DinerInfoDAO {
	// 此介面定義對資料庫的相關存取抽象方法
	int add(DinerInfo dinerInfo);
	int update(DinerInfo dinerInfo);
	int delete(Integer dinerInfoID);
	DinerInfo findByPK(Integer  dinerInfoID);
	List<DinerInfo> getAll();
	
//  萬用複合查詢(傳入參數型態Map)(回傳 List)
//  List<DinerInfo> getAll(Map<String, String[]> map); 

//  查詢某商家的營業時間(一對多)(回傳 Set)	
//  Set<BusinessHours> getBusinessHoursByDinerID(Integer dinerID);

//  查詢某商家的廣告(一對多)(回傳 Set)	
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
