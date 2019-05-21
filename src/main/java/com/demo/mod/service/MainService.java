package com.demo.mod.service;

import com.demo.mod.entity.User;

public interface MainService {
    default User userLogin(String name,String password){
        return null;
    }
}
