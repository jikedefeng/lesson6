package com.biz.lesson.web.controller.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.biz.lesson.business.course.CourseService;
import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.exception.ObjectNotExistsException;
import com.biz.lesson.model.Course;
import com.biz.lesson.model.Student;
import com.biz.lesson.vo.course.CourseVo;

@Controller
@RequestMapping("/course/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	private StudentService studentService;

	@RequestMapping("list")
	@PreAuthorize("hasAuthority('OPT_COURSE_COURSE_LIST')")
	public ModelAndView list() throws Exception {
		ModelAndView modelAndView = new ModelAndView("course/course/list");
		modelAndView.addObject("courses", courseService.findAll());
		return modelAndView;
	}
	@RequestMapping("/add")
	@PreAuthorize("hasAuthority('OPT_COURSE_COURSE_ADD')")
	public ModelAndView  add() throws Exception{
		ModelAndView modelAndView=new ModelAndView("course/course/add");
		modelAndView.addObject("cmd", "add");
		return modelAndView;
	}
	//修改课程的信息
	@RequestMapping("/edit")
	@PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
	public ModelAndView edit(String id) throws Exception {
		ModelAndView view = new ModelAndView("course/course/add");
		Course course = courseService.get(id);
		view.addObject("course", course);
		view.addObject("cmd", "edit");
		return view;
	}
	@RequestMapping("/save_add")
	@PreAuthorize("hasAuthority('OPT_COURSE_COURSE_ADD')")
	public ModelAndView save_add(CourseVo vo ,HttpServletRequest request) throws Exception{
		Course co=new Course();
		co.setCode(vo.getCode());
		co.setName(vo.getName());
		co=courseService.save(co);
		return  new ModelAndView("redirect:/course/course/list.do");
	}
	@RequestMapping("/save_edit")
	@PreAuthorize("hasAuthority('OPT_COURSE_COURSE_EDIT')")
	public ModelAndView save_edit(Course vo, HttpServletRequest request) throws Exception {
		Course co = courseService.get(vo.getId());
		if (Objects.isNull(co)) {
			throw new ObjectNotExistsException(vo.getId());

		}
		co.setName(vo.getName());
		co.setCode(vo.getCode());
		co = courseService.save(co);
		return new ModelAndView("redirect:/course/course/list.do");

	}
	//根据id进行删除
	@RequestMapping("/save_delete")
	@PreAuthorize("hasAuthority('OPT_COURSE_COURSE_DELETE')")
	@ResponseBody
	public Boolean save_delete(@RequestParam("id") String id) throws Exception {
		return courseService.delete(id);
	}

	@RequestMapping("/detail")
	@ResponseBody
	public ModelAndView detail(@RequestParam String id) {
		ModelAndView view=new ModelAndView("course/course/detail");
		Course course=courseService.get(id);
		Set<Student> students = courseService.get(id).getStudents();
		ArrayList<String> list = new ArrayList<>();
		Double sscore=0.0;
		for (Student student : students) {
			list.add(student.getName());
			sscore=sscore+student.getAvgScore();
		}
		Double avgscore=sscore/students.size();
		avgscore=(double)Math.round(avgscore*100)/100;
		view.addObject("course", course);
		view.addObject("list", list);
		view.addObject("avgscore",avgscore);
		return view;
	}

}
