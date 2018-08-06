package com.biz.lesson.dao.grade;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.Grade;
import com.biz.lesson.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends CommonJpaRepository<Grade,String> , GradeDao{
    //查找所有选课学生
    @Query("SELECT o from Student o where o.classId=?1")
    public List<Student> getStudentsBygid(Integer id);

    //根据student类classId查询课程数目
    @Query("select count(o.code) from Student o where o.classId=?1")
    public Number getStudentNumByCode(Integer id);

    //根据code查班级
    @Query("select o from Grade o where o.code=?1")
    public Grade getGradeByCode(Integer code);

}
