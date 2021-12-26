package com.korZombiMiddleware.web.middleware;

import java.io.FileNotFoundException;
import java.io.IOException;
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

import com.korZombiMiddleware.web.middleware.entity.AreaNameEntity;
import com.korZombiMiddleware.web.middleware.entity.AreaSizeEntity;
import com.korZombiMiddleware.web.middleware.entity.TestEntity;
import com.korZombiMiddleware.web.middleware.repository.AreaNameRepository;
import com.korZombiMiddleware.web.middleware.repository.AreaSizeRepository;
import com.korZombiMiddleware.web.middleware.repository.TestRepository;
import com.korZombiMiddleware.web.middleware.utli.CommonUtil;

/*

*/

//@EntityScan(basePackages="com.korZombiMiddleware.web.entity.TestEntity")
//@EnableJpaRepositories(basePackages="com.korZombiMiddleware.web.repository.TestRepository")
@SpringBootApplication//(scanBasePackages = {"com.korZombiMiddleware"})
public class MiddlewareApplication implements ApplicationRunner {
	
	//@Autowired
    //private JdbcTemplate jdbcTemplate;
    //@Autowired
    //private DataSource dataSource;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private AreaNameRepository areaNameRepository;
    private void kor_area_name() throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException {
    	areaNameRepository.saveAll(new CommonUtil().readCsv("src/main/resources/kor_area_name.csv","UTF-8", "\\(", ")", new AreaNameEntity().columnNameList(), AreaNameEntity.class));
    }
    
    @Autowired
    private AreaSizeRepository areaSizeRepository;
    private void kor_area_size() throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException {
    	areaSizeRepository.saveAll(new CommonUtil().readCsv("src/main/resources/kor_area_size.csv", "UTF-8", ",", null, new AreaSizeEntity().columnNameList(), AreaSizeEntity.class));
    } 
    
    @Override
    public void run(ApplicationArguments args) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException {
    	kor_area_name();
    	kor_area_size();
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
