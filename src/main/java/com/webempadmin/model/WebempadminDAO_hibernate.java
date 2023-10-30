package com.webempadmin.model;

import java.util.*;

import com.webempadmin.entity.Webempadmin;

public interface WebempadminDAO_hibernate {
	
          Webempadmin  findByPrimaryKey(String empName) ;

}
