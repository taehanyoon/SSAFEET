package com.ssafy.ssafeet.model.dto;

public class Product {
	private int id;
	private int placeId;
	private String name;
	private String description;
	private int price;
	
	public Product() {
		
	}

	public Product(int id, int placeId, String name, String description, int price) {
		super();
		this.id = id;
		this.placeId = placeId;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", placeId=" + placeId + ", name=" + name + ", description=" + description
				+ ", price=" + price + "]";
	}
	
}
