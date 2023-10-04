package com.grouporder.dao;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import com.grouporder.entity.GroupOrder;

public class GroupOrderJDBCDAO implements GroupOrderDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jo?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "12345678";

	private static final String INSERT_STMT = 
		"INSERT INTO GroupOrder (dinerID, buildingID, orderStatus, groupOrderCreateTime, groupOrderSubmitTime, holderID) VALUES (?,?,?,?,?,?);";
	private static final String GET_ALL_STMT = 
		"SELECT groupOrderID, dinerID, buildingID, orderStatus, groupOrderCreateTime, groupOrderSubmitTime,holderID, groupTotalPrice,deliveredBlob FROM GroupOrder ORDER BY groupOrderID";
	private static final String GET_ONE_STMT = 
		"SELECT groupOrderID, dinerID, buildingID, orderStatus, groupOrderCreateTime, groupOrderSubmitTime,holderID, groupTotalPrice,deliveredBlob FROM GroupOrder WHERE groupOrderID = ?";
	private static final String DELETE = 
		"DELETE FROM GroupOrder where groupOrderID = ?";
	private static final String UPDATE = 
		"UPDATE GroupOrder SET dinerID=?, buildingID=?, orderStatus=?, groupOrderCreateTime=?, groupOrderSubmitTime=?, holderID=?, groupTotalPrice=? WHERE groupOrderID = ?";

	@Override
	public int add(GroupOrder groupOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, groupOrderVO.getDinerID());
			pstmt.setInt(2, groupOrderVO.getBuildingID());
			pstmt.setString(3, groupOrderVO.getOrderStatus());
			pstmt.setTimestamp(4, groupOrderVO.getGroupOrderCreateTime());
			pstmt.setTimestamp(5, groupOrderVO.getGroupOrderSubmitTime());
			pstmt.setInt(6, groupOrderVO.getHolderID());

			pstmt.executeUpdate();
			return 1;

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public int update(GroupOrder groupOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, groupOrderVO.getDinerID());
			pstmt.setInt(2, groupOrderVO.getBuildingID());
			pstmt.setString(3, groupOrderVO.getOrderStatus());
			pstmt.setTimestamp(4, groupOrderVO.getGroupOrderCreateTime());
			pstmt.setTimestamp(5, groupOrderVO.getGroupOrderSubmitTime());
			pstmt.setInt(6, groupOrderVO.getHolderID());
			pstmt.setInt(7, groupOrderVO.getGroupTotalPrice());			
			pstmt.setInt(8, groupOrderVO.getGroupOrderID());

			pstmt.executeUpdate();
			return 1;

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public int delete(Integer groupOrderID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, groupOrderID);

			pstmt.executeUpdate();
			return 1;

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public GroupOrder findByPK(Integer groupOrderID) {

		GroupOrder groupOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, groupOrderID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				groupOrderVO = new GroupOrder();
				groupOrderVO.setDinerID(rs.getInt("dinerID"));
				groupOrderVO.setBuildingID(rs.getInt("buildingID"));
				groupOrderVO.setOrderStatus(rs.getString("orderStatus"));
				groupOrderVO.setGroupOrderCreateTime(rs.getTimestamp("groupOrderCreateTime"));
				groupOrderVO.setGroupOrderSubmitTime(rs.getTimestamp("groupOrderSubmitTime"));
				groupOrderVO.setHolderID(rs.getInt("holderID"));
				groupOrderVO.setGroupTotalPrice(rs.getInt("groupTotalPrice"));			
				groupOrderVO.setGroupOrderID(rs.getInt("groupOrderID"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return groupOrderVO;
	}

	@Override
	public List<GroupOrder> getAll() {
		List<GroupOrder> list = new ArrayList<GroupOrder>();
		GroupOrder groupOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				groupOrderVO = new GroupOrder();
				groupOrderVO.setGroupOrderID(rs.getInt("groupOrderID"));
				groupOrderVO.setDinerID(rs.getInt("dinerID"));
				groupOrderVO.setBuildingID(rs.getInt("buildingID"));
				groupOrderVO.setOrderStatus(rs.getString("orderStatus"));
				groupOrderVO.setGroupOrderCreateTime(rs.getTimestamp("groupOrderCreateTime"));
				groupOrderVO.setGroupOrderSubmitTime(rs.getTimestamp("groupOrderSubmitTime"));
				groupOrderVO.setHolderID(rs.getInt("holderID"));
				groupOrderVO.setGroupTotalPrice(rs.getInt("groupTotalPrice"));			
				list.add(groupOrderVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public static void main(String[] args) {

		GroupOrderJDBCDAO dao = new GroupOrderJDBCDAO();

		// 新增
//		GroupOrderVO groupOrderVO = new GroupOrderVO();		
//		groupOrderVO.setDinerID(1);
//		groupOrderVO.setBuildingID(1);
//		groupOrderVO.setOrderStatus("1");
//		groupOrderVO.setGroupOrderCreateTime(Timestamp.valueOf(LocalDateTime.now()));
//		GregorianCalendar date = new GregorianCalendar(2023, 8, 12, 12, 0);
//		groupOrderVO.setGroupOrderSubmitTime(new java.sql.Timestamp(date.getTimeInMillis()));
//		groupOrderVO.setHolderID(1);
//		dao.insert(groupOrderVO);

		// 修改
//		GroupOrderVO groupOrderVO2 = new GroupOrderVO();
//		groupOrderVO2.setGroupOrderID(1);
//		groupOrderVO2.setDinerID(2);
//		groupOrderVO2.setBuildingID(1);
//		groupOrderVO2.setOrderStatus("1");
//		groupOrderVO2.setGroupOrderCreateTime(Timestamp.valueOf(LocalDateTime.now()));
//		GregorianCalendar date = new GregorianCalendar(2023, 8, 12, 12, 0);
//		groupOrderVO2.setGroupOrderSubmitTime(new java.sql.Timestamp(date.getTimeInMillis()));
//		groupOrderVO2.setHolderID(1);
//		groupOrderVO2.setGroupTotalPrice(0);
//		dao.update(groupOrderVO2);
//
//		// 刪除
//		dao.delete(5);
//
//		// 查詢
//		GroupOrderVO groupOrderVO3 = dao.findByPrimaryKey(1);
//		System.out.print(groupOrderVO3.getGroupOrderID() + ",");
//		System.out.print(groupOrderVO3.getDinerID() + ",");
//		System.out.print(groupOrderVO3.getBuildingID() + ",");
//		System.out.print(groupOrderVO3.getOrderStatus() + ",");
//		System.out.print(groupOrderVO3.getGroupOrderCreateTime() + ",");
//		System.out.print(groupOrderVO3.getGroupOrderSubmitTime() + ",");
//		System.out.print(groupOrderVO3.getHolderID() + ",");
//		System.out.println(groupOrderVO3.getGroupTotalPrice());
//		System.out.println("---------------------");		

		// 查詢
		List<GroupOrder> list = dao.getAll();
		for (GroupOrder aGroupOrder : list) {
			System.out.print(aGroupOrder.getGroupOrderID() + ",");
			System.out.print(aGroupOrder.getDinerID() + ",");
			System.out.print(aGroupOrder.getBuildingID() + ",");
			System.out.print(aGroupOrder.getOrderStatus() + ",");
			System.out.print(aGroupOrder.getGroupOrderCreateTime() + ",");
			System.out.print(aGroupOrder.getGroupOrderSubmitTime() + ",");
			System.out.print(aGroupOrder.getHolderID() + ",");
			System.out.print(aGroupOrder.getGroupTotalPrice());	
			System.out.println();
		}
	}
}