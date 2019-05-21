package com.demo.mod.service.impl;

import com.demo.mod.dao.UserDao;
import com.demo.mod.entity.User;
import com.demo.mod.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    UserDao userDao;
    @Override
    public User userLogin(String name, String password) {
        return userDao.findUserByPNameAndPsword(name, password);
    }
}
