package com.demo.mod.dao;

import com.demo.mod.entity.Personnativ;
import com.demo.mod.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface PersonnativDao extends CrudRepository<Personnativ,Integer> {
}
