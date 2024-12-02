package com.ssafy.ssafeet.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafeet.model.dto.User;

public interface UserDao {

    // 회원 가입
    public int insertUser(User user);
    
    // 회원 정보 수정
    public void updateUser(User user);
    
    // 전체 유저 조회
    public List<User> selectAll();
    
    // 특정 회원 조회 (ID로)
    public User selectOne(int id);
    
    // 특정 회원 조회 (ID로)
    public User selectUser(Map<String, String> info);
    
    // 특정 회원 조회 (전화번호로)
    public User selectPhone(String phone);
    
    // 걸음 수 업데이트
    public void updateSteps(int id, int steps);
    
    // 걸음 수 초기화
    public void resetSteps(int id);
    
    // 포인트 적립 및 사용
    public void updatePoint(int id, int point);
    
    // 걸음 수와 포인트 동시 업데이트
    public void updateStepsAndPoints(int id, int steps, int points);
    
    // 목표 달성 여부 업데이트
    public void updateGoalStatus(int id, int goal, boolean achieved);
    
    // 오늘의 총 걸음 수 조회
    public int getTotalStepsToday(int id);
    
    // 현재 포인트 조회
    public int getPoints(int id);
    
    // 특정 목표 달성 여부 확인
    public boolean isGoalAchieved(int id, int goal);
    
    public int updateProduct(int userId, int productId);
}
