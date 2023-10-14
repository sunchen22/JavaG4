package com.dinerinfo.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dinerinfo.dao.DinerInfoDAO;
import com.dinerinfo.dao.DinerInfoDAOImpl;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

public class DinerInfoServiceImpl  implements DinerInfoService {
	// 一個 service 實體對應一個 dao 實體
	private DinerInfoDAO dao;
	
	public DinerInfoServiceImpl() {
		dao = new DinerInfoDAOImpl(HibernateUtil.getSessionFactory());
	}

	@Override
	public DinerInfo addDinerInfo(DinerInfo dinerInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DinerInfo updateDinerInfo(DinerInfo dinerInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public DinerInfo registerDinerInfo(String dinerName, String dinerPassword, Timestamp dinerRegisterTime,
			String dinerTaxID, String dinerContact, String dinerPhone, String dinerEmail, String dinerAddress,
			String dinerBank, String dinerAccount, String dinerAccountName, String dinerType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDinerID(Integer dinerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DinerInfo getDinerInfoByDinerID(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public DinerInfo getDinerInfoByDinerTaxID(String dinerTaxID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DinerInfo> getAllDinerInfos(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DinerInfo> getDinerInfosByCompositeQuery(Map<String, String[]> map) {
	
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}
		
		System.out.println(query);
		
		return dao.getByCompositeQuery(query);
	}
	
	
	
	
}
