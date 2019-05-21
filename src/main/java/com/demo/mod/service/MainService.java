package com.demo.mod.service;

import com.demo.mod.entity.Department;
import com.demo.mod.entity.User;

import java.util.List;

public interface MainService {
    default User userLogin(String name,String password){
        return null;
    }
    default List<Department> getAllListOfDepartment(){
        return null;
    }
    boolean addDep(Department department);
}
