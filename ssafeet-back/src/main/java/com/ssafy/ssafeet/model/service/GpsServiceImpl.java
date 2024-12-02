package com.ssafy.ssafeet.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafeet.model.dao.GpsDao;
import com.ssafy.ssafeet.model.dto.Gps;

@Service
public class GpsServiceImpl implements GpsService{
	
	@Autowired
	private GpsDao gpsDao;
	
	@Override
	public void addGps(Map<String, Object> params) {
		gpsDao.insertGps(params);
	}
	
	@Override
	public Gps getPreviousGps(int id) {
		return gpsDao.selectGps(id);
	}

	@Override
	public List<Gps> getCourseGps(int id) {
		return gpsDao.selectCourseGps(id);
	}
}
