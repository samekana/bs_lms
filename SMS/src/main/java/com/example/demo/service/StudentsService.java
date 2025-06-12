package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Students;
import com.example.demo.mapper.StudentsMapper;

@Service
public class StudentsService {
	@Autowired
	StudentsMapper sm;
	public List<Students> mainSearch(Students students){
		return sm.mainSearch(students);
		
		
	}
}
