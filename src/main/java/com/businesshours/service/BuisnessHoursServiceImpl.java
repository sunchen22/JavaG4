package com.businesshours.service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import com.businesshours.dao.BusinessHoursDAO;
import com.businesshours.dao.BusinessHoursDAOImpl;
import com.businesshours.entity.BusinessHours;
import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

public class BuisnessHoursServiceImpl implements BusinessHoursService {

	private BusinessHoursDAO dao;

	public BuisnessHoursServiceImpl() {
		dao = new BusinessHoursDAOImpl(HibernateUtil.getSessionFactory());
	}

	public BusinessHoursDAO getDao() {
		return dao;
	}

	public void setDAO(BusinessHoursDAO dao) {
		this.dao = dao;
	}

	@Override
	public BusinessHours setBusinessHours(Time openTime, Time closeTime, String dayOfWeek, Integer dinerID) {
		if (dao.isValidBusinessHours(openTime, closeTime) != 1) {
			return null;
		} else {
			BusinessHours bh = new BusinessHours();
			bh.setOpenTime(openTime);
			bh.setCloseTime(closeTime);
			bh.setDayOfWeek(dayOfWeek);

			DinerInfo dinerInfo = dao.findByPKJoinDinerInfo(dinerID);
			bh.setDinerInfo(dinerInfo);

			return bh;
		}
	}

	@Override
	public String getDayOfWeekByDinerID(Integer dinerID) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void setBusinessHours(Integer dinerID, String dayOfWeek, Time openTime, Time closeTime) {
//		
//		DinerInfoServiceImpl dis = new DinerInfoServiceImpl();
//		DinerInfo dinerInfo = dis.getDinerInfoByDinerID(dinerID);
//		
//		String inputDayOfWeek = dao.isExistDayOfWeek(dinerID, dayOfWeek);
//		if (inputDayOfWeek != null) {
//			BusinessHours businessHours = dao.findByPKJoinDinerInfo(dinerID);
//			businessHours.getDinerInfo(dinerInfo);
//			businessHours.setDayOfWeek(dayOfWeek);
//			businessHours.setOpenTime(openTime);
//			businessHours.setCloseTime(closeTime);;			
//		}
//		
//	}

	@Override
	public List<BusinessHours> getBusinessHoursByDinerID(Integer dinerID) {
		return dao.getBusinessHoursByDinerID(dinerID);
	}

//  public List<BusinessHours> getBusinessHoursByDinerInfo(Integer dinerID){
//	 List<BusinessHours> list = dao.getBusinessHoursByDinerInfo(dinerID);
//	 return list;
//}

	@Override
	public BusinessHours setOpenStatus(Integer dinerID, String dayOfWeek, String openStatus) {
		String oldDataDayOfWeek = dao.isExistDayOfWeek(dinerID, dayOfWeek);

		if (oldDataDayOfWeek != null) {
			List<BusinessHours> bhList = dao.getBusinessHoursByDinerID(dinerID);

			// 使用Java Stream API找到對應的BusinessHours物件
			Optional<BusinessHours> matchedBh = bhList.stream().filter(bh -> oldDataDayOfWeek.equals(bh.getDayOfWeek()))
					.findFirst();

			if (matchedBh.isPresent()) {
				BusinessHours bhToUpdate = matchedBh.get();

				// 更新bhToUpdate的屬性，例如：
				bhToUpdate.setOpenStatus(openStatus);

				// 儲存更新到資料庫
				dao.update(bhToUpdate);
				return bhToUpdate;
			} else {
				return null; // 或是你可以拋出一個異常，表示沒有找到對應的BusinessHours物件
			}
		} else {
			BusinessHours bh = new BusinessHours();
			DinerInfo dinerInfo = bh.getDinerInfo();
			bh.setDinerInfo(dinerInfo);
			bh.setOpenStatus(openStatus);
			bh.setDayOfWeek(dayOfWeek);
			dao.add(bh);
			return bh;
		}
	}

}