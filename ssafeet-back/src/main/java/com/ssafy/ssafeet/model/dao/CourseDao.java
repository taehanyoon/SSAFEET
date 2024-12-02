package com.ssafy.ssafeet.model.dao;

import java.util.List;

import com.ssafy.ssafeet.model.dto.Gps;

public interface CourseDao {
	
	// GPS 데이터 받아오기
	public List<Gps> selectCourseGps(int id);
}
