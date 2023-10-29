package com.userinfo.dao;

import static util.Constants.PAGE_MAX_RESULT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.userinfo.entity.UserInfo;

import util.HibernateUtil;

public class UserInfo2DAOImpl implements UserInfo2DAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用，所以可設定成實體變數
	private SessionFactory factory;

	public UserInfo2DAOImpl() {
	}

	public UserInfo2DAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(UserInfo entity) {
		// 回傳給 service 剛新增成功的自增主鍵值
		return (Integer) getSession().save(entity);
	}

	@Override
	public int update(UserInfo userInfo) {
		System.out.println("我在update中 userInfo有抓到新的資料");
		try {
			getSession().merge(userInfo);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update失敗");
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		UserInfo emp = getSession().get(UserInfo.class, id);
		if (emp != null) {
			getSession().delete(emp);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public UserInfo getById(Integer userid) {
		try {
			UserInfo userinfo = getSession().get(UserInfo.class, userid);
//			System.out.println("getById成功");
			return userinfo;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getById失敗");
		}
		return null;
	}

	@Override
	public List<UserInfo> getAll() {
		return getSession().createQuery("from UserInfo", UserInfo.class).list();
	}

	@Override
	public List<UserInfo> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0)
			return getAll();

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<UserInfo> criteria = builder.createQuery(UserInfo.class);
		Root<UserInfo> root = criteria.from(UserInfo.class);

		List<Predicate> predicates = new ArrayList<>();

		// 複合查詢:利用MAP /或是sql指令的串接
		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("username".equals(row.getKey())) {
				predicates.add(builder.like(root.get("userName"), "%" + row.getValue() + "%"));
			}

			if ("userid".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("userID"), row.getValue()));
			}

			if ("useremail".equals(row.getKey())) {
				predicates.add(builder.like(root.get("userAccount"), "%" + row.getValue() + "%"));
			}
			if ("userphone".equals(row.getKey())) {
				predicates.add(builder.like(root.get("userPhone"), "%" + row.getValue() + "%"));
			}
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("userID")));
		TypedQuery<UserInfo> query = getSession().createQuery(criteria);

		return query.getResultList();
	}

	@Override
	public List<UserInfo> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT; // 設定的常數見Constants檔案
//		return getSession().createQuery("from UserInfo", UserInfo.class) //設定分頁資料設置
		return getSession().createQuery("from UserInfo", UserInfo.class) // 設定分頁資料設置
				.setFirstResult(first).setMaxResults(PAGE_MAX_RESULT).list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from UserInfo", Long.class).uniqueResult(); // 單筆查詢.uniqueResult()
	}

}
