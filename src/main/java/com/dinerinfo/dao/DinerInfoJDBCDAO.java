package com.dinerinfo.dao;


import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.businesshours.entity.BusinessHours;
import com.dinerinfo.entity.DinerInfo;



public class DinerInfoJDBCDAO implements DinerInfoDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jo?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "apple1220";
    
	// insert暫時不放 第1欄的dinerID 和 第16欄的dinerBlob ，因為一個是自增主鍵、一個是圖片要另外引入 
	private static final String INSERT_STMT = "INSERT INTO dinerinfo (dinerName, dinerPassword, dinerRegisterTime, dinerTaxID, dinerContact, dinerPhone, dinerEmail, dinerAddress, dinerBank, dinerAccount, dinerAccountName, dinerType, dinerStatus, dinerOrderThreshold) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ONE_STMT = "SELECT dinerID, dinerName, dinerPassword, dinerRegisterTime, dinerTaxID, dinerContact, dinerPhone, dinerEmail, dinerAddress, dinerBank, dinerAccount, dinerAccountName, dinerType, dinerStatus, dinerOrderThreshold, dinerBlob FROM dinerinfo WHERE dinerID = ?";
	private static final String GET_ALL_STMT = "SELECT dinerID, dinerName, dinerPassword, dinerRegisterTime, dinerTaxID, dinerContact, dinerPhone, dinerEmail, dinerAddress, dinerBank, dinerAccount, dinerAccountName, dinerType, dinerStatus, dinerOrderThreshold, dinerBlob FROM dinerinfo ORDER BY dinerID";

//	private static final String GET_BusinessHours_ByDinerID_STMT = "SELECT dinerOpenHoursID, dinerID, dayOfWeek, openTime, closeTime, openStatus FROM BusinessHours where dinerID = ? order by dinerOpenHoursID";
	
	private static final String DELETE_DinerInfo = "DELETE FROM dinerinfo where dinerID = ?";

//	private static final String DELETE_BusinessHours = "DELETE FROM BusinessHours where dinerID = ?";
//	private static final String DELETE_Advertisement = "DELETE FROM Advertisement where dinerID = ?";
//	private static final String DELETE_DinerRatingComment = "DELETE FROM DinerRatingComment where dinerID = ?";
//	private static final String DELETE_GroupOrder = "DELETE FROM GroupOrder where dinerID = ?";
//	private static final String DELETE_Product = "DELETE FROM Product where dinerID = ?";
//	private static final String DELETE_DinerForm = "DELETE FROM DinerForm where dinerID = ?";
//	private static final String DELETE_Favorite = "DELETE FROM Favorite where dinerID = ?";
	
	private static final String UPDATE = "UPDATE dinerinfo SET dinerName=?, dinerPassword=?, dinerRegisterTime=?, dinerTaxID=?, dinerContact=?, dinerPhone=?, dinerEmail=?, dinerAddress=?, dinerBank=?, dinerAccount=?, dinerAccountName=?, dinerType=?, dinerStatus=?, dinerOrderThreshold=? WHERE dinerID=?";

	@Override
	public int add(DinerInfo dinerInfo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, dinerInfo.getDinerID());    //自增主鍵
			pstmt.setString(1, dinerInfo.getDinerName());
			pstmt.setString(2, dinerInfo.getDinerPassword());
			pstmt.setTimestamp(3, dinerInfo.getDinerRegisterTime());
			pstmt.setString(4, dinerInfo.getDinerTaxID());
			pstmt.setString(5, dinerInfo.getDinerContact());
			pstmt.setString(6, dinerInfo.getDinerPhone());
			pstmt.setString(7, dinerInfo.getDinerEmail());
			pstmt.setString(8, dinerInfo.getDinerAddress());
			pstmt.setString(9, dinerInfo.getDinerBank());
			pstmt.setString(10, dinerInfo.getDinerAccount());
			pstmt.setString(11, dinerInfo.getDinerAccountName());
			pstmt.setString(12, dinerInfo.getDinerType());
			pstmt.setString(13, dinerInfo.getDinerStatus());
			pstmt.setInt(14, dinerInfo.getDinerOrderThreshold());
