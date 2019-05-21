package com.demo.mod.service.impl;

import com.demo.mod.dao.DepartmentDao;
import com.demo.mod.dao.UserDao;
import com.demo.mod.entity.Department;
import com.demo.mod.entity.User;
import com.demo.mod.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    UserDao userDao;
    @Autowired
    DepartmentDao departmentDao;
    @Override
    public User userLogin(String name, String password) {
        return userDao.findUserByPNameAndPsword(name, password);
    }

    @Override
    public List<Department> getAllListOfDepartment() {
        return (List<Department>) departmentDao.findAll();
    }

    @Override
    public boolean addDep(Department department) {
        Department save = departmentDao.save(department);
        if(save==null)
            return false;
        return true;
    }
}
