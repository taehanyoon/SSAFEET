package com.ssafy.ssafeet.model.dto;

import java.sql.Timestamp;

public class Review {
	private int courseId;
	private int userId;
	private Timestamp submitTime;
	private int rating;
	private String content;
	
	public Review() {
		
	}

	public Review(int courseId, int userId, Timestamp submitTime, int rating, String content) {
		super();
		this.courseId = courseId;
		this.userId = userId;
		this.submitTime = submitTime;
		this.rating = rating;
		this.content = content;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Timestamp submitTime) {
		this.submitTime = submitTime;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Review [courseId=" + courseId + ", userId=" + userId + ", submitTime=" + submitTime + ", rating="
				+ rating + ", content=" + content + "]";
	}

}
