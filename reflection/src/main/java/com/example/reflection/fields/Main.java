package com.example.reflection.fields;

import java.lang.reflect.Field;

public class Main {

	public static void main(String[] args) {
		printDeclaredFieldsInfo(Movie.MovieStats.class);
		System.out.println("===============================");
		printDeclaredFieldsInfo(Category.class);
	}

	public static void printDeclaredFieldsInfo(Class<?> clazz) {
		for (Field field : clazz.getDeclaredFields()) {
			System.out.println(String.format("Field name : %s type : %s",
				field.getName(),
				field.getType().getName()));

			System.out.println(String.format("Is synthetic field : %s", field.isSynthetic()));

			System.out.println();
		}
	}

	public static class Movie extends Product {
		public static final double MINIMUM_PRICE = 10.99;

		private boolean isReleased;
		private Category category;
		private double actualPrice;

		public Movie(final String name, final int year,
			final boolean isReleased, final Category category,
			final double actualPrice) {
			super(name, year);
			this.isReleased = isReleased;
			this.category = category;
			this.actualPrice = actualPrice;
		}

		public class MovieStats {
			private double timeWatched;

			public MovieStats(final double timeWatched) {
				this.timeWatched = timeWatched;
			}

			public double getRevenue() {
				return timeWatched * actualPrice;
			}
		}
	}

	//SuperClass
	public static class Product {
		protected String name;
		protected int year;
		protected double actualPrice;

		public Product(final String name, final int year) {
			this.name = name;
			this.year = year;
		}
	}

	public enum Category {
		ADVENTURE,
		ACTION,
		COMEDY
	}
}
