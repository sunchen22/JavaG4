package com.varytype.dao;

import java.util.List;

import com.varytype.entity.VaryType;
import com.varytype.service.VaryTypeDAO_interface;



public class VaryTypeService {
	
	private VaryTypeDAO_interface dao;

	public VaryTypeService() {

		dao = new VaryTypeDAO();
	}

	public VaryType addVaryType(String varyType) {

		VaryType VT = new VaryType();

		VT.setVaryType(varyType);

		dao.insert(VT);

		return VT;
	}

	// 預留給 Struts 2 或 Spring MVC 用
	public void addEmp(VaryType varyType) {
		dao.insert(varyType);
	}

	public VaryType updatevaryType(Integer varyTypeID, String varyType) {

		VaryType VT = new VaryType();

		VT.setVaryTypeID(varyTypeID);
		VT.setVaryType(varyType);
;
		dao.update(VT);

		return dao.findByPrimaryKey(varyTypeID);
	}

	// 預留給 Struts 2 用的
	public void updateEmp(VaryType VT) {
		dao.update(VT);
	}

	public void deleteEmp(Integer varyTypeID) {
		dao.delete(varyTypeID);
	}

	public VaryType getOneEmp(Integer varyTypeID) {
		return dao.findByPrimaryKey(varyTypeID);
	}

	public List<VaryType> getAll() {
		return dao.getAll();
	}

}
