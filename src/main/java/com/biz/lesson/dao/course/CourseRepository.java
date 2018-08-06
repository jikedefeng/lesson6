package com.biz.lesson.dao.course;


import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@RepositoryDefinition(domainClass = Student.class,idClass = Integer.class)
public interface CourseRepository extends CommonJpaRepository<Course, String>, CourseDao{

}


