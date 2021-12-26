package com.korZombiMiddleware.web.middleware.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.korZombiMiddleware.web.middleware.entity.AreaNameEntity;
import com.korZombiMiddleware.web.middleware.entity.AreaPopulationEntity;


@Repository
public interface AreaPopulationRepository extends CrudRepository<AreaPopulationEntity, Long>{

}
