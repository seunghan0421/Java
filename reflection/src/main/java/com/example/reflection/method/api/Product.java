package com.example.reflection.method.api;

import java.util.Date;

public class Product {
	private double price;
	private String name;
	private long quantity;
	private Date expirationDate;

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public long getQuantity() {
		return quantity;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
}
