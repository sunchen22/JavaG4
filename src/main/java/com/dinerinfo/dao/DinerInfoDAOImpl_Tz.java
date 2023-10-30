package com.dinerinfo.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

public class DinerInfoDAOImpl_Tz {

	public List<DinerInfo> searchDiners(String nameKeyword, String addressKeyword) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//		session.beginTransaction();

			StringBuilder sql = new StringBuilder("SELECT * FROM DinerInfo WHERE dinerStatus = 'Active'");

			nameKeyword = (nameKeyword != null) ? nameKeyword.trim() : null;
			addressKeyword = (addressKeyword != null) ? addressKeyword.trim() : null;

			// 根據關鍵字添加AND條件
			if (nameKeyword != null && !nameKeyword.isEmpty()) {
				sql.append(" AND dinerName LIKE :name");
			}
			if (addressKeyword != null && !addressKeyword.isEmpty()) {
				sql.append(" AND dinerAddress LIKE :address");
			}

			NativeQuery<DinerInfo> query = session.createNativeQuery(sql.toString(), DinerInfo.class);

			// 根據KeyWord新增SQL參數
			if (nameKeyword != null && !nameKeyword.isEmpty()) {
				query.setParameter("name", "%" + nameKeyword + "%");
			}
			if (addressKeyword != null && !addressKeyword.isEmpty()) {
				query.setParameter("address", "%" + addressKeyword + "%");
			}

			List<DinerInfo> diners = query.getResultList();

//		session.getTransaction().commit();
			return diners;
		} catch (Exception e) {
			e.printStackTrace();
//		session.getTransaction().rollback();
		}
		return null;
	}

	// sun add
	public int dinerCount() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			String sql = "SELECT COUNT(*) FROM dinerinfo WHERE dinerStatus = 'Active'";
			BigInteger count = (BigInteger) session.createNativeQuery(sql).getSingleResult();
			return count.intValue();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

}
