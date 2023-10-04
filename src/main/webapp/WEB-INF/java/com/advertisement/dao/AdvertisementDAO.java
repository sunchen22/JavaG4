package com.advertisement.dao;

import java.util.List;

import com.advertisement.entity.Advertisement;

public interface AdvertisementDAO {
	// 此介面定義對資料庫的相關存取抽象方法
	int add(Advertisement advertisement);
	int update(Advertisement advertisement);
	int delete(Integer advertisementID);
	Advertisement findByPK(Integer advertisementID);
	List<Advertisement> getAll();
}
