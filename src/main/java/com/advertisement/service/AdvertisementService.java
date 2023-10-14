package com.advertisement.service;

import java.util.List;
import java.util.Map;

import com.advertisement.entity.Advertisement;

public interface AdvertisementService {
	Advertisement addAdvertisement(Advertisement advertisement);
	
	Advertisement updateAdvertisement(Advertisement advertisement);
	
	void deleteAdvertisement(Integer advertisementID);
	
	Advertisement getAdvertisementByAdvertisementID(Integer advertisementID);
	
	List<Advertisement> getAllAdvertisements(int currentPage);
	
	int getPageTotal();
	
	List<Advertisement> getAdvertisementsByCompositeQuery(Map<String, String[]> map);
}
