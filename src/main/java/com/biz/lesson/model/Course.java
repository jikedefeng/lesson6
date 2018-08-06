package com.biz.lesson.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="stu_course")
public class Course extends Persistent {

	private static final long serialVersionUID = 5446164935766996651L;
	@Column(length=8)
	private String code;
	@Column
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy="courses")
	private Set<Student> students;



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}


}
