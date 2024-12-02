package com.ssafy.ssafeet.model.service;

import java.util.List;

import com.ssafy.ssafeet.model.dto.User;

public interface UserService {

	public List<User> getUserList();
	
	public User getUser(int id);
	
	public boolean signup(User user);
	
	public User login(String phone, String password);

	public void updateStepsAndPoints(int id, int steps, int points);

	public User getUserById(int id);

	public void markGoalAchieved(int id, int goal);

	public void addPoints(int id, int bonusPoints);

	public int getTotalStepsToday(int id);

	public int getPoints(int id);

	public boolean isGoalAchieved(int id, int goal);
	
	public boolean setProduct(int userId, int productId);
	
}
