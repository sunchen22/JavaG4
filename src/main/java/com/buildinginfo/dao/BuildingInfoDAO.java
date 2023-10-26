package com.buildinginfo.dao;

import java.util.List;

import com.buildinginfo.entity.BuildingInfo;

public interface BuildingInfoDAO {
	int add(String name , String address);
	int update(BuildingInfo bif);
	int down(Integer buildingID);
	BuildingInfo findByPK(Integer buildingID);
//	BuildingInfo getOne(String buildingName);
	List<BuildingInfo> getAll();
	
}
