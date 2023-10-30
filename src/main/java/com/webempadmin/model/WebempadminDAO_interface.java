package com.webempadmin.model;

import java.util.*;

public interface WebempadminDAO_interface {
          public void insert(WebempadminVO empVO);
          public void update(WebempadminVO empVO);
          public void delete(Integer empID);
          public void suspend(Integer empID);
          public WebempadminVO findByPrimaryKey(Integer empID);
          public Map<String,String> findAccoundPassword(String empName);
          public int findEmpID (String empName);
          public Map<String,Integer> findEmpStatus(String empName);
          public List<WebempadminVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpIDVO> getAll(Map<String, String[]> map); 
}
