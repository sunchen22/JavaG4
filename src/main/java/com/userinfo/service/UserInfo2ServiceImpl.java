package com.userinfo.service;

import static util.Constants.PAGE_MAX_RESULT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import com.dinerinfo.entity.DinerInfo;
import com.userinfo.dao.UserInfo2DAO;
import com.userinfo.dao.UserInfo2DAOImpl;

import com.userinfo.entity.UserInfo;
import util.HibernateUtil;

// 搭配 JSP / Thymeleaf 後端渲染畫面，將交易動作至於 view filter
public class UserInfo2ServiceImpl implements UserInfo2Service {
	// 一個 service 實體對應一個 dao 實體
	private UserInfo2DAO dao;
	
	public UserInfo2ServiceImpl() {
		dao = new UserInfo2DAOImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public void updateUserInfo(UserInfo userInfo) {
		dao.update(userInfo);
	}
	
	@Override
	public void deleteUserInfo(Integer empno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserInfo getUserInfoByuserID(Integer userID) {
		// TODO Auto-generated method stub
//		return null;
		return dao.getById(userID);
	}

	public List<UserInfo> getAllUserInfo() {
		return dao.getAll();
	}
	
	@Override
	public List<UserInfo> getAllUserInfo(int currentPage) {
		return dao.getAll(currentPage);
	}
	
	@Override
	public byte[] getImage(int userID){
		UserInfo userinfo = dao.getById(userID);
		System.out.println("dao.getById(userID) :   "+ dao.getById(userID));
		if(userinfo != null) {
			byte[] userimg = userinfo.getUserBlob();
			System.out.println("userimg " +userimg);
			return userimg;
		}else {
			System.out.println("取照片失敗 ");
			return null;
		}
	}

	@Override //複合查詢
	public List<UserInfo> getUserInfoByCompositeQuery(Map<String, String[]> map) {
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作 //檢查value有沒有資料
			String value = row.getValue()[0]; //getValue拿到一個String陣列,接著[0]取得第一個元素檢查
			if (value == null || value.isEmpty()) { //比較好的寫法，先確認此值有沒有位置
				continue;
			}
			query.put(key, value); //有資料就交給map
		}
		return dao.getByCompositeQuery(query); //回傳List
	}

	@Override
	public int getPageTotal() { //分頁邏輯
		long total = dao.getTotal();
		int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
		return pageQty;
	}

}
