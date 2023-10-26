package com.advertisement.dao;

import java.util.List;
import java.util.Map;

import com.advertisement.entity.Advertisement;
import com.dinerinfo.entity.DinerInfo;



public interface AdvertisementDAOC {
	
	
	List<Advertisement> getAllApprovedAD();
	List<Advertisement> getAllSubmittedAD();
	
//	List<Advertisement> getDinerInfoSubmittedAD(Integer dinerID);
	
//	List<Advertisement> getDinerInfoDeactivatedAD(Integer dinerID);
	
	Advertisement update(Integer advertisementID);
	byte[] getImg(Integer advertisementID);
}
