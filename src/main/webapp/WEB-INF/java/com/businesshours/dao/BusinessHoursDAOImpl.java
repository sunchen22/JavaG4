package com.businesshours.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.businesshours.entity.BusinessHours;




public class BusinessHoursDAOImpl implements BusinessHoursDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public BusinessHoursDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(BusinessHours dinerOpenHours) {
		// 回傳給 service 剛新增成功的自增主鍵值
		return (Integer) getSession().save(dinerOpenHours);
	}

	@Override
	public int update(BusinessHours dinerOpenHours) {
		try {
			getSession().update(dinerOpenHours);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer dinerOpenHoursID) {
		BusinessHours businessHours = getSession().get(BusinessHours.class, dinerOpenHoursID);
		if (businessHours != null) {
			getSession().delete(businessHours);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public int deleteByDinerID(Integer dinerID) {
		BusinessHours businessHours = getSession().get(BusinessHours.class, dinerID);
		if (businessHours != null) {
			getSession().delete(businessHours);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public BusinessHours findByPK(Integer dinerOpenHoursID) {
		return getSession().get(BusinessHours.class, dinerOpenHoursID);
	}

	@Override
	public List<BusinessHours> getAll() {
		return getSession().createQuery("from BusinessHours", BusinessHours.class).list();
	}

	
}
