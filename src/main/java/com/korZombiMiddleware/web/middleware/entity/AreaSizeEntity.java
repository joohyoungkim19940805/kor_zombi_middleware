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
@Table(name = "kor_area_size")
@Embeddable
public class AreaSizeEntity {
	
	//area_name	citi_name	area_size	citi_size	citi_percent	non_citi_size	non_citi_percent
	
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "area_name")
	private String area_name;
	
	@Column(name = "citi_name")
	private String citi_name;
	
	@Column(name = "area_size")
	private long area_size;
	
	@Column(name = "citi_percent")
	private float citi_percent;
	
	@Column(name = "non_citi_size")
	private long non_citi_size;
	
	@Column(name = "non_citi_percent")
	private float non_citi_percent;
	
	public ArrayList<String> columnNameList() {
		return new ArrayList<String>() {{add("area_name"); add("citi_name"); add("area_size"); add("citi_size"); add("citi_percent"); add("non_citi_size"); add("non_citi_percent");}};
	}
	
	public AreaSizeEntity() {}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getCiti_name() {
		return citi_name;
	}

	public void setCiti_name(String citi_name) {
		this.citi_name = citi_name;
	}

	public long getArea_size() {
		return area_size;
	}

	public void setArea_size(long area_size) {
		this.area_size = area_size;
	}

	public float getCiti_percent() {
		return citi_percent;
	}

	public void setCiti_percent(float citi_percent) {
		this.citi_percent = citi_percent;
	}

	public long getNon_citi_size() {
		return non_citi_size;
	}

	public void setNon_citi_size(long non_citi_size) {
		this.non_citi_size = non_citi_size;
	}

	public float getNon_citi_percent() {
		return non_citi_percent;
	}

	public void setNon_citi_percent(float non_citi_percent) {
		this.non_citi_percent = non_citi_percent;
	}
	
}
	