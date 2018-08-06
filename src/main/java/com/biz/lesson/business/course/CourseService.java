package com.biz.lesson.business.course;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.course.CourseRepository;
import com.biz.lesson.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService extends BaseService {

	 @Autowired()
	 private CourseRepository courseRepository;

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course save(Course course) {
		return courseRepository.save(course) ;
	}

	public Course get(String id) {
		return courseRepository.findOne(id);
	}
	public Boolean delete(String id) {
		courseRepository.delete(id);
		 return true;
	}
}
