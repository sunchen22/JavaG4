package com.dinerinfo.dao;

import java.sql.Timestamp;

import com.dinerinfo.entity.DinerInfo;

public class TestDinerInfoHiberateDAO {
	public static void main(String[] args) {
		DinerInfoDAO dao = new DinerInfoDAOHibernateImpl();

//	DinerInfo dinerInfo1 = new DinerInfo();
//	//ID自動生成，不做新增設定
//	
//	//Timestamp的當前時間設置
//	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//	
//	
//	dinerInfo1.setDinerName("測試新增店名6");
//	dinerInfo1.setDinerPassword("123Pssw0rd1416");
//	dinerInfo1.setDinerRegisterTime(currentTime);
//	dinerInfo1.setDinerTaxID("87653321");
//	dinerInfo1.setDinerContact("莊三富");
//	dinerInfo1.setDinerPhone("0912222157");
//	dinerInfo1.setDinerEmail("nsca3e1478@gmail.com");
//	dinerInfo1.setDinerAddress("台北市羅斯福路2段124巷7樓9號");
//	dinerInfo1.setDinerBank("002");
//	dinerInfo1.setDinerAccount("1234567890423556");
//	dinerInfo1.setDinerAccountName("莊三富");
//	dinerInfo1.setDinerType("M");
//	dinerInfo1.setDinerStatus("Active");
//	dinerInfo1.setDinerOrderThreshold(2000);
//	//照片傳入用另外的方式
//	dao.add(dinerInfo1);
//
//	// 修改
//	時間轉換器   String → sql.Timestamp
//	String dateTimeStr2 = "2023-08-29 20:25:58";
//	Timestamp ts = Timestamp.valueOf(dateTimeStr2);
//	
//    DinerInfo dinerInfo2 = new DinerInfo();
//	
//	dinerInfo2.setDinerName("測試修改2");
//	dinerInfo2.setDinerPassword("123Pssw0rd1456");
//	dinerInfo2.setDinerRegisterTime(ts);
//	dinerInfo2.setDinerTaxID("12547787");
//	dinerInfo2.setDinerContact("莊二富");
//	dinerInfo2.setDinerPhone("0912521478");
//	dinerInfo2.setDinerEmail("nscaf8e8478@gmail.com");
//	dinerInfo2.setDinerAddress("台北市羅斯福路三段134巷7樓9號");
//	dinerInfo2.setDinerBank("003");
//	dinerInfo2.setDinerAccount("3214567890123551");
//	dinerInfo2.setDinerAccountName("莊二富");
//	dinerInfo2.setDinerType("X");
//	dinerInfo2.setDinerStatus("Deactive");
//	dinerInfo2.setDinerOrderThreshold(3000);		
////  //照片傳入用另外的方式
//	dinerInfo2.setDinerID(9);	
//	
//	dao.update(dinerInfo2);
//
//	// 刪除
//	dao.delete(9);
//
//	// 查詢單筆
//	DinerInfo dinerInfo3 = dao.findByPrimaryKey(4);

//		 查詢單筆
		DinerInfo dinerInfo5 = dao.findByTaxID("12345678");
		System.out.print(dinerInfo5.getDinerName());
		
//	//ID自動生成，不做新增設定	
//	System.out.print(dinerInfo3.getDinerName() + ",");
//	System.out.print(dinerInfo3.getDinerPassword() + ",");
//	System.out.print(dinerInfo3.getDinerRegisterTime() + ",");
//	System.out.print(dinerInfo3.getDinerTaxID() + ",");
//	System.out.print(dinerInfo3.getDinerContact() + ",");
//	System.out.print(dinerInfo3.getDinerPhone() + ",");
//	System.out.print(dinerInfo3.getDinerEmail() + ",");
//	System.out.print(dinerInfo3.getDinerAddress() + ",");
//	System.out.print(dinerInfo3.getDinerBank() + ",");
//	System.out.print(dinerInfo3.getDinerAccount() + ",");
//	System.out.print(dinerInfo3.getDinerAccountName() + ",");
//	System.out.print(dinerInfo3.getDinerType() + ",");
//	System.out.print(dinerInfo3.getDinerStatus() + ",");
//	System.out.print(dinerInfo3.getDinerOrderThreshold() + ",");
//	System.out.println(dinerInfo3.getDinerBlob());
//	System.out.println("---------------------");

		// 查詢多筆
//	List<DinerInfo> list = dao.getAll();
//	for (DinerInfo aDinerInfo : list) {
//		System.out.print(aDinerInfo.getDinerName() + ",");
//		System.out.print(aDinerInfo.getDinerPassword() + ",");
//		System.out.print(aDinerInfo.getDinerRegisterTime() + ",");
//		System.out.print(aDinerInfo.getDinerTaxID() + ",");
//		System.out.print(aDinerInfo.getDinerContact() + ",");
//		System.out.print(aDinerInfo.getDinerPhone() + ",");
//		System.out.print(aDinerInfo.getDinerEmail() + ",");
//		System.out.print(aDinerInfo.getDinerAddress() + ",");
//		System.out.print(aDinerInfo.getDinerBank() + ",");
//		System.out.print(aDinerInfo.getDinerAccount() + ",");
//		System.out.print(aDinerInfo.getDinerAccountName() + ",");
//		System.out.print(aDinerInfo.getDinerType() + ",");
//		System.out.print(aDinerInfo.getDinerStatus() + ",");
//		System.out.print(aDinerInfo.getDinerOrderThreshold() + ",");
//		System.out.println(aDinerInfo.getDinerBlob());
//		System.out.println();
//	}

	}
}
