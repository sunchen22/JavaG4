package com.tha103.jo;

import org.hibernate.Session;

import com.usernews.entity.UserNews;

import util.HibernateUtil;


public class HelloSun {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
//			Webempadmin w = new Webempadmin();
//			w.setEmpName("捲捲");
//			w.setEmpPassword("1111");
//			w.setEmpArriveDate(java.sql.Date.valueOf("2023-02-26"));
//			w.setEmpAdminAuthorization("manager");

			
//			ConsumerForm emp = new ConsumerForm();
//			emp.setUserID(3);
//			emp.setConsumerFormTime(java.sql.Date.valueOf("2005-01-01"));
//			emp.setConsumerFormType("B.test");
//			emp.setConsumerFormMail("a123456789@gmail.com");
//			emp.setConsumerFormPhone("0912345678");
//			emp.setConsumerFormContent("餐點中多了蛋白質!!!");
//			emp.setConsumerFormReplyStatus("未回覆");

			
//			DinerForm df = new DinerForm();
//			df.setDinerID(2);
//			df.setDinerFormTime(java.sql.Date.valueOf("2023-02-12"));
//			df.setDinerFormType("錢錢錢錢");
//			df.setDinerFormContent("想申請金流明細");
//			df.setDinerFormReplyStatus("未回覆");
			
//			UserFAQ uf = new UserFAQ();
//			uf.setUserFAQTitle("如何查詢商品配送/退回的進度？");
//			uf.setUserFAQContent("UNIQLO配送查詢服務：https://www.uniqlo.com/tw/OMS/trackingservice/可使用查詢服務確認訂單配送單號，並可透過下方步驟進一步確認配送/退回情況。");
//			uf.setUserFAQReleaseTime(java.sql.Date.valueOf("2023-02-26"));
//			uf.setUserFAQReviseTime(java.sql.Date.valueOf("2023-02-25"));
//			uf.setEmpID(2);
			
			
			UserNews un = new UserNews();
			un.setUserNewsContent("09/22至09/28 實體店舖取貨滿額贈(洗衣球體驗包)");
			un.setEmpID(3);
			un.setUserNewsReleaseTime(java.sql.Date.valueOf("2023-02-26"));
			un.setUserNewsReviseTime(java.sql.Date.valueOf("2023-02-25"));			
			
			
			session.save(un);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}	
		
	}

}
