package com.demo.mod.dao;

import com.demo.mod.entity.Department;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface DepartmentDao extends CrudRepository<Department, Integer> {
    @Modifying
    void deleteById(Integer id);
    List<Department> getAllByPid(Integer pid);
    @Modifying
    @Query(value = "update department d set d.name = ?1 where d.dp_id= ?2 ",nativeQuery = true)
    void updateOfname(String name,Integer id);
    List<Department> findAllByName(String name);
}
