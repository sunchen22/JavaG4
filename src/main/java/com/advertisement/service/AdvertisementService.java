package com.advertisement.service;

import java.util.List;
import java.util.Map;

import com.advertisement.entity.Advertisement;
import com.dinerinfo.entity.DinerInfo;

public interface AdvertisementService {
	void addAdvertisement(Advertisement advertisement,Integer dinerID);
	
	Advertisement updateAdvertisement(Advertisement advertisement);
	
	DinerInfo getDinerInfoByDinerID(Integer dinerID);
	
	void deleteAdvertisement(Integer advertisementID);
	
	Advertisement getAdvertisementByAdvertisementID(Integer advertisementID);
	
	Advertisement setAdvertisementBlob(byte[] advertisementBlob , Integer dinerID);
	
	List<Advertisement> getAdvertisementsByDinerID(Integer dinerID);

	List<Advertisement> getAllAdvertisements(int currentPage);
	
	int getPageTotal();
	
	List<Advertisement> getAdvertisementsByCompositeQuery(Map<String, String[]> map);
}
