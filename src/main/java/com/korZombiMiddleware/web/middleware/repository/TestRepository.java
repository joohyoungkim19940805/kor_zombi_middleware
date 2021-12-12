package com.korZombiMiddleware.web.middleware.repository;

import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.korZombiMiddleware.web.middleware.entity.TestEntity;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<TestEntity, Long>{
	/*
	List<TestEntity> findAll();
	
	TestEntity findByAccess_id(long access_id);
	
	List<TestEntity> findByMy_name(String my_name);
	
	TestEntity findByMemeber_no(int member_no);
	
	List<TestEntity> findByNameAndAgeLessThan(String my_name, int member_no);
	
	@Query("select t from Member t where my_name=:my_name and member_no < :member_no")
	List<TestEntity> findByNameAndAgeLessThanSQL(@Param("my_name") String name, @Param("member_no") int age);
	
	List<TestEntity> findByNameAndAgeLessThanOrderByAgeDesc(String my_name, int member_no);
	*/
}
