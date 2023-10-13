package com.advertisement.dao;

import java.sql.Timestamp;
import java.util.List;

import com.advertisement.entity.Advertisement;

public class TestAdvertisementDAO {
	
	public static void main(String[] args) throws Exception {
		AdvertisementDAO dao = new AdvertisementDAOImpl(null);

//		// 新增
		
        //時間轉換器 String → sql.Timestamp
        String UpTimeStr1 = "2023-09-09 00:00:00";
		Timestamp ut1 = Timestamp.valueOf(UpTimeStr1);
		String DownTimeStr1 = "2023-10-10 00:00:00";
		Timestamp dt1 = Timestamp.valueOf(DownTimeStr1);
		
		Advertisement advertisement1 = new Advertisement();
		advertisement1.setDinerID(6);
//		圖片另外傳入
		advertisement1.setAdvertisementName("快樂");
		advertisement1.setAdvertisementUpTime(ut1);
		advertisement1.setAdvertisementDownTime(dt1);
		//AdvertisementDuringTime在mysql資料庫裡已有天數計算器，這裡不另外新增
		advertisement1.setAdvertisementStatus("Submitted");

		dao.add(advertisement1);
//
//		// 修改
//		Advertisement advertisement2 = new Advertisement();
//		advertisement2.setAdvertisementID(7001);
//		advertisement2.setAdvertisementUpTime("David Jr.");
//		advertisement2.setAdvertisementDownTime("MANAGER2");
//		advertisement2.setAdvertisementDuringTime(new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-07"));
//		advertisement2.setAdvertisementStatus(new BigDecimal(20000));

//		dao.update(advertisement2);
//
//		// 刪除
//		dao.delete(7015);
//
//		// 查詢單筆
//		Advertisement advertisement3 = dao.findByPK(7001);
//		System.out.print(advertisement3.getAdvertisementID() + ",");
//		System.out.print(advertisement3.getAdvertisementUpTime() + ",");
//		System.out.print(advertisement3.getAdvertisementDownTime() + ",");
//		System.out.print(advertisement3.getAdvertisementDuringTime() + ",");
//		System.out.print(advertisement3.getAdvertisementStatus() + ",");

//		System.out.println("---------------------");

		// 查詢多筆
//		List<Advertisement> list = dao.getAll();
//		for (Advertisement advertisement : list) {
//			System.out.print(advertisement.getAdvertisementID() + ",");
//			System.out.print(advertisement.getAdvertisementUpTime() + ",");
//			System.out.print(advertisement.getAdvertisementDownTime() + ",");
//			System.out.print(advertisement.getAdvertisementDuringTime() + ",");
//			System.out.print(advertisement.getAdvertisementStatus());
//
//			System.out.println();
//		}
	}

}
