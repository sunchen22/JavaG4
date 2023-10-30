package com.advertisement.dao;

import java.math.BigDecimal;
import java.sql.Date;
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

import com.advertisement.entity.Advertisement;
import com.dinerinfo.entity.DinerInfo;

import  static  util.Constants.PAGE_MAX_RESULT;

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

	@Override
	public List<Advertisement> getByCompositeQuery(Map<String, String> map) {
		if (map.size() == 0)
			return getAll();

		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Advertisement> criteria = builder.createQuery(Advertisement.class);
		Root<Advertisement> root = criteria.from(Advertisement.class);

		List<Predicate> predicates = new ArrayList<>();

		if (map.containsKey("starthiredate") && map.containsKey("endhiredate"))
			predicates.add(builder.between(root.get("hiredate"), Date.valueOf(map.get("starthiredate")), Date.valueOf(map.get("endhiredate"))));

		if (map.containsKey("startsal") && map.containsKey("endsal"))
			predicates.add(builder.between(root.get("sal"), new BigDecimal(map.get("startsal")), new BigDecimal(map.get("endsal"))));

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("ename".equals(row.getKey())) {
				predicates.add(builder.like(root.get("ename"), "%" + row.getValue() + "%"));
			}

			if ("job".equals(row.getKey())) {
				predicates.add(builder.equal(root.get("job"), row.getValue()));
			}

			if ("starthiredate".equals(row.getKey())) {
				if (!map.containsKey("endhiredate"))
					predicates.add(builder.greaterThanOrEqualTo(root.get("hiredate"), Date.valueOf(row.getValue())));
			}

			if ("endhiredate".equals(row.getKey())) {
				if (!map.containsKey("starthiredate"))
					predicates.add(builder.lessThanOrEqualTo(root.get("hiredate"), Date.valueOf(row.getValue())));

			}

			if ("startsal".equals(row.getKey())) {
				if (!map.containsKey("endsal"))
					predicates.add(builder.greaterThanOrEqualTo(root.get("sal"), new BigDecimal(row.getValue())));

			}

			if ("endsal".equals(row.getKey())) {
				if (!map.containsKey("startsal"))
					predicates.add(builder.lessThanOrEqualTo(root.get("sal"), new BigDecimal(row.getValue())));

			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("empno")));
		TypedQuery<Advertisement> query = getSession().createQuery(criteria);

		return query.getResultList();
	}

	@Override
	public List<Advertisement> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from Advertisement", Advertisement.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from Advertisement", Long.class).uniqueResult();
	}

	@Override
	public void addAdvertisement(Advertisement advertisement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DinerInfo getDinerInfoByDinerID(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> getSubmitted(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> getActive(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> getDeactivated(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Advertisement> getAdvertisementsByDinerID(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
