package com.usernews.service;

import java.util.List;

import com.usernews.dao.UserNewsDAO;
import com.usernews.dao.UserNewsDAOImpl;
import com.usernews.entity.UserNews;

import util.HibernateUtil;

// 搭配 JSP / Thymeleaf 後端渲染畫面，將交易動作至於 view filter
public class UserNewsServiceImpl implements UserNewsService {
	// 一個 service 實體對應一個 dao 實體
	private UserNewsDAO dao;
	
	public UserNewsServiceImpl() {
		dao = new UserNewsDAOImpl(HibernateUtil.getSessionFactory());
	}
	

	public UserNews addUserNews(UserNews usernews) {
		dao.insert(usernews);
		return usernews;
	}
	
	@Override
	public void updateUserNews(UserNews usernews) {
		dao.update(usernews);
	}
	
	@Override
	public void deleteUserNews(Integer usernewsID) {
		// TODO Auto-generated method stub
		dao.delete(usernewsID);
	}

	@Override
	public UserNews getUserNewsByID(Integer usernewsID) {
		// TODO Auto-generated method stub
//		return null;
		return dao.getById(usernewsID);
	}

	public List<UserNews> getAllUserNews() {
		return dao.getAll();
	}

	// add by tz
	@Override
	public List<UserNews> getAllUserNewsByStatus() {
		// TODO Auto-generated method stub
		return dao.getAllbyStatus();
	}
	
//	@Override //複合查詢
//	public List<UserInfo> getUserInfoByCompositeQuery(Map<String, String[]> map) {
//		Map<String, String> query = new HashMap<>();
//		// Map.Entry即代表一組key-value
//		Set<Map.Entry<String, String[]>> entry = map.entrySet();
//		
//		for (Map.Entry<String, String[]> row : entry) {
//			String key = row.getKey();
//			// 因為請求參數裡包含了action，做個去除動作
//			if ("action".equals(key)) {
//				continue;
//			}
//			// 若是value為空即代表沒有查詢條件，做個去除動作 //檢查value有沒有資料
//			String value = row.getValue()[0]; //getValue拿到一個String陣列,接著[0]取得第一個元素檢查
//			if (value == null || value.isEmpty()) { //比較好的寫法，先確認此值有沒有位置
//				continue;
//			}
//			query.put(key, value); //有資料就交給map
//		}
//		return dao.getByCompositeQuery(query); //回傳List
//	}

}
