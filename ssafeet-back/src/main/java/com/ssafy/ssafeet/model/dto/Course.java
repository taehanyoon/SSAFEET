package com.ssafy.ssafeet.model.dto;

import java.sql.Timestamp;

public class Course {
	private int id;
	private int userId;
	private Timestamp startTime;
	private Timestamp endTime;
	private String name;
	private String description;
	
	public Course() {
		
	}

	public Course(int id, int userId, Timestamp startTime, Timestamp endTime, String name, String description) {
		super();
		this.id = id;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", userId=" + userId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", name=" + name + ", description=" + description + "]";
	}
	
}
