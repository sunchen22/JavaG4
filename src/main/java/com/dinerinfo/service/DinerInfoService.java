package com.dinerinfo.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.dinerinfo.entity.DinerInfo;

public interface DinerInfoService {

	DinerInfo addDinerInfo(DinerInfo dinerInfo);

	DinerInfo updateDinerInfo(DinerInfo dinerInfo);

	DinerInfo registerDinerInfo(String dinerName, String dinerPassword, Timestamp dinerRegisterTime, String dinerTaxID,
			String dinerContact, String dinerPhone, String dinerEmail, String dinerAddress, String dinerBank,
			String dinerAccount, String dinerAccountName, String dinerType);

	void deleteDinerID(Integer dinerID);

	DinerInfo getDinerInfoByDinerID(Integer dinerID);

	DinerInfo getDinerInfoByDinerTaxID(String dinerTaxID);

	List<DinerInfo> getAllDinerInfos(int currentPage);

	int getPageTotal();

	List<DinerInfo> getDinerInfosByCompositeQuery(Map<String, String[]> map);
}
