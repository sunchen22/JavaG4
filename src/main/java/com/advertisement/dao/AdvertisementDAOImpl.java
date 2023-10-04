package com.advertisement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.advertisement.entity.Advertisement;



public class AdvertisementDAOImpl implements AdvertisementDAO{
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public AdvertisementDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int add(Advertisement advertisement) {
		// 回傳給 service 剛新增成功的自增主鍵值
		return (Integer) getSession().save(advertisement);
	}

	@Override
	public int update(Advertisement advertisement) {
		try {
			getSession().update(advertisement);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer advertisementID) {
		Advertisement advertisement = getSession().get(Advertisement.class, advertisementID);
		if (advertisement != null) {
			getSession().delete(advertisement);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public Advertisement findByPK(Integer advertisementID) {
		return getSession().get(Advertisement.class, advertisementID);
	}

	@Override
	public List<Advertisement> getAll() {
		return getSession().createQuery("from Advertisement", Advertisement.class).list();
	}
	
}
