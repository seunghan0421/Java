package com.example.reflection.fields;

import java.lang.reflect.Field;

public class Main {

	public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
		// printDeclaredFieldsInfo(Movie.MovieStats.class);
		// System.out.println("===============================");
		// printDeclaredFieldsInfo(Category.class);

		Movie lord_of_the_rings =
			new Movie("Lord of the Rings", 2001, true, Category.ADVENTURE, 12.99);
		printDeclaredFieldsInfo(lord_of_the_rings.getClass(), lord_of_the_rings);

		Field minPriceStaticField = Movie.class.getDeclaredField("MINIMUM_PRICE");
		// 정적 변수이기 때문에 정상적으로 출력된다.
		System.out.println(String.format("static MINIMUM_PRICE value : %f", minPriceStaticField.get(null)));


	}

	public static <T> void printDeclaredFieldsInfo(Class<? extends T> clazz, T instance) throws IllegalAccessException {
		for (Field field : clazz.getDeclaredFields()) {
			System.out.println(String.format("Field name : %s type : %s",
				field.getName(),
				field.getType().getName()));

			System.out.println(String.format("Is synthetic field : %s", field.isSynthetic()));
			System.out.println(String.format("Field value is : %s", field.get(instance)));

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

		// Non Static inner class
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
