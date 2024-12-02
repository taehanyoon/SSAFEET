package com.ssafy.ssafeet.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafeet.model.dto.Gps;

public interface GpsService {
    public void addGps(Map<String, Object> params);

	public Gps getPreviousGps(int id);
	
	public List<Gps> getCourseGps(int id);
}
