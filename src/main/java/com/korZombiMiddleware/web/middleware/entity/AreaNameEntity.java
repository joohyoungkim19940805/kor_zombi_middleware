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
@Table(name = "kor_area_name")
@Embeddable
public class AreaNameEntity {
	
	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "area_no")
	private long area_no;
	
	@Column(name = "area")
	private String area;
	
	public ArrayList<String> columnNameList() {
		return new ArrayList<String>() {{add("area"); add("area_no");}};
	}
	
	public AreaNameEntity() {}
	
	public AreaNameEntity(long area_no, String area) {
		this.area_no = area_no;
		this.area = area;
	}
	
	public long getArea_no() {
		return area_no;
	}
	public void setArea_no(long area_no) {
		this.area_no = area_no;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
