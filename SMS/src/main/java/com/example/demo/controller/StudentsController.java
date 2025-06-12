package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Students;
import com.example.demo.mapper.StudentsMapper;
import com.example.demo.service.StudentsService;


@Controller
public class StudentsController {
	@Autowired
	StudentsService ss;
	
	@Autowired
	StudentsMapper sm;
	
	@RequestMapping("/")
	public String mainList(Model model) {
		model.addAttribute("stu", new Students());
		model.addAttribute("stuList", ss.mainSearch(null));
		return "mainList";
	}

	@RequestMapping("/mainSearch")
	public String mainSearch(Students students, Model model) {
		model.addAttribute("stu", students);
		model.addAttribute("stuList", ss.mainSearch(students));
		return "mainList";
	}
	
	@RequestMapping("toAddPage")
	public String toAddPage() {
		return "addPage";
	}
	@RequestMapping("/addStu")
	public String addStu(Students students) {
		if(students.getGender().equals("0") ){
			students.setGender("男");
		}else {
			students.setGender("女");
		}
		students.setCreateDate(new Date());
		students.setDelflge(0);
		sm.insert(students);
		return "redirect:/";
	}
	
	//today
	@RequestMapping("/stuDel")
	public String stuDel(@RequestParam("id")int id,Model model) {
		Students stu = new Students();
		stu.setId(id);
		stu.setDeleteDate(new Date());
		sm.stuDel(stu);
		return "redirect:/";
	}
	
	@RequestMapping("/stuDet")
	public String toDetPage(@RequestParam("id")int id,Model model) {
//	创建一个新的对象用，来导入数据
		Students s = new Students();
		s.setId(id);
		model.addAttribute("stu",sm.mainSearch(s).get(0));
		return "detPage";
	}
	
	@RequestMapping("/toUpdPage")
	public String toUpdPage(@RequestParam("id")int id,Model model) {
	System.out.println(1);
		Students s = new Students();
		s.setId(id);
	System.out.println(s.getId());
		model.addAttribute("stu", sm.mainSearch(s).get(0));
		return "updPage";
	}
	
	@RequestMapping("/stuUpd")
	public String stuUpd(Students stu,Model model) {
		if(stu.getGender().equals("0") ){
			stu.setGender("男");
		}else {
			stu.setGender("女");
		}
		stu.setUpdateDate(new Date());
		sm.updateByPrimaryKeySelective(stu);
		return"redirect:/";
	}
}
