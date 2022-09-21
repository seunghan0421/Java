package com.example.reflection.fields.jsonwriter.data;

public class Actor {
	private final String name;
	private final String[] knownForMovies;

	public Actor(final String name, final String[] knownForMovies) {
		this.name = name;
		this.knownForMovies = knownForMovies;
	}
}
