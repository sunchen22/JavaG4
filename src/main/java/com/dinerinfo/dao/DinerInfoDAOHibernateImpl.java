package com.dinerinfo.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.dinerinfo.controller.DinerPasswordGenerator;
import com.dinerinfo.entity.DinerInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DinerInfoDAOHibernateImpl implements DinerInfoDAO {
	private SessionFactory factory;

	public DinerInfoDAOHibernateImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(DinerInfo dinerInfo) {
		System.out.println("開始增加商家資料");
		return (Integer) getSession().save(dinerInfo);
	}

	@Override
	public DinerInfo register(DinerInfo dinerInfo) {

		// 應該要把 註冊時間、初始密碼、dinerStatus = "Submitted" 寫在這裡，因為這裡才是真正註冊成功的地方
		// Timestamp的當前時間設置
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		dinerInfo.setDinerRegisterTime(currentTime);// 存下註冊者的註冊時間
 		// 調用寫好的密碼產生器，產生一組預設的密碼
		String temporaryPassword = DinerPasswordGenerator.generateTemporaryPassword(6);
		dinerInfo.setDinerPassword(temporaryPassword); // 存下密碼產生器產生的密碼	
		
		String dinerStatus = "Submitted";
		dinerInfo.setDinerStatus(dinerStatus);

		Session session = getSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction(); // 開始事務
			session.save(dinerInfo);
			tx.commit(); // 提交事務
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback(); // 如果出現錯誤，則回滾事務
			}
			throw e; // 或者選擇其他的錯誤處理方式
		}

		return dinerInfo;
	}

