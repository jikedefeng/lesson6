package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.StudentRepository;
import com.biz.lesson.model.Student;
import com.biz.lesson.vo.student.StudentSearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "studentServices")
public class StudentService extends BaseService {
	 private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

	 @Autowired()
	 private StudentRepository studentRepository;

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Student get(String id) {

		return studentRepository.findOne(id);
	}

	public Student save(Student student) {
		return studentRepository.save(student);
	}

	public Boolean delete(String id) {
		 studentRepository.delete(id);
		 return true;
	}
	 public List<Student>findByCode(String code){
		 return studentRepository.findByCode(code);
	 }
	 public List<Student>findByNameLike(String name){
		 String key = "%"+name+"%";
		 return studentRepository.findByNameLike(key);
	 }
	 public List<Student> serach(StudentSearchVo vo){
		 return studentRepository.search(vo);
	 }


}
