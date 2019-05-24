package com.demo.mod.dao;

import com.demo.mod.entity.Responsie;
import com.demo.mod.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResponsieDao extends CrudRepository<Responsie,Integer> {
    Responsie findResponsieByXzId(Integer zwinp);
}
