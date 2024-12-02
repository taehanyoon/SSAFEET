package com.ssafy.ssafeet.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafeet.model.dto.Place;
import com.ssafy.ssafeet.model.dto.Product;

public interface CouponDao {

    // 쿠폰 구매
    public int insertCoupon(Map<String, Object> params);
    
    // 전체 쿠폰 조회
    public List<Map<String, Object>> selectAll(int userId);
    
    // 쿠폰 사용
    public int updateCoupon(Map<String, Object> params);
    
    public List<Place> selectPlaces(String campus);
    
    public List<Product> selectProducts(int id);
    
}
