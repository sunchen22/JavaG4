package com.varytype.service;

import java.util.List;

import com.varytype.entity.VaryType;

public interface VaryTypeDAO_interface {

	public void insert(VaryType varyType);

	public void update(VaryType varyType);

	public void delete(Integer varyTypeID);

	public VaryType findByPrimaryKey(Integer varyTypeID);

	public List<VaryType> getAll();
}
