package com.demo.mod.dao;

import com.demo.mod.entity.JobInstructions;
import org.springframework.data.repository.CrudRepository;

public interface JobInstructionsDao extends CrudRepository<JobInstructions,Integer> {
}
