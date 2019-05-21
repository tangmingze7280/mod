package com.demo.mod.dao;

import com.demo.mod.entity.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentDao extends CrudRepository<Department, Long> {
    @Modifying
    void deleteById(Integer id);
    List<Department> getAllByPid(Integer pid);
}
