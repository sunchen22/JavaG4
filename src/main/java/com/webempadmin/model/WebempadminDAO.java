package com.webempadmin.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.sql.*;

public class WebempadminDAO implements WebempadminDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/jo?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "12345678";

	private static final String INSERT_STMT = 
		"INSERT INTO webempadmin (empName,empPassword,empArriveDate,empAdminAuthorization,empBlob) VALUES ( ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT empID,empName,empPassword,empArriveDate,empAdminAuthorization,empBlob FROM webempadmin WHERE empStatus=0 order by empID";
	private static final String GET_ONE_STMT = 
		"SELECT empID,empName,empPassword,empArriveDate,empAdminAuthorization,empBlob FROM webempadmin where empID = ?";
	private static final String DELETE = 
		"DELETE FROM webempadmin where empID = ?";
	private static final String SUSPEND = 
		"UPDATE webempadmin set empStatus=?  where empID = ?";
	private static final String UPDATE = 
		"UPDATE webempadmin set empName=?, empPassword=?, empArriveDate=?, empAdminAuthorization=? ,empBlob=? where empID = ?";
	private static final String GET_EMP_STMT = 
		"SELECT empName,empPassword FROM webempadmin where empName = ?";
	private static final String GET_EMPID_STMT = 
		"SELECT empID FROM webempadmin where empName = ?";
	private static final String GET_EMPSTATUS_STMT = 
		"SELECT empName,empStatus FROM webempadmin where empName = ?";

	@Override
	public void insert(WebempadminVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, empVO.getEmpID());
			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpPassword());
			pstmt.setDate(3, empVO.getEmpArriveDate());
			pstmt.setString(4, empVO.getEmpAdminAuthorization());
			pstmt.setBytes(5, empVO.getEmpBlob());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (ClassNotFoundException e) {
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

	
	public void update(WebempadminVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(6, empVO.getEmpID());
			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpPassword());
			pstmt.setDate(3, empVO.getEmpArriveDate());
			pstmt.setString(4, empVO.getEmpAdminAuthorization());
			pstmt.setBytes(5, empVO.getEmpBlob());

			pstmt.executeUpdate();

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

//	@Override
	public void delete(Integer empID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empID);

			pstmt.executeUpdate();

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
	
	@Override //停權:改變status狀態
	public void suspend(Integer empID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SUSPEND );
			
			pstmt.setInt(1, 1);
			pstmt.setInt(2, empID);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public WebempadminVO findByPrimaryKey(Integer empID) {

		WebempadminVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, empID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new WebempadminVO();
				empVO.setEmpID(rs.getInt("empID"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpPassword(rs.getString("empPassword"));
				empVO.setEmpArriveDate(rs.getDate("empArriveDate"));
				empVO.setEmpAdminAuthorization(rs.getString("empAdminAuthorization"));
				empVO.setEmpBlob(rs.getBytes("empBlob"));
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
		return empVO;
	}
	
	@Override
	public Map<String,String> findAccoundPassword(String empName) {
		WebempadminVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> emp = new HashMap<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_EMP_STMT);
			pstmt.setString(1, empName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new WebempadminVO();
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpPassword(rs.getString("empPassword"));
				emp.put(empVO.getEmpName(), empVO.getEmpPassword());
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
		return emp;
	}
	
	
	@Override
	public int findEmpID(String empName) {
		WebempadminVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer empID = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_EMPID_STMT);
			pstmt.setString(1, empName);
			rs = pstmt.executeQuery();
			
			  while (rs.next()) {
			        empVO = new WebempadminVO();
			        empID = rs.getInt("empID"); 
			        empVO.setEmpID(empID);
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
		return empID;
	}
	@Override
	public Map<String,Integer> findEmpStatus(String empName) {
		WebempadminVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Integer> emp = new HashMap<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_EMPSTATUS_STMT);
			pstmt.setString(1, empName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new WebempadminVO();
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpStatus(rs.getInt("empStatus"));
				emp.put(empVO.getEmpName(), empVO.getEmpStatus());
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
		return emp;
	}

	@Override
	public List<WebempadminVO> getAll() {
		List<WebempadminVO> list = new ArrayList<WebempadminVO>();
		WebempadminVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				empVO = new WebempadminVO();
				empVO.setEmpID(rs.getInt("empID"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpPassword(rs.getString("empPassword"));
				empVO.setEmpArriveDate(rs.getDate("empArriveDate"));
				empVO.setEmpAdminAuthorization(rs.getString("empAdminAuthorization"));
				empVO.setEmpBlob(rs.getBytes("empBlob"));
				list.add(empVO); // Store the row in the list
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
	
	public static InputStream getPictureStream(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return fis;
	}

	public static void readPicture(Blob blob) throws IOException, SQLException {  //�ǤJ���쪺blob����
		InputStream is = blob.getBinaryStream();
		FileOutputStream fos = new FileOutputStream("Output/1.png"); //�⮳�쪺��ƿ�X �s�Joutput��Ƨ�
		int i; 
		while ((i = is.read()) != -1) {
			fos.write(i);
			fos.flush();
		}
		fos.close();
		is.close();
	}

	public static void readPicture(InputStream is) throws IOException {
		FileOutputStream fos = new FileOutputStream("C:/Users/jumbo/Desktop/�M�D����/1.png");
		int i;
		while ((i = is.read()) != -1) {
			fos.write(i);
			fos.flush();
		}
		fos.close();
		is.close();
	}
		

}