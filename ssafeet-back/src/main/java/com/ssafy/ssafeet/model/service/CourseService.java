package com.ssafy.ssafeet.model.service;

import java.util.List;

import com.ssafy.ssafeet.model.dto.Gps;

public interface CourseService {

	public List<Gps> getCourseGps(int id);
}
