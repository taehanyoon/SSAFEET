package com.ssafy.ssafeet.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafeet.model.dao.CouponDao;
import com.ssafy.ssafeet.model.dto.Place;
import com.ssafy.ssafeet.model.dto.Product;

@Service
public class CouponServiceImpl implements CouponService{
	
	@Autowired
	private CouponDao couponDao;

	@Override
	public List<Map<String, Object>> getCouponList(int userId) {
		return couponDao.selectAll(userId);
	}

	@Override
	public boolean redeemCoupon(Map<String, Object> params) {
		return couponDao.insertCoupon(params) == 1;
	}

	@Override
	public boolean useCoupon(Map<String, Object> params) {
		return couponDao.updateCoupon(params) == 1;
	}

	@Override
	public List<Place> getAllPlaces(String campus) {
		return couponDao.selectPlaces(campus);
	}

	@Override
	public List<Product> getAllProducts(int id) {
		return couponDao.selectProducts(id);
	}
}
