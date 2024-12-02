package com.ssafy.ssafeet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafeet.jwt.JwtUtil;
import com.ssafy.ssafeet.model.dto.User;
import com.ssafy.ssafeet.model.service.UserService;

@RestController
@RequestMapping("/api-user")
@CrossOrigin("*")
public class UserRestController {

	private final UserService userService;
	private final JwtUtil jwtUtil;

//	@Autowired
	public UserRestController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	// 사용자 목록 전체 가져오기
	@GetMapping("/users")
	public ResponseEntity<?> userList() {
		List<User> list = userService.getUserList();
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	// 사용자 가져오기
	@GetMapping("/user")
	public ResponseEntity<?> user(int id) {
		User user = userService.getUserById(id);
		if (user == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 사용자 회원가입
	@PostMapping("/signup")
	public ResponseEntity<String> write(@RequestBody User user) {
		if (userService.signup(user)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add user");
	}

	// 사용자 로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		HttpStatus status = null;
		Map<String, Object> result = new HashMap<>();
		User loginUser = userService.login(user.getPhone(), user.getPassword());
		if (loginUser != null) {
			result.put("message", "login 성공");
			result.put("access-token", jwtUtil.createToken(loginUser));
			status = HttpStatus.ACCEPTED;
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(result, status);
	}
	
	@PutMapping("/goal")
	public ResponseEntity<String> update(int userId, int productId) {
		if (userService.setProduct(userId, productId)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Product used successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product");
	}
}
