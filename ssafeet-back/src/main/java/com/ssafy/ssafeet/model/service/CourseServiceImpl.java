package com.ssafy.ssafeet.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafeet.model.dao.CourseDao;
import com.ssafy.ssafeet.model.dto.Gps;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseDao courseDao;

	@Override
	public List<Gps> getCourseGps(int id) {
		return courseDao.selectCourseGps(id);
	}
}
