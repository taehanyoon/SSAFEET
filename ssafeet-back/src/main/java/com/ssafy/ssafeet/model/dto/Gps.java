package com.ssafy.ssafeet.model.dto;

import java.sql.Timestamp;

public class Gps {
	private int userId;
	private Timestamp time;
	private float lat;
	private float lng;
	
	public Gps() {
		
	}

	public Gps(int userId, Timestamp time, float lat, float lng) {
		super();
		this.userId = userId;
		this.time = time;
		this.lat = lat;
		this.lng = lng;
	}

	public int getUserId() {
		return userId;
	}

	public void setUser_id(int userId) {
		this.userId = userId;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "Gps [userId=" + userId + ", time=" + time + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
}
