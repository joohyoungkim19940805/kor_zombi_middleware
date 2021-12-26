package com.korZombiMiddleware.web.middleware.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.korZombiMiddleware.web.middleware.entity.AreaNameEntity;


@Repository
public interface AreaNameRepository extends CrudRepository<AreaNameEntity, Long>{

}
