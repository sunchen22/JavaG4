package com.businesshours.dao;

import java.sql.Time;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.businesshours.entity.BusinessHours;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

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
	public int isValidBusinessHours(Time openTime, Time closeTime) {
		if (openTime == null || closeTime == null) {
			return -1;
		} else if (openTime.compareTo(closeTime) < 0) {
			return 1; // 確定開店時間早於關店時間，返回1
		} else
			return -1;
	}

	@Override
	public DinerInfo findByPKJoinDinerInfo(Integer dinerOpenHoursID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			DinerInfo dinerInfo = getSession().get(BusinessHours.class, dinerOpenHoursID).getDinerInfo();
			return dinerInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BusinessHours> getAll() {
		return getSession().createQuery("from BusinessHours", BusinessHours.class).list();
	}

	// 用 dinerID 去查詢該店家所有的營業時間
	@Override
	public List<BusinessHours> getBusinessHoursByDinerID(Integer dinerID) {

		try {
			 
		String hql = "FROM BusinessHours b WHERE b.dinerInfo.dinerID = :dinerID";

//		String hql = "FROM OrdersVO AS o WHERE o.publisherVO.pubID = :pubID";
		Query<BusinessHours> query = getSession().createQuery(hql , BusinessHours.class);
		query.setParameter("dinerID", dinerID);
		List<BusinessHours> businessHoursList = query.getResultList();
		return businessHoursList; 

		}catch(Exception e) {
			e.printStackTrace();

			}
		 return Collections.emptyList(); // 如果查詢失敗或沒有結果，則返回空列表

	}

	// 把每天的營業時間變成 星期幾:營業時間
	@Override
	public Map<String, BusinessHours> getBusinessHoursByDay(List<BusinessHours> businessHoursList) {
		Map<String, BusinessHours> businessHoursByDay = new HashMap<>();
		for (BusinessHours businessHours : businessHoursList) {
			String day = businessHours.getDayOfWeek();
			businessHoursByDay.put(day, businessHours);
		}
		return businessHoursByDay;
	}
	
	

	@Override
	public String isExistDayOfWeek(Integer dinerID, String dayOfWeek) {
		// 從dinerID取得店家的營業時間列表
		List<BusinessHours> businessHoursList = getBusinessHoursByDinerID(dinerID);

		// 檢查是否有指定的dayOfWeek
		for (BusinessHours businessHours : businessHoursList) {
			if (businessHours.getDayOfWeek().equalsIgnoreCase(dayOfWeek)) {
				return dayOfWeek;  
				//如果該店家本來就有設定該日的營業時間，就返回原值
			}
		}
		return null; 
		//如果該店家尚未設定，返回null
	}

	@Override
	public BusinessHours setInitialBusinessHours(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDayOfWeek(Map<String, BusinessHours> businessHoursByDay, String dayOfWeek) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Time getOpentime(Map<String, BusinessHours> businessHoursByDay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Time getClosetime(Map<String, BusinessHours> businessHoursByDay) {
		// TODO Auto-generated method stub
		return null;
	}



}
