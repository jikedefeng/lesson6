package com.biz.lesson.web.controller.grade;


import com.biz.lesson.business.grade.GradeService;
import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.exception.ObjectNotExistsException;
import com.biz.lesson.model.Course;
import com.biz.lesson.model.Grade;
import com.biz.lesson.model.Student;
import com.biz.lesson.vo.course.CourseVo;
import com.biz.lesson.vo.grade.GradeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/grade/grade")
public class GradeController {

    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);

    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentService studentService;

    @RequestMapping("list")
//    @PreAuthorize("hasAuthority('OPT_COURSE_COURSE_LIST')")
    public ModelAndView list() throws Exception{
        ModelAndView modelAndView=new ModelAndView("grade/grade/list");
        List<Grade> grades=gradeService.findAll();
        List<Student> students=studentService.findAll();
//        modelAndView.addObject("avgscore",avgscore);
        modelAndView.addObject("grades",grades);
        modelAndView.addObject("students",students);
        return modelAndView;
    }

    @RequestMapping("/add")
//    @PreAuthorize("hasAuthority('OPT_COURSE_COURSE_ADD')")
    public ModelAndView  add() throws Exception{
        ModelAndView modelAndView=new ModelAndView("grade/grade/add");
        modelAndView.addObject("grades",gradeService.findAll());
        modelAndView.addObject("cmd", "add");
        return modelAndView;
    }

    ///////////////////////////////////////////////////////////////
    //修改课程的信息
    @RequestMapping("/edit")
//    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
    public ModelAndView edit(String id) throws Exception {
        ModelAndView view = new ModelAndView("grade/grade/add");
        Grade grade = gradeService.get(id);
        view.addObject("grade", grade);
        view.addObject("cmd", "edit");
        return view;
    }

    @RequestMapping("/save_add")
//    @PreAuthorize("hasAuthority('OPT_COURSE_COURSE_ADD')")
    public ModelAndView save_add(GradeVo vo , HttpServletRequest request) throws Exception{
        Grade grade=new Grade();
        String[] Tcode=(request.getParameterValues("code"));
        Integer code=0;
        for (String tcode:Tcode){
            code=Integer.parseInt(tcode);
        }
        grade.setCode(code);
        vo.setCode(code);
        grade.setClassName(code+"班");
        vo.setClassName(code+"班");
        grade=gradeService.save(grade);
        return  new ModelAndView("redirect:/grade/grade/list.do");
    }

    @RequestMapping("/save_edit")
//    @PreAuthorize("hasAuthority('OPT_COURSE_COURSE_EDIT')")
    public ModelAndView save_edit(Grade vo, HttpServletRequest request) throws Exception {
        Grade grade = gradeService.get(vo.getId());
        if (Objects.isNull(grade)) {
            throw new ObjectNotExistsException(vo.getId());
        }
        grade.setCode(vo.getCode());
        grade.setClassName(vo.getCode()+"班");
        grade = gradeService.save(grade);
        return new ModelAndView("redirect:/grade/grade/list.do");

    }

    //根据id进行删除
    @RequestMapping("/save_delete")
//    @PreAuthorize("hasAuthority('OPT_COURSE_COURSE_DELETE')")
    @ResponseBody
    public Boolean save_delete(@RequestParam("id") String id) throws Exception {
        return gradeService.delete(id);
    }

    //详情
    @RequestMapping("/detail")
    @ResponseBody
    public ModelAndView detail(@RequestParam String id) {
        ModelAndView view=new ModelAndView("grade/grade/detail");
        Grade grade=gradeService.get(id);
        int classId=grade.getCode();
        List<Student> students=gradeService.getStudentsBygid(classId);
        view.addObject("grade", grade);
        view.addObject("students", students);
        return view;
    }

}
