package com.demo.mod.dao;

import com.demo.mod.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,Integer> {
    User findUserByPNameAndPsword(String pName,String psword);
}
