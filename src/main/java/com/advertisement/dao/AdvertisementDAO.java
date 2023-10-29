package com.advertisement.dao;

import java.util.List;
import java.util.Map;

import com.advertisement.entity.Advertisement;
import com.dinerinfo.entity.DinerInfo;



public interface AdvertisementDAO {
	// 此介面定義對資料庫的相關存取抽象方法
	int add(Advertisement advertisement);
	void addAdvertisement(Advertisement advertisement);
	int update(Advertisement advertisement);
	int delete(Integer advertisementId);
	Advertisement findByPK(Integer advertisementId);
	
	DinerInfo getDinerInfoByDinerID(Integer dinerID);
	
	List<Advertisement> getSubmitted(Integer dinerID);
	List<Advertisement> getActive(Integer dinerID);
	List<Advertisement> getDeactivated(Integer dinerID);
	List<Advertisement> getAdvertisementsByDinerID(Integer dinerID);
	
	
	List<Advertisement> getAll();
	
	List<Advertisement> getByCompositeQuery(Map<String, String> map);
	
	List<Advertisement> getAll(int currentPage);
	
	long getTotal();
}
