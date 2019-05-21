package com.demo.mod.dao;

import com.demo.mod.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentDao extends CrudRepository<Department, Long> {

}
