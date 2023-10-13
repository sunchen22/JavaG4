package com.buildinginfo.dao;

import java.util.List;

import com.buildinginfo.entity.BuildingInfo;

public class TestHibernateBuildingInfoDAO {
	
	public static void main(String[] args) {
		BuildingInfoDAO dao = new BuildingInfoDAOHibernateImpl();
		//新增
		BuildingInfo bif = new BuildingInfo();
//		bif.setBuildingName("FF大樓");
//		bif.setBuildingAddress("台北市松山區6號");
//		dao.add(bif);
		
		
		//修改
//		bif.setBuildingID(13);
//		bif.setBuildingName("KK大樓");
//		bif.setBuildingAddress("台北市松山區8號");
//		dao.update(bif);
		//刪除
//		dao.down(4);
		
		//查詢
		bif = dao.findByPK(4);
		System.out.print(bif.getBuildingName()+" , ");
		System.out.println(bif.getBuildingAddress());
		
		
		//查詢多筆
//		List<BuildingInfo> list = dao.getAll();
//		for(BuildingInfo bifo : list) {
//			System.out.print(bifo.getBuildingID()+ ",");
//			System.out.print(bifo.getBuildingName()+ ",");
//			System.out.println(bifo.getBuildingAddress());
//		}
	}
}
