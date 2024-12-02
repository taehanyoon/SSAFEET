package com.ssafy.ssafeet.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafeet.jwt.JwtUtil;
import com.ssafy.ssafeet.model.dto.Place;
import com.ssafy.ssafeet.model.dto.Product;
import com.ssafy.ssafeet.model.service.CouponService;

@RestController
@RequestMapping("/api-coupon")
@CrossOrigin("*")
public class CouponRestController {

	private final CouponService couponService;
	private final JwtUtil jwtUtil;

//	@Autowired
	public CouponRestController(CouponService couponService, JwtUtil jwtUtil) {
		this.couponService = couponService;
		this.jwtUtil = jwtUtil;
	}

	// 사용자 쿠폰 전체 가져오기
	@GetMapping("/coupons")
	public ResponseEntity<List<Map<String, Object>>> getCoupons(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "");

            int userId = jwtUtil.getUserIdFromToken(token);

            List<Map<String, Object>> coupons = couponService.getCouponList(userId);

            return ResponseEntity.ok(coupons);

        } catch (Exception e) {
        	System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

	// 쿠폰 구매
	@PostMapping("/redeem")
	public ResponseEntity<String> write(@RequestBody Map<String, Object> params) {
		if (couponService.redeemCoupon(params)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Coupon redeemed successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to redeem coupon");
	}
	
	// 쿠폰 사용
	@PutMapping("/use")
	public ResponseEntity<String> update(@RequestBody Map<String, Object> params) {
		if (couponService.useCoupon(params)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("쿠폰을 사용하였습니다!");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to use coupon");
	}
	
	@GetMapping("/places")
    public ResponseEntity<List<Place>> getPlaces(@RequestParam String campus) {
        try {
            List<Place> places = couponService.getAllPlaces(campus);
            return ResponseEntity.ok(places);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(@RequestParam int id) {
		try {
			List<Product> products = couponService.getAllProducts(id);
			return ResponseEntity.ok(products);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
