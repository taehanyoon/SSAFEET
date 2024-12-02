package com.ssafy.ssafeet.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafeet.model.dto.Place;
import com.ssafy.ssafeet.model.dto.Product;

public interface CouponService {

	public List<Map<String, Object>> getCouponList(int userId);
	
	public boolean redeemCoupon(Map<String, Object> params);
	
	public boolean useCoupon(Map<String, Object> params);

	public List<Place> getAllPlaces(String campus);
	
	public List<Product> getAllProducts(int id);
	
}