//	@Override
//	public DinerInfo register(String dinerName, String dinerPassword, Timestamp dinerRegisterTime, String dinerTaxID,
//			String dinerContact, String dinerPhone, String dinerEmail, String dinerAddress, String dinerBank,
//			String dinerAccount, String dinerAccountName, String dinerType, String dinerStatus) {
//
//		DinerInfo dinerInfo = new DinerInfo();
//
//		dinerInfo.setDinerName(dinerName);
//		dinerInfo.setDinerPassword(dinerPassword);
//		dinerInfo.setDinerRegisterTime(dinerRegisterTime);
//		dinerInfo.setDinerTaxID(dinerTaxID);
//		dinerInfo.setDinerContact(dinerContact);
//		dinerInfo.setDinerPhone(dinerPhone);
//		dinerInfo.setDinerEmail(dinerEmail);
//		dinerInfo.setDinerAddress(dinerAddress);
//		dinerInfo.setDinerBank(dinerBank);
//		dinerInfo.setDinerAccount(dinerAccount);
//		dinerInfo.setDinerAccountName(dinerAccountName);
//		dinerInfo.setDinerType(dinerType);
//		dinerInfo.setDinerStatus(dinerStatus);
//
//		Session session = getSession();
//		org.hibernate.Transaction tx = null;
//		try {
//			tx = session.beginTransaction(); // 開始事務
//			session.save(dinerInfo);
//			tx.commit(); // 提交事務
//		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback(); // 如果出現錯誤，則回滾事務
//			}
//			throw e; // 或者選擇其他的錯誤處理方式
//		}
//
//		return dinerInfo;
//
//	}

	@Override
	public DinerInfo update(DinerInfo dinerInfo) {
		try {
			getSession().update(dinerInfo);
			return dinerInfo; // 成功的話，回傳dinerInfo
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int delete(Integer dinerID) {

		DinerInfo dinerInfo = getSession().get(DinerInfo.class, dinerID);
		if (dinerInfo != null) {
			getSession().delete(dinerInfo);
			return 1; // 成功的話，回傳1
		} else {
			return -1; // 不成功的話，回傳-1
		}
	}

	@Override
	public DinerInfo findByPK(Integer dinerID) {
		return getSession().get(DinerInfo.class, dinerID);
	}

	@Override
	public DinerInfo findByTaxID(String dinerTaxID) {
		Session session = getSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction(); // 開始事務

			String hql = "FROM DinerInfo WHERE dinerTaxID  = :dinerTaxID";
			Query<DinerInfo> query = getSession().createQuery(hql, DinerInfo.class);
			query.setParameter("dinerTaxID", dinerTaxID);
			DinerInfo dinerInfo = query.uniqueResult(); // 因為dinerTaxID應該是唯一的，所以使用uniqueResult()取得單一結果

			tx.commit(); // 提交事務
			return dinerInfo; // 成功的話，回傳 dinerInfo 物件
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			return null;
		}
	}

	@Override
	public DinerInfo findByPhone(String dinerPhone) {
		Session session = getSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction(); // 開始事務

			String hql = "FROM DinerInfo WHERE dinerPhone  = :dinerPhone";
			Query<DinerInfo> query = getSession().createQuery(hql, DinerInfo.class);
			query.setParameter("dinerPhone", dinerPhone);
			DinerInfo dinerInfo = query.uniqueResult(); // 因為dinerPhone應該是唯一的，所以使用uniqueResult()取得單一結果

			tx.commit(); // 提交事務
			return dinerInfo; // 成功的話，回傳 dinerInfo 物件
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			return null;
		}
	}

	@Override
	public DinerInfo findByEmail(String dinerEmail) {

		Session session = getSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction(); // 開始事務

			String hql = "FROM DinerInfo WHERE dinerEmail  = :dinerEmail";
			Query<DinerInfo> query = getSession().createQuery(hql, DinerInfo.class);
			query.setParameter("dinerEmail", dinerEmail);
			DinerInfo dinerInfo = query.uniqueResult(); // 因為dinerEmail應該是唯一的，所以使用uniqueResult()取得單一結果

			tx.commit(); // 提交事務
			return dinerInfo; // 成功的話，回傳 dinerInfo 物件
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			return null;
		}
	}

	// ============ 以下三個方法用來比對資料庫不能重複的選項 ===========

	@Override
	public String isExistTaxID(String dinerTaxID) {
		try {
			String hql = "FROM DinerInfo WHERE dinerTaxID = :dinerTaxID";
			Query<DinerInfo> query = getSession().createQuery(hql, DinerInfo.class);
			query.setParameter("dinerTaxID", dinerTaxID);
			DinerInfo dinerInfo = query.uniqueResult();
			return (dinerInfo != null) ? dinerInfo.getDinerTaxID() : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String isExistEmail(String dinerEmail) {
		try {
			String hql = "FROM DinerInfo WHERE dinerEmail = :dinerEmail";
			Query<DinerInfo> query = getSession().createQuery(hql, DinerInfo.class);
			query.setParameter("dinerEmail", dinerEmail);
			DinerInfo dinerInfo = query.uniqueResult();
			return (dinerInfo != null) ? dinerInfo.getDinerEmail() : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String isExistPhone(String dinerPhone) {
		try {
			String hql = "FROM DinerInfo WHERE dinerPhone = :dinerPhone";
			Query<DinerInfo> query = getSession().createQuery(hql, DinerInfo.class);
			query.setParameter("dinerPhone", dinerPhone);
			DinerInfo dinerInfo = query.uniqueResult();
			return (dinerInfo != null) ? dinerInfo.getDinerPhone() : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ======================================================

	@Override
	public String compare(DinerInfo oldInfo, DinerInfo newInfo) {
		// 設定一個 JSON 物件來儲存有變動的欄位
		JsonObject difference = new JsonObject();

		if (!oldInfo.getDinerName().equals(newInfo.getDinerName())) {
			difference.addProperty("dinerName", newInfo.getDinerName());
		}
		if (!oldInfo.getDinerPassword().equals(newInfo.getDinerPassword())) {
			difference.addProperty("dinerPassword", newInfo.getDinerPassword());
		}
		if (!oldInfo.getDinerTaxID().equals(newInfo.getDinerTaxID())) {
			difference.addProperty("dinerTaxID", newInfo.getDinerTaxID());
		}
		if (!oldInfo.getDinerContact().equals(newInfo.getDinerContact())) {
			difference.addProperty("dinerContact", newInfo.getDinerContact());
		}
		if (!oldInfo.getDinerPhone().equals(newInfo.getDinerPhone())) {
			difference.addProperty("dinerPhone", newInfo.getDinerPhone());
		}
		if (!oldInfo.getDinerEmail().equals(newInfo.getDinerEmail())) {
			difference.addProperty("dinerEmail", newInfo.getDinerEmail());
		}
		if (!oldInfo.getDinerAddress().equals(newInfo.getDinerAddress())) {
			difference.addProperty("dinerAddress", newInfo.getDinerAddress());
		}
		if (!oldInfo.getDinerBank().equals(newInfo.getDinerBank())) {
			difference.addProperty("dinerBank", newInfo.getDinerBank());
		}
		if (!oldInfo.getDinerAccount().equals(newInfo.getDinerAccount())) {
			difference.addProperty("dinerAccount", newInfo.getDinerAccount());
		}
		if (!oldInfo.getDinerAccountName().equals(newInfo.getDinerAccountName())) {
			difference.addProperty("dinerAccountName", newInfo.getDinerAccountName());
		}
		if (!oldInfo.getDinerType().equals(newInfo.getDinerType())) {
			difference.addProperty("dinerType", newInfo.getDinerType());
		}

		// 使用 Gson 將 JsonObject 轉換成 String
		Gson gson = new Gson();
		String jsonString = gson.toJson(difference);
		// 返回對比完的 String , 以便放入商家修改的欄位中
		return jsonString;
	}

	@Override
	public List<DinerInfo> getAll() {
		return getSession().createQuery("FROM DinerInfo", DinerInfo.class).list();

	}

	@Override
	public List<DinerInfo> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DinerInfo> getAll(int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

}