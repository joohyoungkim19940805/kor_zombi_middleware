package com.korZombiMiddleware.web.middleware;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

import com.korZombiMiddleware.web.middleware.entity.TestEntity;
import com.korZombiMiddleware.web.middleware.repository.TestRepository;

/*

*/

//@EntityScan(basePackages="com.korZombiMiddleware.web.entity.TestEntity")
//@EnableJpaRepositories(basePackages="com.korZombiMiddleware.web.repository.TestRepository")
@SpringBootApplication(scanBasePackages = {"com.korZombiMiddleware"})
public class MiddlewareApplication implements ApplicationRunner {
	
	//@Autowired
    //private JdbcTemplate jdbcTemplate;
    //@Autowired
    //private DataSource dataSource;
    @Autowired
    private TestRepository testRepository;
    
    @Override
    public void run(ApplicationArguments args) {
    	try {
	    	String[] my_name = "abcdefghijklnmopqrstuvwxyz".split("");
	    	List<TestEntity> test_entity_list = new ArrayList<TestEntity>();
	    	
	    	for (int i = 0 ; i < my_name.length ; i++) {
	    		test_entity_list.add(new TestEntity( (my_name.length - i), (my_name[i]), (i+1) ));
	    	}
	    	
	    	testRepository.saveAll(test_entity_list);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
	
	/*
	@Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()){
            System.out.println(dataSource.getClass());
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            //String sql = "CREATE TABLE account(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            //statement.executeUpdate(sql);
           	
            //String sql = "SELECT *\n"
            //		+ "FROM pg_catalog.pg_tables\n"
            //		+ "WHERE schemaname != 'pg_catalog' AND \n"
            //		+ "    schemaname != 'information_schema'";
            
            String sql = "SELECT * FROM \"KOR_ZOMBI_TEST\"";
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){    
            	System.out.println(result.getString(1));
            	System.out.println(result.getString(2));
            	System.out.println(result.getString(3));
            }
        }
        //jdbcTemplate.execute("INSERT INTO account VALUES (1, 'dsunni')");
    }
    */
	public static void main(String[] args) {
		SpringApplication.run(MiddlewareApplication.class, args);
	}
}
