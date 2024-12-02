package com.ssafy.ssafeet.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafeet.model.dto.Gps;

public interface GpsDao {
	
	// GPS 데이터 받아오기
	public void insertGps(Map<String, Object> params);
	
	public Gps selectGps(int id);
	
	public List<Gps> selectCourseGps(int id);
}
