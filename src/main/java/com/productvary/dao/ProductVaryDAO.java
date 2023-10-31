package com.productvary.dao;

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

import com.productvary.entity.ProductVary;

public class ProductVaryDAO implements ProductVaryDAO_interface {

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

	private static final String INSERT_STMT = 
		"INSERT INTO productvary (productID,productVaryDes,productVaryPrice,varyTypeID) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT productVaryID,productID,productVaryDes,productVaryPrice,varyTypeID FROM productvary order by productVaryID";
	private static final String GET_BY_TYPE = 
			"SELECT productVaryID,productID,productVaryDes,productVaryPrice,varyTypeID FROM productvary where varyTypeID= ?";
	private static final String GET_ONE_STMT = 
		"SELECT productVaryID,productID,productVaryDes,productVaryPrice,varyTypeID FROM productvary where productVaryID = ?";
	private static final String GET_BY_PID = 
		"SELECT productVaryID,productID,productVaryDes,productVaryPrice,varyTypeID FROM productvary where productID = ? order by varyTypeID";
	private static final String DELETE = 
		"DELETE FROM productvary where productVaryID = ?";
	private static final String UPDATE = 
		"UPDATE productvary set productVaryDes=?, productVaryPrice=?, varyTypeID=? where productVaryID = ?";

	@Override
	public void insert(ProductVary productvary) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productvary.getProductID());
			pstmt.setString(2, productvary.getProductVaryDes());
			pstmt.setInt(3, productvary.getProductVaryPrice());
			pstmt.setInt(4, productvary.getVaryTypeID());
		

			pstmt.executeUpdate();

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
	public void update(ProductVary productvary) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productvary.getProductVaryDes());
			pstmt.setInt(2, productvary.getProductVaryPrice());
			pstmt.setInt(3, productvary.getVaryTypeID());
			pstmt.setInt(4, productvary.getProductVaryID());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer productVaryID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, productVaryID);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ProductVary findByPrimaryKey(Integer productVaryID) {

		ProductVary productVary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, productVaryID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				productVary = new ProductVary();
				productVary.setProductVaryID(rs.getInt("productVaryID"));
				productVary.setProductID(rs.getInt("productID"));
				productVary.setProductVaryDes(rs.getString("productVaryDes"));
				productVary.setProductVaryPrice(rs.getInt("productVaryPrice"));
				productVary.setVaryTypeID(rs.getInt("varyTypeID"));
	
			}

			// Handle any driver errors
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
		return productVary;
	}

	@Override
	public List<ProductVary> getAll() {
		List<ProductVary> list = new ArrayList<ProductVary>();
		ProductVary productVary = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				productVary = new ProductVary();
				productVary.setProductVaryID(rs.getInt("productVaryID"));
				productVary.setProductID(rs.getInt("productID"));
				productVary.setProductVaryDes(rs.getString("productVaryDes"));
				productVary.setProductVaryPrice(rs.getInt("productVaryPrice"));
				productVary.setVaryTypeID(rs.getInt("varyTypeID"));
				list.add(productVary); // Store the row in the list
			}

			// Handle any driver errors
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
	
	@Override
	public List<ProductVary> getByPID(Integer productID) {
		List<ProductVary> list = new ArrayList<ProductVary>();
		
		ProductVary productVary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PID);
			pstmt.setInt(1, productID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				productVary = new ProductVary();
				productVary.setProductVaryID(rs.getInt("productVaryID"));
				productVary.setProductID(rs.getInt("productID"));
				productVary.setProductVaryDes(rs.getString("productVaryDes"));
				productVary.setProductVaryPrice(rs.getInt("productVaryPrice"));
				productVary.setVaryTypeID(rs.getInt("varyTypeID"));
				
				list.add(productVary); // Store the row in the list
			}

			// Handle any driver errors
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
	
	@Override
	public List<ProductVary> getByType(Integer varyTypeID) {
		List<ProductVary> list = new ArrayList<ProductVary>();
		
		ProductVary productVary = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_TYPE);
			pstmt.setInt(1, varyTypeID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				productVary = new ProductVary();
				productVary.setProductVaryID(rs.getInt("productVaryID"));
				productVary.setProductID(rs.getInt("productID"));
				productVary.setProductVaryDes(rs.getString("productVaryDes"));
				productVary.setProductVaryPrice(rs.getInt("productVaryPrice"));
				productVary.setVaryTypeID(rs.getInt("varyTypeID"));
				
				list.add(productVary); // Store the row in the list
			}

			// Handle any driver errors
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
}