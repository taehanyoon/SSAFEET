package com.ssafy.ssafeet.model.dto;

import java.sql.Timestamp;

public class Coupon {
	private int productId;
	private int userId;
	private Timestamp redeemTime;
	private Timestamp usedTime;
	
	public Coupon() {
		
	}

	public Coupon(int productId, int userId, Timestamp redeemTime, Timestamp usedTime) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.redeemTime = redeemTime;
		this.usedTime = usedTime;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getRedeemTime() {
		return redeemTime;
	}

	public void setRedeemTime(Timestamp redeemTime) {
		this.redeemTime = redeemTime;
	}

	public Timestamp getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(Timestamp usedTime) {
		this.usedTime = usedTime;
	}

	@Override
	public String toString() {
		return "Coupon [productId=" + productId + ", userId=" + userId + ", redeemTime=" + redeemTime + ", usedTime="
				+ usedTime + "]";
	}

}
