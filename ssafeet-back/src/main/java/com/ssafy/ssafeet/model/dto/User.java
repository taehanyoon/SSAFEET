package com.ssafy.ssafeet.model.dto;

import java.sql.Timestamp;

public class User {
    private int id;
    private Integer product_id;
    private String phone;
    private String name;
    private String campus;
    private String password;
    private Timestamp signupTime;
    private int point;        // 총 포인트
    private int cummWalk;     // 누적 걸음 수
    private int currWalk;     // 현재 걸음 수 (오늘 기준)
    private int dailyGoal;    // 하루 목표 걸음 수
    private boolean goalAchieved; // 오늘 목표 달성 여부

    public User() {
    }

    public User(int id, String phone, String name, String campus, String password) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.campus = campus;
        this.password = password;
        this.point = 0;
        this.cummWalk = 0;
        this.currWalk = 0;
        this.dailyGoal = 8000; // 기본 목표 설정
        this.goalAchieved = false;
        this.setProduct_id(null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Timestamp signupTime) {
        this.signupTime = signupTime;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getCummWalk() {
        return cummWalk;
    }

    public void setCummWalk(int cummWalk) {
        this.cummWalk = cummWalk;
    }

    public int getCurrWalk() {
        return currWalk;
    }

    public void setCurrWalk(int currWalk) {
        this.currWalk = currWalk;
    }

    public int getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(int dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public boolean isGoalAchieved() {
        return goalAchieved;
    }

    public void setGoalAchieved(boolean goalAchieved) {
        this.goalAchieved = goalAchieved;
    }

    // 유틸리티 메서드
    public void incrementSteps(int steps) {
        this.currWalk += steps;
        this.cummWalk += steps;
    }

    public void addPoints(int points) {
        this.point += points;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", phone=" + phone + ", name=" + name + ", campus=" + campus + ", password="
				+ password + ", signupTime=" + signupTime + ", point=" + point + ", cummWalk=" + cummWalk
				+ ", currWalk=" + currWalk + ", dailyGoal=" + dailyGoal + ", goalAchieved=" + goalAchieved + "]";
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

}
