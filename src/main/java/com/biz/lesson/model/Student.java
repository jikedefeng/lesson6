package com.biz.lesson.model;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stu_student")
public class Student extends Persistent {
	private static final long serialVersionUID = 3706888238866848512L;
	@Column(length = 50)
	private String name;
	@Column
	private String code;

	@Column
	private Date birthday;

	@Column
	private Integer gender;

	@Column(length = 255)
	private String description;

	@Column
	private Integer classId;

	@Column
	private Double avgScore;
	@ManyToMany
	@JoinTable(name="stu_sc",
			joinColumns=@JoinColumn(name="STUDENT_ID",referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="COURSE_ID",referencedColumnName="id"))
	@JsonIgnore
	private Set<Course>courses;

//	@ManyToOne()
//	@JoinColumn(name = "gid")
//	private Grade grade;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

//	public Grade getGrade() {
//		return grade;
//	}
//
//	public void setGrade(Grade grade) {
//		this.grade = grade;
//	}

		public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", code='" + code + '\'' +
				", birthday=" + birthday +
				", gender=" + gender +
				", description='" + description + '\'' +
				", classId=" + classId +
				", courses=" + courses +
				'}';
	}
}
