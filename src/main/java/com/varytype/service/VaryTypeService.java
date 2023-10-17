package com.varytype.service;

import java.util.List;

import com.varytype.dao.VaryTypeDAO;
import com.varytype.dao.VaryTypeDAO_interface;
import com.varytype.entity.VaryType;

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
	public void addVaryType(VaryType varyType) {
		dao.insert(varyType);
	}

	// 預留給 Struts 2 用的

	public void deleteVaryType(Integer varyTypeID) {
		dao.delete(varyTypeID);
	}

	public VaryType getOneVaryType(Integer varyTypeID) {
		return dao.findByPrimaryKey(varyTypeID);
	}

	public List<VaryType> getAll() {
		return dao.getAll();
	}

}
