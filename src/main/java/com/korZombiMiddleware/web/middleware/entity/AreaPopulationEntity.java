package com.korZombiMiddleware.web.middleware.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 테이블을 생성할 때 테이블 명칭과 컬럼명은 반드시 소문자로 한다.
 * */


@Entity
@Table(name = "kor_area_population")
@Embeddable
public class AreaPopulationEntity {
	

	/** 행정안전부 인구통 테이블 https://jumin.mois.go.kr/
	 * csv에서 인구총계와 연령인구총계는 같으므로 연령인구총계는 csv에서 제거한다.
	 * _0~_10은 0~9세(_0), 10~19세(_1), 20~29세(_2), 30~39세 (_3), 40~49세(_4), 50~59세(_5), 60~69세(_6), 70~79세(_7), 80~89세(_8), 90~99세(_9), 100세 이상(_10)
	 * 을 의미한다.
	 * max는 남녀 인구를 합친 값이다.
	 * */
		
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "area_name")
	private String area_name;
	
	@Column(name = "area_code")
	private long area_code;
	
	@Column(name ="population_max")
	private long population_max;
	
	@Column(name = "population_max_0")
	private long population_max_0;
	
	@Column(name = "population_max_1")
	private long population_max_1;	
	
	@Column(name = "population_max_2")
	private long population_max_2;	
	
	@Column(name = "population_max_3")
	private long population_max_3;	
	
	@Column(name = "population_max_4")
	private long population_max_4;	
	
	@Column(name = "population_max_5")
	private long population_max_5;	
	
	@Column(name = "population_max_6")
	private long population_max_6;	
	
	@Column(name = "population_max_7")
	private long population_max_7;	
	
	@Column(name = "population_max_8")
	private long population_max_8;	
	
	@Column(name = "population_max_9")
	private long population_max_9;	
	
	@Column(name = "population_max_10")
	private long population_max_10;
	
	@Column(name = "population_man_max")
	private long population_man_max;	
	
	@Column(name = "population_man_0")
	private long population_man_0;	
	
	@Column(name = "population_man_1")
	private long population_man_1;	
	
	@Column(name = "population_man_2")
	private long population_man_2;	
	
	@Column(name = "population_man_3")
	private long population_man_3;	
	
	@Column(name = "population_man_4")
	private long population_man_4;	
	
	@Column(name = "population_man_5")
	private long population_man_5;	
	
	@Column(name = "population_man_6")
	private long population_man_6;	
	
	@Column(name = "population_man_7")
	private long population_man_7;	
	
	@Column(name = "population_man_8")
	private long population_man_8;	
	
	@Column(name = "population_man_9")
	private long population_man_9;	
	
	@Column(name = "population_man_10")
	private long population_man_10;
	
	@Column(name = "population_woman_max")
	private long population_woman_max;	
	
	@Column(name = "population_woman_0")
	private long population_woman_0;
	
	@Column(name = "population_woman_1")
	private long population_woman_1;
	
	@Column(name = "population_woman_2")
	private long population_woman_2;
	
	@Column(name = "population_woman_3")
	private long population_woman_3;
	
	@Column(name = "population_woman_4")
	private long population_woman_4;
	
	@Column(name = "population_woman_5")
	private long population_woman_5;
	
	@Column(name = "population_woman_6")
	private long population_woman_6;
	
	@Column(name = "population_woman_7")
	private long population_woman_7;
	
	@Column(name = "population_woman_8")
	private long population_woman_8;
	
	@Column(name = "population_woman_9")
	private long population_woman_9;
	
	@Column(name = "population_woman_10")
	private long population_woman_10;
	
	
	public ArrayList<String> columnNameList() {
		return new ArrayList<String>() {{add("area_name");add("area_code");add("population_max");
										add("population_max_0");add("population_max_1");add("population_max_2");
										add("population_max_3");add("population_max_4");add("population_max_5");
										add("population_max_6");add("population_max_7");add("population_max_8");
										add("population_max_9");add("population_max_10");add("population_man_max");
										add("population_man_0");add("population_man_1");add("population_man_2");
										add("population_man_3");add("population_man_4");add("population_man_5");
										add("population_man_6");add("population_man_7");add("population_man_8");
										add("population_man_9");add("population_man_10");add("population_woman_max");
										add("population_woman_0");add("population_woman_1");add("population_woman_2");
										add("population_woman_3");add("population_woman_4");add("population_woman_5");
										add("population_woman_6");add("population_woman_7");add("population_woman_8");
										add("population_woman_9");add("population_woman_10");}};
	}
	
	public AreaPopulationEntity() {}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public long getArea_code() {
		return area_code;
	}

	public void setArea_code(long area_code) {
		this.area_code = area_code;
	}

	public long getPopulation_max() {
		return population_max;
	}

	public void setPopulation_max(long population_max) {
		this.population_max = population_max;
	}

	public long getPopulation_max_0() {
		return population_max_0;
	}

	public void setPopulation_max_0(long population_max_0) {
		this.population_max_0 = population_max_0;
	}

	public long getPopulation_max_1() {
		return population_max_1;
	}

	public void setPopulation_max_1(long population_max_1) {
		this.population_max_1 = population_max_1;
	}

	public long getPopulation_max_2() {
		return population_max_2;
	}

	public void setPopulation_max_2(long population_max_2) {
		this.population_max_2 = population_max_2;
	}

	public long getPopulation_max_3() {
		return population_max_3;
	}

	public void setPopulation_max_3(long population_max_3) {
		this.population_max_3 = population_max_3;
	}

	public long getPopulation_max_4() {
		return population_max_4;
	}

	public void setPopulation_max_4(long population_max_4) {
		this.population_max_4 = population_max_4;
	}

	public long getPopulation_max_5() {
		return population_max_5;
	}

	public void setPopulation_max_5(long population_max_5) {
		this.population_max_5 = population_max_5;
	}

	public long getPopulation_max_6() {
		return population_max_6;
	}

	public void setPopulation_max_6(long population_max_6) {
		this.population_max_6 = population_max_6;
	}

	public long getPopulation_max_7() {
		return population_max_7;
	}

	public void setPopulation_max_7(long population_max_7) {
		this.population_max_7 = population_max_7;
	}

	public long getPopulation_max_8() {
		return population_max_8;
	}

	public void setPopulation_max_8(long population_max_8) {
		this.population_max_8 = population_max_8;
	}

	public long getPopulation_max_9() {
		return population_max_9;
	}

	public void setPopulation_max_9(long population_max_9) {
		this.population_max_9 = population_max_9;
	}

	public long getPopulation_max_10() {
		return population_max_10;
	}

	public void setPopulation_max_10(long population_max_10) {
		this.population_max_10 = population_max_10;
	}

	public long getPopulation_man_max() {
		return population_man_max;
	}

	public void setPopulation_man_max(long population_man_max) {
		this.population_man_max = population_man_max;
	}

	public long getPopulation_man_0() {
		return population_man_0;
	}

	public void setPopulation_man_0(long population_man_0) {
		this.population_man_0 = population_man_0;
	}

	public long getPopulation_man_1() {
		return population_man_1;
	}

	public void setPopulation_man_1(long population_man_1) {
		this.population_man_1 = population_man_1;
	}

	public long getPopulation_man_2() {
		return population_man_2;
	}

	public void setPopulation_man_2(long population_man_2) {
		this.population_man_2 = population_man_2;
	}

	public long getPopulation_man_3() {
		return population_man_3;
	}

	public void setPopulation_man_3(long population_man_3) {
		this.population_man_3 = population_man_3;
	}

	public long getPopulation_man_4() {
		return population_man_4;
	}

	public void setPopulation_man_4(long population_man_4) {
		this.population_man_4 = population_man_4;
	}

	public long getPopulation_man_5() {
		return population_man_5;
	}

	public void setPopulation_man_5(long population_man_5) {
		this.population_man_5 = population_man_5;
	}

	public long getPopulation_man_6() {
		return population_man_6;
	}

	public void setPopulation_man_6(long population_man_6) {
		this.population_man_6 = population_man_6;
	}

	public long getPopulation_man_7() {
		return population_man_7;
	}

	public void setPopulation_man_7(long population_man_7) {
		this.population_man_7 = population_man_7;
	}

	public long getPopulation_man_8() {
		return population_man_8;
	}

	public void setPopulation_man_8(long population_man_8) {
		this.population_man_8 = population_man_8;
	}

	public long getPopulation_man_9() {
		return population_man_9;
	}

	public void setPopulation_man_9(long population_man_9) {
		this.population_man_9 = population_man_9;
	}

	public long getPopulation_man_10() {
		return population_man_10;
	}

	public void setPopulation_man_10(long population_man_10) {
		this.population_man_10 = population_man_10;
	}

	public long getPopulation_woman_max() {
		return population_woman_max;
	}

	public void setPopulation_woman_max(long population_woman_max) {
		this.population_woman_max = population_woman_max;
	}

	public long getPopulation_woman_0() {
		return population_woman_0;
	}

	public void setPopulation_woman_0(long population_woman_0) {
		this.population_woman_0 = population_woman_0;
	}

	public long getPopulation_woman_1() {
		return population_woman_1;
	}

	public void setPopulation_woman_1(long population_woman_1) {
		this.population_woman_1 = population_woman_1;
	}

	public long getPopulation_woman_2() {
		return population_woman_2;
	}

	public void setPopulation_woman_2(long population_woman_2) {
		this.population_woman_2 = population_woman_2;
	}

	public long getPopulation_woman_3() {
		return population_woman_3;
	}

	public void setPopulation_woman_3(long population_woman_3) {
		this.population_woman_3 = population_woman_3;
	}

	public long getPopulation_woman_4() {
		return population_woman_4;
	}

	public void setPopulation_woman_4(long population_woman_4) {
		this.population_woman_4 = population_woman_4;
	}

	public long getPopulation_woman_5() {
		return population_woman_5;
	}

	public void setPopulation_woman_5(long population_woman_5) {
		this.population_woman_5 = population_woman_5;
	}

	public long getPopulation_woman_6() {
		return population_woman_6;
	}

	public void setPopulation_woman_6(long population_woman_6) {
		this.population_woman_6 = population_woman_6;
	}

	public long getPopulation_woman_7() {
		return population_woman_7;
	}

	public void setPopulation_woman_7(long population_woman_7) {
		this.population_woman_7 = population_woman_7;
	}

	public long getPopulation_woman_8() {
		return population_woman_8;
	}

	public void setPopulation_woman_8(long population_woman_8) {
		this.population_woman_8 = population_woman_8;
	}

	public long getPopulation_woman_9() {
		return population_woman_9;
	}

	public void setPopulation_woman_9(long population_woman_9) {
		this.population_woman_9 = population_woman_9;
	}

	public long getPopulation_woman_10() {
		return population_woman_10;
	}

	public void setPopulation_woman_10(long population_woman_10) {
		this.population_woman_10 = population_woman_10;
	}

	
}
	