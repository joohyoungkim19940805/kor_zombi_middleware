package com.korZombiMiddleware.web.middleware;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.mapping.Map;
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
import com.korZombiMiddleware.web.middleware.entity.AreaPopulationEntity;
import com.korZombiMiddleware.web.middleware.entity.AreaSizeEntity;
import com.korZombiMiddleware.web.middleware.entity.TestEntity;
import com.korZombiMiddleware.web.middleware.repository.AreaNameRepository;
import com.korZombiMiddleware.web.middleware.repository.AreaPopulationRepository;
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
    //@Autowired
    //private TestRepository testRepository;
    
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
    
    @Autowired
    private AreaPopulationRepository areaPoplationRepository;
    private void kor_area_population() throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException {
    	areaPoplationRepository.saveAll(new CommonUtil().readCsv("src/main/resources/kor_aear_population.csv", "UTF-8", ",", null, new AreaPopulationEntity().columnNameList(), AreaPopulationEntity.class));
    }
    
    @Autowired
    private DataSource dataSource;
    private void kor_area_population_density() throws SQLException {
    	int result = -1;
    	Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("call insert_population_density(-1)");
        ResultSet rs = statement.executeQuery();
    	try (connection; statement; rs;){
            if(rs.next()) {
            	result = rs.getInt(1);
            }
            if(result == -1) {
            	throw new SQLException("kor_area_population_density 테이블에 데이터를 인설트하지 못했습니다.");
            }
        }
    }
    
    private void kor_area_shelter() throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException {
    	List<HashMap> data_list = new CommonUtil().readCsv("src/main/resources/kor_area_shelter.csv", "UTF-8", ",", "\"", null, HashMap.class);
    	//소재지전체 주소
    	/*
    	 * 은행(농협 포함), 마트 및 마켓(농협파머스마켓), 주차장, 우체국, 
    	 * 식당, 아파트 및 타운 및 맨션 및 타워, 전문대 및 대학 및 대학원, 학원 
    	 * 군청, 수련회 및 수련원, 사회복지관 및 예술회관 및 센터 및 센타, 
    	 * 사무소, 대피시설 및 대피소, 역, 교회, 성당, 웨딩,
    	 * 상가 및 빌딩, 홈플러스 및 롯데마트 및 이마트 및 신세계 및 하나로 , 세무서, 체육관, 보건소, 도서관, 법원, 경찰, 
    	 * 검찰, 호텔, 시장, 병원, 학교
    	 * */
    	//사업장명
    	//좌표정보x
    	//좌표정보y
    	//개방자치단체코드
    	for(HashMap data : data_list) {
    		System.out.println(data);
    	}
    }
    
    @Override
    public void run(ApplicationArguments args) throws FileNotFoundException, InstantiationException, IllegalAccessException, IOException, SQLException {
    	/*
    	kor_area_name();
    	kor_area_size();
    	kor_area_population();
    	kor_area_population_density();*/
    	kor_area_shelter();
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
