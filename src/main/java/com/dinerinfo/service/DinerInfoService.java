package com.dinerinfo.service;

import java.util.List;
import java.util.Map;

import com.dinerinfo.entity.DinerInfo;


public interface DinerInfoService {
	

	
	DinerInfo addDinerInfo(DinerInfo dinerInfo);
	
	DinerInfo updateDinerInfo(DinerInfo dinerInfo);
	
	void deleteDinerID(Integer dinerID);
	
	DinerInfo getDinerInfoByDinerID(Integer dinerID);
	
	DinerInfo getDinerInfoByDinerTaxID(String dinerTaxID);
	
	List<DinerInfo> getAllDinerInfos(int currentPage);
	
	int getPageTotal();
	
	List<DinerInfo> getDinerInfosByCompositeQuery(Map<String, String[]> map);
}
