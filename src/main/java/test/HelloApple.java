//package test;
//
//import org.hibernate.Session;
//
//import com.dinerinfo.dao.DinerInfoDAOHibernateImpl;
//import com.dinerinfo.entity.DinerInfo;
//
//import util.HibernateUtil;
//
//public class HelloApple {
//	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			
////			DinerInfoDAOHibernateImpl dd = new DinerInfoDAOHibernateImpl();
////			DinerInfo di = new DinerInfo();
////			di = dd.findByTaxID("12345678");
////			System.out.println(di.getDinerName());
//			
//			
////			session.beginTransaction();
//			
//////			測試DinerInfo
////			//Timestamp的當前時間設置
////			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
////			
////			DinerInfo dinerInfo1 = new DinerInfo();
////			dinerInfo1.setDinerName("測試新增店名3");
////			dinerInfo1.setDinerPassword("123Pssw0rd1456");
////			dinerInfo1.setDinerRegisterTime(currentTime);
////			dinerInfo1.setDinerTaxID("87651321");
////			dinerInfo1.setDinerContact("莊3富");
////			dinerInfo1.setDinerPhone("0912552457");
////			dinerInfo1.setDinerEmail("nscafe3478@gmail.com");
////			dinerInfo1.setDinerAddress("台北市羅斯福路三段124巷7樓9號");
////			dinerInfo1.setDinerBank("001");
////			dinerInfo1.setDinerAccount("1234547890123556");
////			dinerInfo1.setDinerAccountName("莊3富");
////			dinerInfo1.setDinerType("M");
////			dinerInfo1.setDinerStatus("Active");
////			dinerInfo1.setDinerOrderThreshold(2000);
////			//照片傳入用另外的方式
////			
//			//查詢DinerInfo
////			session.save(dinerInfo1);
//			
//			
//////			測試BusinessHours
////			// 新增
////			BusinessHours businessHours1 = new BusinessHours();
////			
//////			時間轉換器   String → sql.Time
////	        String optime = "9:00:00";
////			Time tOpen = Time.valueOf(optime);
////	        String cltime = "13:30:00";
////			Time tClose = Time.valueOf(cltime);
////						
////			//ID自動生成，不做新增設定
////			businessHours1.setDinerID(6);
////			businessHours1.setDayOfWeek("Monday");
////			businessHours1.setOpenTime(tOpen);
////			businessHours1.setCloseTime(tClose);
////			businessHours1.setOpenStatus("Open");
////
////			session.save(businessHours1);
//			
//			
//			
////			測試Advertisement
////			// 新增
//			
////	        //時間轉換器 String → sql.Timestamp
////	        String UpTimeStr1 = "2023-09-09 00:00:00";
////			Timestamp ut1 = Timestamp.valueOf(UpTimeStr1);
////			String DownTimeStr1 = "2023-10-10 00:00:00";
////			Timestamp dt1 = Timestamp.valueOf(DownTimeStr1);
////			
////			Advertisement advertisement1 = new Advertisement();
////			advertisement1.setDinerID(6);
////			advertisement1.setAdvertisementName("吃到飽");
////			advertisement1.setAdvertisementUpTime(ut1);
////			advertisement1.setAdvertisementDownTime(dt1);
////			//AdvertisementDuringTime在mysql資料庫裡已有天數計算器，這裡不另外新增
////			advertisement1.setAdvertisementStatus("Submitted");
////			session.save(advertisement1);			
////			
////			session.getTransaction().commit();
//			
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		} finally {
//			HibernateUtil.shutdown();
//		}	
//		
//	}
//}
