package com.dinerinfo.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.dinerinfo.entity.DinerInfo;

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
	public DinerInfo register(String dinerName, String dinerPassword, Timestamp dinerRegisterTime, String dinerTaxID,
			String dinerContact, String dinerPhone, String dinerEmail, String dinerAddress, String dinerBank,
			String dinerAccount, String dinerAccountName, String dinerType, String dinerStatus) {
		

		  DinerInfo dinerInfo = new DinerInfo();

		    dinerInfo.setDinerName(dinerName);
		    dinerInfo.setDinerPassword(dinerPassword);
		    dinerInfo.setDinerRegisterTime(dinerRegisterTime);
		    dinerInfo.setDinerTaxID(dinerTaxID);
		    dinerInfo.setDinerContact(dinerContact);
		    dinerInfo.setDinerPhone(dinerPhone);
		    dinerInfo.setDinerEmail(dinerEmail);
		    dinerInfo.setDinerAddress(dinerAddress);
		    dinerInfo.setDinerBank(dinerBank);
		    dinerInfo.setDinerAccount(dinerAccount);
		    dinerInfo.setDinerAccountName(dinerAccountName);
		    dinerInfo.setDinerType(dinerType);
		    dinerInfo.setDinerStatus(dinerStatus);

		    Session session = getSession();
		    org.hibernate.Transaction tx = null;
		    try {
		        tx = session.beginTransaction();  // 開始事務
		        session.save(dinerInfo);
		        tx.commit();  // 提交事務
		    } catch (Exception e) {
		        if (tx != null) {
		            tx.rollback();  // 如果出現錯誤，則回滾事務
		        }
		        throw e;  // 或者選擇其他的錯誤處理方式
		    }
		    
		    return dinerInfo;

	}

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
		String hql = "FROM DinerInfo WHERE dinerTaxID  = :dinerTaxID";
		Query<DinerInfo> query = getSession().createQuery(hql, DinerInfo.class);
		query.setParameter("dinerTaxID", dinerTaxID);
		DinerInfo dinerInfo = query.uniqueResult(); // 因為dinerTaxID應該是唯一的，所以使用uniqueResult()取得單一結果
		return dinerInfo; // 成功的話，回傳 dinerInfo 物件
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