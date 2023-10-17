package com.dinerinfo.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dinerinfo.dao.DinerInfoDAO;
import com.dinerinfo.dao.DinerInfoDAOHibernateImpl;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

public class DinerInfoServiceImpl  implements DinerInfoService {
	// 一個 service 實體對應一個 dao 實體
	private DinerInfoDAO dao;
	
	public DinerInfoServiceImpl() {
		dao = new DinerInfoDAOHibernateImpl(HibernateUtil.getSessionFactory());
	}

	public DinerInfoDAO getDao() {
		return dao;
	}

	public void setDao(DinerInfoDAO dao) {
		this.dao = dao;
	}

	@Override
	public Integer addDinerInfo(DinerInfo dinerInfo) {
		return dao.add(dinerInfo);
	}

	@Override
	public DinerInfo updateDinerInfo(DinerInfo dinerInfo) {
		return dao.update(dinerInfo);
	}

	


	@Override
	public DinerInfo registerDinerInfo(String dinerName, String dinerPassword, Timestamp dinerRegisterTime,
			String dinerTaxID, String dinerContact, String dinerPhone, String dinerEmail, String dinerAddress,
			String dinerBank, String dinerAccount, String dinerAccountName, String dinerType , String dinerStatus) {
		return dao.register(dinerName, dinerPassword, dinerRegisterTime, dinerTaxID, dinerContact, dinerPhone,
				dinerEmail, dinerAddress, dinerBank, dinerAccount, dinerAccountName, dinerType ,dinerStatus);
	}

	@Override
	public Integer deleteDinerID(Integer dinerID) {
		return dao.delete(dinerID);
	}

	@Override
	public DinerInfo getDinerInfoByDinerID(Integer dinerID) {
		return dao.findByPK(dinerID);
	}
	
	

	@Override
	public DinerInfo getDinerInfoByDinerTaxID(String dinerTaxID) {
		return dao.findByTaxID(dinerTaxID);
	}

	@Override
	public List<DinerInfo> getAllDinerInfos(int currentPage) {
		return dao.getAll();
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
