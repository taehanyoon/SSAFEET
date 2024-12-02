package com.ssafy.ssafeet.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafeet.model.dto.Gps;
import com.ssafy.ssafeet.model.service.GpsService;
import com.ssafy.ssafeet.model.service.UserService;

@RestController
@RequestMapping("/api-gps")
@CrossOrigin("*")
public class GpsRestController {

    private final GpsService gpsService;
    private final UserService userService;

    public GpsRestController(GpsService gpsService, UserService userService) {
        this.gpsService = gpsService;
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/track-steps")
    public void trackSteps(@RequestBody Map<String, Object> params) {
    	int id = (int) params.get("id");
    	
    	// 10초 전의 GPS 데이터 확인
    	Gps previousGps = gpsService.getPreviousGps(id);
    	System.out.println(previousGps);

    	// 현재 GPS 위치 정보
        gpsService.addGps(params);
        
        if (previousGps != null) {
            // 현재 위치와 10초 전 위치 간의 거리 계산
        	
        	final double R = 6371e3; // 지구 반지름 (미터)
        	float lat1 = ((Number) params.get("lat")).floatValue();
        	float lng1 = ((Number) params.get("lng")).floatValue();
        	float lat2 = previousGps.getLat();
        	float lng2 = previousGps.getLng();
            double phi1 = Math.toRadians(lat1);
            double phi2 = Math.toRadians(lat2);
            double deltaPhi = Math.toRadians(lat2 - lat1);
            double deltaLambda = Math.toRadians(lng2 - lng1);

            double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                    Math.cos(phi1) * Math.cos(phi2) *
                    Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            float distance = (float) (R * c); // 미터 단위로 반환
            System.out.println(distance);

            if (distance > 0) {
                // 걸음 수와 포인트 계산
                int steps = Math.round(distance / 0.3f); // 30cm 보폭 기준으로 걸음 수 계산

                // 현재 사용자의 상태를 가져옴
                int totalSteps = userService.getTotalStepsToday(id);

                // 걸음 수 목표 달성 체크 및 포인트 업데이트
                int points = 0;
                if (totalSteps >= 8000 && !userService.isGoalAchieved(id, 8000)) {
                    points = 10;
                    userService.markGoalAchieved(id, 8000);
                } else if (totalSteps >= 7500 && !userService.isGoalAchieved(id, 7500)) {
                    points = 40;
                    userService.markGoalAchieved(id, 7500);
                } else if (totalSteps >= 5000 && !userService.isGoalAchieved(id, 5000)) {
                    points = 30;
                    userService.markGoalAchieved(id, 5000);
                } else if (totalSteps >= 3000 && !userService.isGoalAchieved(id, 3000)) {
                    points = 20;
                    userService.markGoalAchieved(id, 3000);
                }

                if (points > 0) {
                    userService.addPoints(id, points);
                }
                
                userService.updateStepsAndPoints(id, steps, points);
            }
        }
    }
    
	@PostMapping("/course")
	public ResponseEntity<?> write(int id) {
		List<Gps> list = gpsService.getCourseGps(id);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Gps>>(list, HttpStatus.OK);
	}
    
}
