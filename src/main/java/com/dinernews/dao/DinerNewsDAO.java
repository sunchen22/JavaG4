package com.dinernews.dao;

import java.util.List;

import com.dinernews.entity.DinerNews;

public interface DinerNewsDAO {
	int add(DinerNews dns);
	DinerNews update();
	int down(Integer dinerNewsID);
	List<DinerNews> getAll();
}
