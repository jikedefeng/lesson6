package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.Student;
import com.biz.lesson.vo.student.StudentSearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CommonJpaRepository<Student, String>, StudentDao {

	List<Student> findByCode(String code);


	List<Student> findByNameLike(String key);


	List<Student> search(StudentSearchVo vo);




}
