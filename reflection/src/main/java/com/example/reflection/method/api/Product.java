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

	public void setPrice(final double price) {
		this.price = price;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setQuantity(final long quantity) {
		this.quantity = quantity;
	}

	public void setExpirationDate(final Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
