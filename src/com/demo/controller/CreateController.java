package com.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.demo.dao.StudentDao;
import com.demo.model.Student;

@Controller
public class CreateController {

	@Autowired
	private StudentDao studentDao;

	@RequestMapping(value = {"/create"}, method = RequestMethod.POST)
	public ModelAndView createStudent(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("course") String course, ModelAndView mv) {

		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		student.setCourse(course);

		int counter = studentDao.create(student);

		if (counter > 0) {
			mv.addObject("msg", "Student registration successful...");
		} else {
			mv.addObject("msg", "Error- check the console log.");
		}

		mv.setViewName("create");

		return mv;
	}
}