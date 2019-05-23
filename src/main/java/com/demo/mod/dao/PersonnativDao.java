package com.demo.mod.dao;

import com.demo.mod.entity.Personnativ;
import com.demo.mod.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonnativDao extends CrudRepository<Personnativ,Integer> {

}
