package com.dinernews.dao;

import java.util.Date;
import java.util.List;

import com.dinernews.entity.DinerNews;

public interface DinerNewsDAO {
	int add(DinerNews dns);
	DinerNews update(String news1 , String news2 , String news3 , String startDate ,
			String endDate , /*Integer empid*/String empName);
	int down(Integer dinerNewsID);
	List<DinerNews> getAll();
}
