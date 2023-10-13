package com.varytype.dao;

import static util.Constants.PAGE_MAX_RESULT;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.productvary.entity.ProductVary;
import com.varytype.entity.VaryType;

public class VaryTypeDAOImpl implements VaryTypeDAO{
	
	private SessionFactory factory;

	public VaryTypeDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(VaryType entity) {
		// TODO Auto-generated method stub
		return  (Integer) getSession().save(entity);
	}

	@Override
	public int update(VaryType entity) {
		try {
			getSession().update(entity);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public int delete(Integer id) {
		VaryType varytype = getSession().get(VaryType.class, id);
		if (varytype != null) {
			getSession().delete(varytype);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
	}

	@Override
	public VaryType getById(Integer id) {
		return getSession().get(VaryType.class, id);
	}

	@Override
	public List<VaryType> getAll() {
		return getSession().createQuery("from VaryType", VaryType.class).list();

	}

	@Override
	public List<VaryType> getByCompositeQuery(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VaryType> getAll(int currentPage) {
		int first = (currentPage - 1) * PAGE_MAX_RESULT;
		return getSession().createQuery("from VaryType", VaryType.class)
				.setFirstResult(first)
				.setMaxResults(PAGE_MAX_RESULT)
				.list();
	}

	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from VaryType", Long.class).uniqueResult();

	}

}
