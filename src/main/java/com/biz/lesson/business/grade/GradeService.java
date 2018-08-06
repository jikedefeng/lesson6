package com.biz.lesson.business.grade;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.grade.GradeRepository;
import com.biz.lesson.model.Grade;
import com.biz.lesson.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeService extends BaseService {

    @Autowired
    private GradeRepository gradeRepository;

    //查找所有班级信息
    public List<Grade> findAll(){return gradeRepository.findAll();}

    //存储班级
    public Grade save(Grade grade){return gradeRepository.save(grade);}

    //根据id获取班级
    public Grade get(String id) {
        return gradeRepository.findOne(id);
    }

    //删除班级
    public Boolean delete(String id) {
        gradeRepository.delete(id);
        return true;
    }

    //获取所有学生
    public List<Student> getStudentsBygid(Integer id){
        return  gradeRepository.getStudentsBygid(id);

    }

    //获取学生数目
    public Number getStudentNumByCode(Integer id){
       return gradeRepository.getStudentNumByCode(id);
    }

    //通过code获取班级
    public Grade getGradeByCode(Integer code){return gradeRepository.getGradeByCode(code);}
}
