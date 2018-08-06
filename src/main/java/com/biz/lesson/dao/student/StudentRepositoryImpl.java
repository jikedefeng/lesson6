package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.Student;
import com.biz.lesson.vo.student.StudentSearchVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;


public class StudentRepositoryImpl extends CommonJpaRepositoryBean<Student, String> implements
        StudentDao {

	@Autowired
	public StudentRepositoryImpl(EntityManager em) {
		super(Student.class, em);
	}

    public List<Student> search(StudentSearchVo vo) {
       return this.findAll(buildSearchSpecification(vo));
    }

    private Specification<Student> buildSearchSpecification(final StudentSearchVo reqVo) {
        return (root, query, cb) -> {
            List<Predicate> predicates = Lists.newArrayList();

            if (StringUtils.isNotBlank(reqVo.getCode())) {
                predicates.add(cb.like(root.get("code"), "%"+reqVo.getCode()+"%"));
            }

            if (StringUtils.isNotBlank(reqVo.getName())) {
                predicates.add(cb.like(root.get("name"), "%" + reqVo.getName() + "%"));
            }

            if(reqVo.getFromDate()!=null){
                predicates.add(cb.greaterThan(root.get("birthday"), reqVo.getFromDate()));
            }


            if(reqVo.getToDate()!=null){
                predicates.add(cb.lessThanOrEqualTo(root.get("birthday"), reqVo.getToDate()));
            }

            // 将所有条件用 and 联合起来
            if (predicates.size() > 0) {
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            } else {
                return null;
            }
        };
    }


}
