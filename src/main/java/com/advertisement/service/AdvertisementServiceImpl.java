package com.advertisement.service;

import  static  util.Constants.PAGE_MAX_RESULT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.advertisement.dao.AdvertisementDAO;
import com.advertisement.dao.AdvertisementDAOHibernateImpl;
import com.advertisement.entity.Advertisement;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;



//搭配 JSP / Thymeleaf 後端渲染畫面，將交易動作至於 view filter
public class AdvertisementServiceImpl implements AdvertisementService {
	// 一個 service 實體對應一個 dao 實體
	private  AdvertisementDAO dao;

	public AdvertisementServiceImpl() {
		dao = new AdvertisementDAOHibernateImpl(HibernateUtil.getSessionFactory());
	}
	

	@Override
	public void addAdvertisement(Advertisement advertisement,Integer dinerID) {
	 DinerInfo dinerInfo = dao.getDinerInfoByDinerID(dinerID);
	 advertisement.setAdvertisementStatus("Submitted");
	 advertisement.setDinerid(dinerInfo);
	 dinerInfo.setDinerID(dinerID);
	 dao.addAdvertisement(advertisement);
		 
	}

	@Override
	public Advertisement updateAdvertisement(Advertisement advertisement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAdvertisement(Integer advertisementID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Advertisement getAdvertisementByAdvertisementID(Integer advertisementID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	


	
	
	@Override
	public DinerInfo getDinerInfoByDinerID(Integer dinerID) {
		return dao.getDinerInfoByDinerID(dinerID);
	}


	@Override
	public List<Advertisement> getAdvertisementsByCompositeQuery(Map<String, String[]> map) {
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
	


	@Override
	public Advertisement setAdvertisementBlob(byte[] advertisementBlob, Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Advertisement> getAdvertisementsByDinerID(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getPageTotal() {
		long total = dao.getTotal();
		// 計算Advertisement數量每頁5筆的話總共有幾頁
		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}


	@Override
	public List<Advertisement> getAllAdvertisements(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

}
