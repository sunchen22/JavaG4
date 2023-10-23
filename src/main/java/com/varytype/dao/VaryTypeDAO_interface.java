package com.varytype.dao;

import java.util.List;

import com.varytype.entity.VaryType;

public interface VaryTypeDAO_interface {

	public void insert(VaryType varyType);

	public void delete(Integer varyTypeID);

	public VaryType findByPrimaryKey(Integer varyTypeID);

	public List<VaryType> getAll();
}
