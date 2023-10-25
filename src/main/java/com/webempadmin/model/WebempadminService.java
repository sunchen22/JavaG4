package com.webempadmin.model;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


public class WebempadminService {

	private WebempadminDAO_interface dao;

	public WebempadminService() {

		dao = new WebempadminDAO();
	}

	public WebempadminVO addEmp(String empName, String empPassword, java.sql.Date empArriveDate,
			String empAdminAuthorization, byte[] empBlob) {

		WebempadminVO empVO = new WebempadminVO();

		empVO.setEmpName(empName);
		empVO.setEmpPassword(empPassword);
		empVO.setEmpArriveDate(empArriveDate);
		empVO.setEmpAdminAuthorization(empAdminAuthorization);
		empVO.setEmpBlob(empBlob);
		dao.insert(empVO);

		return empVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addEmp(WebempadminVO empVO) {
		dao.insert(empVO);
	}
	
	public WebempadminVO updateEmp(Integer empID, String empName, String empPassword,java.sql.Date empArriveDate,
			 String empAdminAuthorization, byte[] empBlob) {

		WebempadminVO empVO = new WebempadminVO();

		empVO.setEmpID(empID);
		empVO.setEmpName(empName);
		empVO.setEmpPassword(empPassword);
		empVO.setEmpArriveDate(empArriveDate);
		empVO.setEmpAdminAuthorization(empAdminAuthorization);
		empVO.setEmpBlob(empBlob);
		dao.update(empVO);

		return dao.findByPrimaryKey(empID);
	}
	
	//預留給 Struts 2 用的
	public void updateEmp(WebempadminVO empVO) {
		dao.update(empVO);
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}
	
	//停權
	public void suspendEmp(Integer empno) {
		dao.suspend(empno);
	}

	public WebempadminVO getOneEmp(Integer empID) {
		return dao.findByPrimaryKey(empID);
	}

	public Map<String,String> getOnePassword(String empAccound) {
		return dao.findAccoundPassword(empAccound);
	}
	
	public Map<String,Integer> getOneStatus(String empAccound) {
		return dao.findEmpStatus(empAccound);
	}
	
	public List<WebempadminVO> getAll() {
		return dao.getAll();
	}
}
