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
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int update(DinerInfo dinerInfo) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int delete(Integer dinerInfoID) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public DinerInfo findByPK(Integer dinerInfoID) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<DinerInfo> getAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
}
