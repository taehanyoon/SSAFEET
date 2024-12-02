package com.ssafy.ssafeet.model.dto;

public class Place {
	private int id;
	private String name;
	private float lat;
	private float lng;
	private String phone;
	private String description;
	private String campus;
	
	public Place() {
		
	}

	public Place(int id, String name, float lat, float lng, String phone, String description, String campus) {
		super();
		this.id = id;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
		this.phone = phone;
		this.description = description;
		this.campus = campus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", lat=" + lat + ", lng=" + lng + ", phone=" + phone
				+ ", description=" + description + ", campus=" + campus + "]";
	}

}
