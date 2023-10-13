package com.dinerinfo.dao;

import java.util.List;

import com.dinerinfo.entity.DinerInfo;

public interface DinerInfoDAOC {
	DinerInfo findByPK(Integer dinerID);
	List<DinerInfo> getAll();

}
