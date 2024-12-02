package com.ssafy.ssafeet.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.ssafeet.model.dao.UserDao;
import com.ssafy.ssafeet.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUserList() {
        return userDao.selectAll();
    }

    @Override
    public User getUser(int id) {
        return userDao.selectOne(id);
    }

    @Override
    public boolean signup(User user) {
        return userDao.insertUser(user) == 1;
    }

    @Override
	public User login(String phone, String password) {
		Map<String, String> info = new HashMap<>();
		info.put("phone", phone);
		info.put("password", password);
		User tmp = userDao.selectUser(info);
		return tmp;
	}

    @Override
    public void updateStepsAndPoints(int id, int steps, int points) {
        userDao.updateStepsAndPoints(id, steps, points);
    }

    @Override
    public User getUserById(int id) {
        return userDao.selectOne(id); // UserDao에 id로 사용자 정보를 가져오는 메서드가 있다고 가정
    }

    @Override
    public void markGoalAchieved(int id, int goal) {
        userDao.updateGoalStatus(id, goal, true); // 목표 달성을 기록
    }

    @Override
    public void addPoints(int id, int points) {
        userDao.updatePoint(id, points); // 포인트 추가
    }

    @Override
    public int getTotalStepsToday(int id) {
        // UserDao에서 오늘의 총 걸음 수를 가져오는 메서드를 호출
        return userDao.getTotalStepsToday(id);
    }

    @Override
    public int getPoints(int id) {
        // UserDao에서 현재 포인트를 가져오는 메서드를 호출
        return userDao.getPoints(id);
    }

    @Override
    public boolean isGoalAchieved(int id, int goal) {
        // UserDao에서 목표 달성 여부를 확인하는 메서드를 호출
        return userDao.isGoalAchieved(id, goal);
    }

	@Override
	public boolean setProduct(int userId, int productId) {
		return userDao.updateProduct(userId, productId) == 1;
	}
}
