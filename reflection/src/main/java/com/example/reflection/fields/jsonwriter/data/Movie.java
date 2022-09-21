package com.example.reflection.fields.jsonwriter.data;

public class Movie {
	private final String name;
	private final float rating;
	private final String[] categories;
	private final Actor[] actors;

	public Movie(final String name, final float rating, final String[] categories, final Actor[] actors) {
		this.name = name;
		this.rating = rating;
		this.categories = categories;
		this.actors = actors;
	}
}
