package com.product.dao;

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

import com.product.entity.ProductVO;

public class ProductDAO implements ProductDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO product (dinerID,productName,productPrice,productTypeID,productDailyStock,productRemark,productBlob1,productBlob2,productBlob3) VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT productID,dinerID,productName,productPrice,productTypeID,productDailyStock,productReleaseTime,productRemark,productStatus,productBlob1,productBlob2,productBlob3 FROM product ";
	private static final String GET_ONE_STMT = "SELECT productID,dinerID,productName,productPrice,productTypeID,productDailyStock,productReleaseTime,productRemark,productStatus,productBlob1,productBlob2,productBlob3 FROM product where productID = ?";
	private static final String GET_ByDID_STMT = "SELECT productID,dinerID,productName,productPrice,productTypeID,productDailyStock,productReleaseTime,productRemark,productStatus,productBlob1,productBlob2,productBlob3 FROM product where dinerID = ?";
	private static final String DELETE = "DELETE FROM product where productID = ?";
	private static final String UPDATE = "UPDATE product set dinerID=?, productName=?, productPrice=?, productTypeID=?, productDailyStock=?, productRemark=?, productBlob1=?, productBlob2=?, productBlob3=?  where productID = ?";
	private static final String OFF_SHELVE = "UPDATE product set productStatus=?  where productID = ?";

	@Override
	public void insert(ProductVO product) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, product.getDinerID());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getProductPrice());
			pstmt.setInt(4, product.getProductTypeID());
			pstmt.setInt(5, product.getProductDailyStock());
			pstmt.setString(6, product.getProductRemark());
			pstmt.setBytes(7, product.getProductBlob1());
			pstmt.setBytes(8, product.getProductBlob2());
			pstmt.setBytes(9, product.getProductBlob3());

			pstmt.executeUpdate();

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
	public void update(ProductVO product) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, product.getDinerID());
			pstmt.setString(2, product.getProductName());
			pstmt.setInt(3, product.getProductPrice());
			pstmt.setInt(4, product.getProductTypeID());
			pstmt.setInt(5, product.getProductDailyStock());
			pstmt.setString(6, product.getProductRemark());
			pstmt.setBytes(7, product.getProductBlob1());
			pstmt.setBytes(8, product.getProductBlob2());
			pstmt.setBytes(9, product.getProductBlob3());
			pstmt.setInt(10, product.getProductID());
			


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

	@Override
	public void delete(Integer productID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, productID);

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

	@Override
	public ProductVO findByPrimaryKeyType(Integer productTypeID) {

		ProductVO product = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, productTypeID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				product = new ProductVO();
				product.setProductID(rs.getInt("productID"));
				product.setDinerID(rs.getInt("dinerID"));
				product.setProductName(rs.getString("productName"));
				product.setProductPrice(rs.getInt("productPrice"));
				product.setProductTypeID(rs.getInt("productTypeID"));
				product.setProductDailyStock(rs.getInt("productDailyStock"));
				product.setProductReleaseTime(rs.getTimestamp("productReleaseTime"));
				product.setProductRemark(rs.getString("productRemark"));
				product.setProductStatus(rs.getString("productStatus"));
				product.setProductBlob1(rs.getBytes("productBlob1"));
				product.setProductBlob2(rs.getBytes("productBlob2"));
				product.setProductBlob3(rs.getBytes("productBlob3"));
			

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
		return product;
	}

	@Override
	public ProductVO findByPrimaryKey(Integer productID) {

		ProductVO product = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, productID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				product = new ProductVO();
				product.setProductID(rs.getInt("productID"));
				product.setDinerID(rs.getInt("dinerID"));
				product.setProductName(rs.getString("productName"));
				product.setProductPrice(rs.getInt("productPrice"));
				product.setProductTypeID(rs.getInt("productTypeID"));
				product.setProductDailyStock(rs.getInt("productDailyStock"));
				product.setProductReleaseTime(rs.getTimestamp("productReleaseTime"));
				product.setProductRemark(rs.getString("productRemark"));
				product.setProductStatus(rs.getString("productStatus"));
				product.setProductBlob1(rs.getBytes("productBlob1"));
				product.setProductBlob2(rs.getBytes("productBlob2"));
				product.setProductBlob3(rs.getBytes("productBlob3"));
				

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
		return product;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO product = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
	
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				product = new ProductVO();
				product.setProductID(rs.getInt("productID"));
				product.setDinerID(rs.getInt("dinerID"));
				product.setProductName(rs.getString("productName"));
				product.setProductPrice(rs.getInt("productPrice"));
				product.setProductTypeID(rs.getInt("productTypeID"));
				product.setProductDailyStock(rs.getInt("productDailyStock"));
				product.setProductReleaseTime(rs.getTimestamp("productReleaseTime"));
				product.setProductRemark(rs.getString("productRemark"));
				product.setProductStatus(rs.getString("productStatus"));
				product.setProductBlob1(rs.getBytes("productBlob1"));
				product.setProductBlob2(rs.getBytes("productBlob2"));
				product.setProductBlob3(rs.getBytes("productBlob3"));
				

				list.add(product); // Store the row in the list
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
	public List<ProductVO> getByDID(Integer dinerID) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO product = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ByDID_STMT);
			pstmt.setInt(1, dinerID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				product = new ProductVO();
				product.setProductID(rs.getInt("productID"));
				product.setDinerID(rs.getInt("dinerID"));
				product.setProductName(rs.getString("productName"));
				product.setProductPrice(rs.getInt("productPrice"));
				product.setProductTypeID(rs.getInt("productTypeID"));
				product.setProductDailyStock(rs.getInt("productDailyStock"));
				product.setProductReleaseTime(rs.getTimestamp("productReleaseTime"));
				product.setProductRemark(rs.getString("productRemark"));
				product.setProductStatus(rs.getString("productStatus"));
				product.setProductBlob1(rs.getBytes("productBlob1"));
				product.setProductBlob2(rs.getBytes("productBlob2"));
				product.setProductBlob3(rs.getBytes("productBlob3"));
				

				list.add(product); // Store the row in the list
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
	public void offshelve(ProductVO product) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(OFF_SHELVE);

		
			pstmt.setString(1, product.getProductStatus());	
			pstmt.setInt(2, product.getProductID());

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
