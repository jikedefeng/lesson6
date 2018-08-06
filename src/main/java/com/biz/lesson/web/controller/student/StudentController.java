package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.course.CourseService;
import com.biz.lesson.business.grade.GradeService;
import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.exception.ObjectNotExistsException;
import com.biz.lesson.model.Course;
import com.biz.lesson.model.Grade;
import com.biz.lesson.model.Student;
import com.biz.lesson.vo.student.StudentSearchVo;
import com.biz.lesson.vo.student.StudentVo;
import com.biz.lesson.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/student/student")
public class StudentController extends BaseController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private GradeService gradeService;
	//展示列表方法
	@RequestMapping("list")
//	@PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_LIST')")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView("student/student/list");
		List<Student> all = studentService.findAll();
		modelAndView.addObject("students",all);
		modelAndView.addObject("courses",courseService.findAll());
		return modelAndView;
	}
	//根据学号进行查询
	@RequestMapping("listByCode")
	public ModelAndView findByCode(String code) throws Exception {
		ModelAndView modelAndView = new ModelAndView("student/student/list");
		modelAndView.addObject("students", studentService.findByCode(code));
		return modelAndView;
	}
	//根据名字进行模糊查找
	@RequestMapping("listLikeName")
	public ModelAndView findLikeName(String name) throws Exception {
		ModelAndView modelAndView = new ModelAndView("student/student/list");
		modelAndView.addObject("students", studentService.findByNameLike(name));
		return modelAndView;
	}
	//添加学生数据
	@RequestMapping("/add")
//	@PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADD')")
	public ModelAndView add() throws Exception {
		ModelAndView view = new ModelAndView("student/student/add");
		view.addObject("cmd", "add");
		view.addObject("courses",courseService.findAll());
		return view;
	}
	//修改学生数据
	@RequestMapping("/edit")
//	@PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
	public ModelAndView edit(String id) throws Exception {
		ModelAndView view = new ModelAndView("student/student/add");
		Student studnet = studentService.get(id);
		view.addObject("student", studnet);
		view.addObject("courses", courseService.findAll());
		view.addObject("cmd", "edit");
		return view;
	}

	@RequestMapping("/save_add")
//	@PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADD')")
	public ModelAndView save_add(StudentVo vo, HttpServletRequest request) throws Exception {
		Student po = new Student();
		Set<Course>set=new HashSet<Course>();
		String[] str=request.getParameterValues("reqcourses");
		for (String string : str) {
			Course course=courseService.get(string);
			set.add(course);
		}
		String[] core=request.getParameterValues("score");
		Double sscore=0.0;
		if (core!=null) {
            for (String string : core) {
                Double score = Double.parseDouble(string);
                sscore = sscore + score;
            }
        }
		Double avgscore=sscore/set.size();
		avgscore=(double)Math.round(avgscore*100)/100;
		po.setName(vo.getName());
		po.setBirthday(vo.getBirthday());
		po.setCode(vo.getCode());
		po.setDescription(vo.getDescription());
		po.setGender(vo.getGender());
		po.setCourses(set);
		po.setClassId(vo.getClassId());
		po.setAvgScore(avgscore);
		po = studentService.save(po);
		return new ModelAndView("redirect:/student/student/list.do");

	}

	@RequestMapping("/save_edit")
//	@PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
	public ModelAndView save_edit(StudentVo vo, HttpServletRequest request) throws Exception {
		Set<Course> courses=new HashSet<Course>();
		String[] string=request.getParameterValues("reqcourses");
		for (String str : string) {
			Course course=courseService.get(str);
			courses.add(course);
		}
		String[] doubles=request.getParameterValues("score");
		Double sscore=0.0;
		for (String core:doubles){
			Double score=Double.parseDouble(core);
			sscore=sscore+score;
		}
		Double avgscore=sscore/courses.size();
		avgscore=(double)Math.round(avgscore*100)/100;
		Student po = studentService.get(vo.getId());
		if (Objects.isNull(po)) {
			throw new ObjectNotExistsException(vo.getId());
		}
		po.setName(vo.getName());
		po.setBirthday(vo.getBirthday());
		po.setCode(vo.getCode());
		po.setDescription(vo.getDescription());
		po.setGender(vo.getGender());
		po.setCourses(courses);
		po.setClassId(vo.getClassId());
		po.setAvgScore(avgscore);
		po = studentService.save(po);
		return new ModelAndView("redirect:/student/student/list.do");

	}
	//根据id进行删除
	@RequestMapping("/save_delete")
//	@PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_DELETE')")
	@ResponseBody
	public Boolean save_delete(@RequestParam("id") String id) throws Exception {
		return studentService.delete(id);
	}
	//搜索
	@RequestMapping("search")
	public ModelAndView search(StudentSearchVo vo) throws Exception {
		ModelAndView modelandview = new ModelAndView("student/student/list");
		modelandview.addObject("students", studentService.serach(vo));
		return modelandview;

	}
	@RequestMapping("/detail")
	@ResponseBody
	public ModelAndView detail(@RequestParam String id) {
		ModelAndView view=new ModelAndView("student/student/detail");
		Student student=studentService.get(id);
		Set<Course> courses = studentService.get(id).getCourses();
		ArrayList<String> list = new ArrayList<>();
		for (Course course : courses) {
			list.add(course.getName());
		}
		Integer classId=student.getClassId();
		Grade grade=gradeService.getGradeByCode(classId);
		view.addObject("student", student);
		view.addObject("courses", list);
		view.addObject("grade",grade);
//		view.addObject("cmd", "edit");
		return view;
	}


}
