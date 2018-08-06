package com.biz.lesson.dao.course;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.Course;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class CourseRepositoryImpl extends CommonJpaRepositoryBean<Course, String> implements CourseDao {

	@Autowired
	public CourseRepositoryImpl(EntityManager em) {
		super(Course.class, em);
	}

}
