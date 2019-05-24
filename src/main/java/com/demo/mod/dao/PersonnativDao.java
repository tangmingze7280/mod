package com.demo.mod.dao;

import com.demo.mod.entity.Personnativ;
import com.demo.mod.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersonnativDao extends CrudRepository<Personnativ,Integer> , Repository<Personnativ,Integer>, JpaRepository<Personnativ,Integer>, JpaSpecificationExecutor<Personnativ>, PagingAndSortingRepository<Personnativ,Integer> {
    Personnativ findPersonnativByDpIdAndXzId(Integer dpid,Integer xzid);
    List<Personnativ> findAllByDpId(Integer dpid);
}
