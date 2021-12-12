package com.korZombiMiddleware.web.middleware.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 테이블을 생성할 때 테이블 명칭과 컬럼명은 반드시 소문자로 한다.
 * */

@Entity
@Table(name = "test_entity")
@Embeddable
public class TestEntity {

	@Id
	//@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "access_id")
	private long access_id;
	
	@Column(name = "my_name")
	private String my_name;
	
	@Column(name = "member_no")
	private int member_no;
	
	public TestEntity() {}
	
	public TestEntity(long access_id, String my_name, int member_no) {
		this.access_id = access_id;
		this.my_name = my_name;
		this.member_no = member_no;
	}
	
	public long getAccess_id() {
		return access_id;
	}
	public void setAccess_id(long access_id) {
		this.access_id = access_id;
	}
	
	public String getMy_name() {
		return my_name;
	}
	public void setMy_name(String my_name) {
		this.my_name = my_name;
	}
	
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	
	@Override
	public String toString() {
		return "[" + access_id + "] name = " + my_name + ", member_no = " + member_no;
	}
}
