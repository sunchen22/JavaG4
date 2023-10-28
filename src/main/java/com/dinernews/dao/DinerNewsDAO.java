package com.dinernews.dao;

import java.util.List;

import com.dinernews.entity.DinerNews;

public interface DinerNewsDAO {
	int add(DinerNews dns);
	int update(DinerNews dns);
	int down(Integer dinerNewsID);
	List<DinerNews> getAll();
}
