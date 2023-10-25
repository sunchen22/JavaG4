package com.dinerinfo.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.businesshours.entity.BusinessHours;
import com.dinerinfo.dao.DinerInfoDAO;
import com.dinerinfo.dao.DinerInfoDAOHibernateImpl;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

public class DinerInfoServiceImpl implements DinerInfoService {
	// 一個 service 實體對應一個 dao 實體
	private DinerInfoDAO dao;

	public DinerInfoServiceImpl() {
		dao = new DinerInfoDAOHibernateImpl(HibernateUtil.getSessionFactory());
	}

	public DinerInfoDAO getDao() {
		return dao;
	}

	public void setDao(DinerInfoDAO dao) {
		this.dao = dao;
	}

	@Override
	public Integer addDinerInfo(DinerInfo dinerInfo) {
		return dao.add(dinerInfo);
	}

	@Override
	public DinerInfo updateDinerInfo(DinerInfo dinerInfo) {
		return dao.update(dinerInfo);
	}

	// 這個方法是註冊的時候用來分辨哪些是可以註冊、哪些是要記錄到session讓註冊者重填資料比較不麻煩的
	// 傳入的 dinerInfo 是註冊者輸入的資訊
	// 回傳的 dinerInfo 是要記錄到下一步，即將真正可以註冊的使用者容器(newDinerInfo)裡
	@Override
	public DinerInfo registerCheckDinerInfo(DinerInfo dinerInfo) {
		String dinerTaxID = dinerInfo.getDinerTaxID();
		String dinerPhone = dinerInfo.getDinerPhone();
		String dinerEmail = dinerInfo.getDinerEmail();
		if (dinerTaxID.equals(dao.isExistTaxID(dinerTaxID)) || dinerPhone.equals(dao.isExistPhone(dinerPhone))
				|| dinerEmail.equals(dao.isExistEmail(dinerEmail))) {
			return null;
		} else {
			return dinerInfo;
		}
	}

	@Override
	public DinerInfo registerDinerInfo(DinerInfo dinerInfo) {
		dao.register(dinerInfo);
		return dinerInfo;
	}

	@Override
	public Integer deleteDinerID(Integer dinerID) {
		return dao.delete(dinerID);
	}

	@Override
	public DinerInfo getDinerInfoByDinerID(Integer dinerID) {
		return dao.findByPK(dinerID);
	}

	@Override
	public DinerInfo getDinerInfoByDinerTaxID(String dinerTaxID) {
		return dao.findByTaxID(dinerTaxID);
	}

	@Override
	public DinerInfo getDinerInfoByDinerPhone(String dinerPhone) {
		return dao.findByPhone(dinerPhone);
	}

	@Override
	public DinerInfo getDinerInfoByDinerEmail(String dinerEmail) {
		return dao.findByEmail(dinerEmail);
	}

	// ****************此區專門給商家修改使用*********************************************

	@Override
	public String checkDinerTaxID(String dinerTaxID, Integer dinerID) {		
		return dao.isExistTaxID(dinerTaxID, dinerID);
	}

	@Override
	public String checkDinerPhone(String dinerPhone, Integer dinerID) {
		return dao.isExistPhone(dinerPhone, dinerID);
	}

	@Override
	public String checkDinerEmail(String dinerEmail, Integer dinerID) {
		return dao.isExistEmail(dinerEmail, dinerID);
	}

	@Override
	public DinerInfo checkEditDinerInfo(DinerInfo oldInfo, DinerInfo editInfo) {
		int dinerID = oldInfo.getDinerID();
		String dinerTaxID = editInfo.getDinerTaxID();
		String dinerPhone = editInfo.getDinerPhone();
		String dinerEmail = editInfo.getDinerEmail();
		if (dinerTaxID.equals(dao.isExistTaxID(dinerTaxID, dinerID))
				|| dinerPhone.equals(dao.isExistPhone(dinerPhone, dinerID))
				|| dinerEmail.equals(dao.isExistEmail(dinerEmail, dinerID))) {
			return null;
		} else {
			return editInfo;
		}
	}

	@Override
	public DinerInfo compareDinerInfo(DinerInfo oldInfo, DinerInfo newInfo) {
		String change = dao.compare(oldInfo, newInfo);
		System.out.println(change);
		// 要確認真的有修改，才去改商家狀態
		if (change != null) {
			oldInfo.setDinerUpdate(change);
			oldInfo.setDinerStatus("changed");
		}			
		return oldInfo;
	}

	// ****************************************************************************
	
	
	// 用來設定成團訂單金額的方法
	@Override
	public DinerInfo setDinerOrderThreshold(Integer dinerID, String dinerOrderThreshold) {
		DinerInfo dinerinfo = dao.findByPK(dinerID);
		int dot = Integer.parseInt(dinerOrderThreshold);
		dinerinfo.setDinerOrderThreshold(dot);
		return dinerinfo;
	}
	
	// 用來讀取圖片的方法
	@Override
	public byte[] getDinerBlob(Integer dinerID) {
		DinerInfo dinerInfo = dao.findByPK(dinerID);
		if (dinerInfo != null) {
			byte[] dinerBlob = dinerInfo.getDinerBlob();
			return dinerBlob;
		} else {
			return null;
		}
	}
		
	// 用來新增圖片的方法
	@Override
	public DinerInfo setDinerBlob(byte[] dinerBlob , Integer dinerID) {
		DinerInfo dinerInfo = dao.findByPK(dinerID);
		dinerInfo.setDinerBlob(dinerBlob);
		return dinerInfo;
	}
	
	// 用來查廣告
	@Override
	public Set<BusinessHours> getBusinessHoursByDinerID(DinerInfo dinerInfo) {
		int dinerID = dinerInfo.getDinerID();
		return dao.getBusinessHoursByDinerID(dinerID);
	}

	@Override
	public List<DinerInfo> getAllDinerInfos(int currentPage) {
		return dao.getAll();
	}



	@Override
	public int getPageTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DinerInfo> getDinerInfosByCompositeQuery(Map<String, String[]> map) {

		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();

		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0];
			if (value.isEmpty() || value == null) {
				continue;
			}
			query.put(key, value);
		}

		System.out.println(query);

		return dao.getByCompositeQuery(query);
	}

}
