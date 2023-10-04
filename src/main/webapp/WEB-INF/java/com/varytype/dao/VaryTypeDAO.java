package com.varytype.dao;

import java.util.List;
import java.util.Map;

import com.varytype.entity.VaryType;

public interface VaryTypeDAO {

	int insert(VaryType entity);

	int update(VaryType entity);
	
	int delete(Integer id);
	 
	VaryType getById(Integer id);
	
	List<VaryType> getAll();
	
	List<VaryType> getByCompositeQuery(Map<String, String> map);
	
	List<VaryType> getAll(int currentPage);
	
	long getTotal();
}
