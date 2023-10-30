package com.grouporder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.grouporder.entity.GroupOrderVO;
import com.product.entity.ProductVO;

public class GroupOrderDAON implements GroupOrderDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_STMT = "SELECT * from grouporder where orderStatus=? ";
	private static final String UPDATE_STATUS = "UPDATE grouporder set orderStatus=?  where groupOrderID = ? ";
	private static final String GET_ONE_STMT = "SELECT groupOrderID,dinerID,buildingID,orderStatus,groupOrderCreateTime,groupOrderSubmitTime,holderID,groupTotalPrice,deliveredBlob FROM grouporder where groupOrderID = ?";

	@Override
	public List<GroupOrderVO> getAll(Integer orderStatus) {
		List<GroupOrderVO> list = new ArrayList<GroupOrderVO>();

		GroupOrderVO goVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			pstmt.setInt(1, orderStatus);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				goVO = new GroupOrderVO();
				goVO.setGroupOrderID(rs.getInt("groupOrderID"));
				goVO.setDinerID(rs.getInt("dinerID"));
				goVO.setBuildingID(rs.getInt("buildingID"));
				goVO.setOrderStatus(rs.getInt("orderStatus"));
				goVO.setGroupOrderCreateTime(rs.getTimestamp("groupOrderCreateTime"));
				goVO.setGroupOrderSubmitTime(rs.getTimestamp("groupOrderSubmitTime"));
				goVO.setHolderID(rs.getInt("holderID"));
				goVO.setGroupTotalPrice(rs.getInt("groupTotalPrice"));
				goVO.setHolderID(rs.getInt("holderID"));
				goVO.setDeliveredBlob(rs.getBytes("deliveredBlob"));

				list.add(goVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public GroupOrderVO findByPrimaryKey(Integer groupOrderID) {

		GroupOrderVO grouporder = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, groupOrderID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				grouporder = new GroupOrderVO();
				grouporder.setGroupOrderID(rs.getInt("groupOrderID"));
				grouporder.setDinerID(rs.getInt("dinerID"));
				grouporder.setBuildingID(rs.getInt("buildingID"));
				grouporder.setOrderStatus(rs.getInt("orderStatus"));
				grouporder.setGroupOrderCreateTime(rs.getTimestamp("groupOrderCreateTime"));
				grouporder.setGroupOrderSubmitTime(rs.getTimestamp("groupOrderSubmitTime"));
				grouporder.setGroupTotalPrice(rs.getInt("groupTotalPrice"));
				grouporder.setDeliveredBlob(rs.getBytes("deliveredBlob"));

			}

			// Handle any driver errors
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
		return grouporder;
	}

	@Override
	public void gostatus(GroupOrderVO grouporder) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, grouporder.getOrderStatus());
			pstmt.setInt(2, grouporder.getGroupOrderID());

			pstmt.executeUpdate();

			// Handle any driver errors
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
}