//			pstmt.setBytes(16, dinerInfo.getDinerBlob());   //圖片

			pstmt.executeUpdate();
			return 1;
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public int update(DinerInfo dinerInfo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, dinerInfo.getDinerName());
			pstmt.setString(2, dinerInfo.getDinerPassword());
			pstmt.setTimestamp(3, dinerInfo.getDinerRegisterTime());
			pstmt.setString(4, dinerInfo.getDinerTaxID());
			pstmt.setString(5, dinerInfo.getDinerContact());
			pstmt.setString(6, dinerInfo.getDinerPhone());
			pstmt.setString(7, dinerInfo.getDinerEmail());
			pstmt.setString(8, dinerInfo.getDinerAddress());
			pstmt.setString(9, dinerInfo.getDinerBank());
			pstmt.setString(10, dinerInfo.getDinerAccount());
			pstmt.setString(11, dinerInfo.getDinerAccountName());
			pstmt.setString(12, dinerInfo.getDinerType());
			pstmt.setString(13, dinerInfo.getDinerStatus());
			pstmt.setInt(14, dinerInfo.getDinerOrderThreshold());
			//圖片用另外的方式傳入

			pstmt.setInt(15, dinerInfo.getDinerID());
			
			pstmt.executeUpdate();
			return 1;

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public int delete(Integer dinerID) {

//		int updateCount_OpenHours = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1.設定於 pstm.executeUpdate()之前
			// 關閉自動提交（auto-commit），以便在刪除操作完成後手動進行提交或回滾
//			con.setAutoCommit(false);

//			// 刪除營業時間
//			pstmt = con.prepareStatement(DELETE_BusinessHours);
//			pstmt.setInt(1, dinerID);
//			updateCount_OpenHours = pstmt.executeUpdate();
//					
//			// 刪除廣告
////		    public Set<AdvertisementVO> getAdvertisementByDinerID(Integer dinerID);//          查詢某商家的營業時間(一對多)(回傳 Set)
//			pstmt = con.prepareStatement(DELETE_Advertisement);
//			pstmt.setInt(1, dinerID);
//			updateCount_OpenHours = pstmt.executeUpdate();
//			
//			// 刪除商店評分
////		    public Set<DinerRatingCommentVO> getDinerRatingCommentByDinerID(Integer dinerID);
//			pstmt = con.prepareStatement(DELETE_DinerRatingComment);
//			pstmt.setInt(1, dinerID);
//			updateCount_OpenHours = pstmt.executeUpdate();
//			
//			// 刪除團購訂單
////		    public Set<GroupOrderVO> getGroupOrderByDinerID(Integer dinerID);
//			pstmt = con.prepareStatement(DELETE_GroupOrder);
//			pstmt.setInt(1, dinerID);
//			updateCount_OpenHours = pstmt.executeUpdate();
//			
//			// 刪除商品資料
////		    public Set<ProductVO> getProductByDinerID(Integer dinerID);
//			pstmt = con.prepareStatement(DELETE_Product);
//			pstmt.setInt(1, dinerID);
//			updateCount_OpenHours = pstmt.executeUpdate();
//			
//			// 刪除商家問卷留言
////		    public Set<DinerFormVO> getDinerFormByDinerInfoID(Integer dinerID);
//			pstmt = con.prepareStatement(DELETE_DinerForm);
//			pstmt.setInt(1, dinerID);
//			updateCount_OpenHours = pstmt.executeUpdate();
//			
//			// 刪除最愛商家
////		    public Set<FavoriteVO> getFavoriteByDinerInfoID(Integer dinerID);	
//			pstmt = con.prepareStatement(DELETE_Favorite);
//			pstmt.setInt(1, dinerID);
//			updateCount_OpenHours = pstmt.executeUpdate();

			// 最後再刪除商家資訊
			pstmt = con.prepareStatement(DELETE_DinerInfo);
			pstmt.setInt(1, dinerID);
			pstmt.executeUpdate();
			return 1;

			// 2.設定於 pstm.executeUpdate()之後
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("刪除商家編號" + dinerID + "時,共有營業時間" + updateCount_OpenHours + "個同時被刪除");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public DinerInfo findByPK(Integer dinerID) {
		DinerInfo dinerInfo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, dinerID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// dinerInfo 也稱為 Domain objects
//			
				dinerInfo = new DinerInfo();
				dinerInfo.setDinerID(rs.getInt("dinerID"));
				dinerInfo.setDinerName(rs.getString("dinerName"));
				dinerInfo.setDinerPassword(rs.getString("dinerPassword"));
				dinerInfo.setDinerRegisterTime(rs.getTimestamp("dinerRegisterTime"));
				dinerInfo.setDinerTaxID(rs.getString("dinerTaxID"));
				dinerInfo.setDinerContact(rs.getString("dinerContact"));
				dinerInfo.setDinerPhone(rs.getString("dinerPhone"));
				dinerInfo.setDinerEmail(rs.getString("dinerEmail"));
				dinerInfo.setDinerAddress(rs.getString("dinerAddress"));
				dinerInfo.setDinerBank(rs.getString("dinerBank"));
				dinerInfo.setDinerAccount(rs.getString("dinerAccount"));
				dinerInfo.setDinerAccountName(rs.getString("dinerAccountName"));
				dinerInfo.setDinerType(rs.getString("dinerType"));
				dinerInfo.setDinerStatus(rs.getString("dinerStatus"));
				dinerInfo.setDinerOrderThreshold(rs.getInt("dinerOrderThreshold"));
				dinerInfo.setDinerBlob(rs.getBytes("dinerBlob"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return dinerInfo;
	}

	@Override
	public List<DinerInfo> getAll() {
		List<DinerInfo> list = new ArrayList<DinerInfo>();
		DinerInfo dinerInfo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dinerInfo = new DinerInfo();
				dinerInfo.setDinerID(rs.getInt("dinerID"));
				dinerInfo.setDinerName(rs.getString("dinerName"));
				dinerInfo.setDinerPassword(rs.getString("dinerPassword"));
				dinerInfo.setDinerRegisterTime(rs.getTimestamp("dinerRegisterTime"));
				dinerInfo.setDinerTaxID(rs.getString("dinerTaxID"));
				dinerInfo.setDinerContact(rs.getString("dinerContact"));
				dinerInfo.setDinerPhone(rs.getString("dinerPhone"));
				dinerInfo.setDinerEmail(rs.getString("dinerEmail"));
				dinerInfo.setDinerAddress(rs.getString("dinerAddress"));
				dinerInfo.setDinerBank(rs.getString("dinerBank"));
				dinerInfo.setDinerAccount(rs.getString("dinerAccount"));
				dinerInfo.setDinerAccountName(rs.getString("dinerAccountName"));
				dinerInfo.setDinerType(rs.getString("dinerType"));
				dinerInfo.setDinerStatus(rs.getString("dinerStatus"));
				dinerInfo.setDinerOrderThreshold(rs.getInt("dinerOrderThreshold"));
				dinerInfo.setDinerBlob(rs.getBytes("dinerBlob"));
				list.add(dinerInfo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
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
	
	//查詢某商家的營業時間(一對多)(回傳 Set)	
//	@Override
//	public Set<BusinessHours> getBusinessHoursByDinerID(Integer dinerID) {
//		Set<BusinessHours> set = new LinkedHashSet<BusinessHours>();
//		BusinessHours businessHoursVO = null;
//	
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//	
//		try {
//	
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_BusinessHours_ByDinerID_STMT);
//			pstmt.setInt(1, dinerID);
//			rs = pstmt.executeQuery();
//	
//			while (rs.next()) {
//				businessHoursVO = new BusinessHours();
//				businessHoursVO.setDinerOpenHoursID(rs.getInt("dinerOpenHoursID"));
//				businessHoursVO.setDinerID(rs.getInt("dinerID"));
//				businessHoursVO.setDayOfWeek(rs.getString("dayOfWeek"));
//				businessHoursVO.setOpenTime(rs.getTime("openTime"));
//				businessHoursVO.setCloseTime(rs.getTime("closeTime"));
//				businessHoursVO.setOpenStatus(rs.getString("openStatus"));
//				
//				set.add(businessHoursVO); // Store the row in the list
//			}
//	
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return set;
//	}




	public static void main(String[] args) {
//		
		DinerInfoJDBCDAO dao = new DinerInfoJDBCDAO();
		
//		// 新增
//		DinerInfo dinerInfo1 = new DinerInfo();
//		//ID自動生成，不做新增設定
//		
//		//Timestamp的當前時間設置
//		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//		
//		dinerInfo1.setDinerName("測試新增店名1");
//		dinerInfo1.setDinerPassword("123Pssw0rd1456");
//		dinerInfo1.setDinerRegisterTime(currentTime);
//		dinerInfo1.setDinerTaxID("87654321");
//		dinerInfo1.setDinerContact("莊富");
//		dinerInfo1.setDinerPhone("0912222457");
//		dinerInfo1.setDinerEmail("nscafe1478@gmail.com");
//		dinerInfo1.setDinerAddress("台北市羅斯福路三段124巷7樓9號");
//		dinerInfo1.setDinerBank("001");
//		dinerInfo1.setDinerAccount("1234567890123556");
//		dinerInfo1.setDinerAccountName("莊富");
//		dinerInfo1.setDinerType("M");
//		dinerInfo1.setDinerStatus("Active");
//		dinerInfo1.setDinerOrderThreshold(2000);
//		//照片傳入用另外的方式
//		dao.add(dinerInfo1);

		
		// 修改
//		時間轉換器   String → sql.Timestamp
//		String dateTimeStr2 = "2023-08-29 20:25:58";
//		Timestamp ts = Timestamp.valueOf(dateTimeStr2);
//		
//	    DinerInfo dinerInfo2 = new DinerInfo();
//		
//		dinerInfo2.setDinerName("測試修改2");
//		dinerInfo2.setDinerPassword("123Pssw0rd1456");
//		dinerInfo2.setDinerRegisterTime(ts);
//		dinerInfo2.setDinerTaxID("12547787");
//		dinerInfo2.setDinerContact("莊二富");
//		dinerInfo2.setDinerPhone("0912521478");
//		dinerInfo2.setDinerEmail("nscaf8e8478@gmail.com");
//		dinerInfo2.setDinerAddress("台北市羅斯福路三段134巷7樓9號");
//		dinerInfo2.setDinerBank("003");
//		dinerInfo2.setDinerAccount("3214567890123551");
//		dinerInfo2.setDinerAccountName("莊二富");
//		dinerInfo2.setDinerType("X");
//		dinerInfo2.setDinerStatus("Deactive");
//		dinerInfo2.setDinerOrderThreshold(3000);		
////      //照片傳入用另外的方式
//		dinerInfo2.setDinerID(9);	
//		
//		dao.update(dinerInfo2);
		
		
		
		
//		// 刪除
//		dao.delete(9);
//
//		// 查詢
//		DinerInfo dinerInfo3 = dao.findByPrimaryKey(4);
//		//ID自動生成，不做新增設定	
//		System.out.print(dinerInfo3.getDinerName() + ",");
//		System.out.print(dinerInfo3.getDinerPassword() + ",");
//		System.out.print(dinerInfo3.getDinerRegisterTime() + ",");
//		System.out.print(dinerInfo3.getDinerTaxID() + ",");
//		System.out.print(dinerInfo3.getDinerContact() + ",");
//		System.out.print(dinerInfo3.getDinerPhone() + ",");
//		System.out.print(dinerInfo3.getDinerEmail() + ",");
//		System.out.print(dinerInfo3.getDinerAddress() + ",");
//		System.out.print(dinerInfo3.getDinerBank() + ",");
//		System.out.print(dinerInfo3.getDinerAccount() + ",");
//		System.out.print(dinerInfo3.getDinerAccountName() + ",");
//		System.out.print(dinerInfo3.getDinerType() + ",");
//		System.out.print(dinerInfo3.getDinerStatus() + ",");
//		System.out.print(dinerInfo3.getDinerOrderThreshold() + ",");
//		System.out.println(dinerInfo3.getDinerBlob());
//		System.out.println("---------------------");
//
		// 查詢所有商家
		List<DinerInfo> list = dao.getAll();
		for (DinerInfo aDinerInfo : list) {
			System.out.print(aDinerInfo.getDinerName() + ",");
			System.out.print(aDinerInfo.getDinerPassword() + ",");
			System.out.print(aDinerInfo.getDinerRegisterTime() + ",");
			System.out.print(aDinerInfo.getDinerTaxID() + ",");
			System.out.print(aDinerInfo.getDinerContact() + ",");
			System.out.print(aDinerInfo.getDinerPhone() + ",");
			System.out.print(aDinerInfo.getDinerEmail() + ",");
			System.out.print(aDinerInfo.getDinerAddress() + ",");
			System.out.print(aDinerInfo.getDinerBank() + ",");
			System.out.print(aDinerInfo.getDinerAccount() + ",");
			System.out.print(aDinerInfo.getDinerAccountName() + ",");
			System.out.print(aDinerInfo.getDinerType() + ",");
			System.out.print(aDinerInfo.getDinerStatus() + ",");
			System.out.print(aDinerInfo.getDinerOrderThreshold() + ",");
			System.out.println(aDinerInfo.getDinerBlob());
			System.out.println();
		}
		
//		// 查詢某商家的營業時間
//		Set<BusinessHours> set = dao.getBusinessHoursByDinerID(2);
//		for (BusinessHours aBusinessHours : set) {
//			System.out.print(aBusinessHours.getDinerOpenHoursID() + ",");
//			System.out.print(aBusinessHours.getDinerID() + ",");
//			System.out.print(aBusinessHours.getDayOfWeek() + ",");
//			System.out.print(aBusinessHours.getOpenTime() + ",");
//			System.out.print(aBusinessHours.getCloseTime() + ",");
//			System.out.print(aBusinessHours.getOpenStatus() + ",");
//			
//			System.out.println();
//		}
		
	}
}
