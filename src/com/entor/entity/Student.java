package com.entor.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Student {
	private int id;
	private String name;
	private String username;
	private String password;
	private int sex;
	private int age;
	private Date brithday;
	private Timestamp createTime;
	
	public Student(int id, String name, String username, String password, int sex, int age, Date brithday,
			Timestamp createTime) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.brithday = brithday;
		this.createTime = createTime;
	}
	public Student() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", sex="
				+ sex + ", age=" + age + ", brithday=" + brithday + ", createTime=" + createTime + "]";
	}
	
	
	
}
