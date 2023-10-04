package com.dinerinfo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dinerinfo.entity.DinerInfo;


public class DinerInfoDAOImpl implements DinerInfoDAO{
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
		private SessionFactory factory;

		public DinerInfoDAOImpl(SessionFactory factory) {
			this.factory = factory;
		}
		
		// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
		// 以避免請求執行緒共用了同個 Session
		private Session getSession() {
			return factory.getCurrentSession();
		}

		@Override
		public int add(DinerInfo dinerInfo) {
			// 回傳給 service 剛新增成功的自增主鍵值
			return (Integer) getSession().save(dinerInfo);
		}

		@Override
		public int update(DinerInfo dinerInfo) {
			try {
				getSession().update(dinerInfo);
				return 1;
			} catch (Exception e) {
				return -1;
			}
		}

		@Override
		public int delete(Integer dinerInfoID) {
			DinerInfo dinerInfo = getSession().get(DinerInfo.class, dinerInfoID);
			if (dinerInfo != null) {
				getSession().delete(dinerInfoID);
				// 回傳給 service，1代表刪除成功
				return 1;
			} else {
				// 回傳給 service，-1代表刪除失敗
				return -1;
			}
		}

		@Override
		public DinerInfo findByPK(Integer dinerInfoID) {
			return getSession().get(DinerInfo.class, dinerInfoID);
		}

		@Override
		public List<DinerInfo> getAll() {
			return getSession().createQuery("from DinerInfo", DinerInfo.class).list();
		}
		
		
}
