package com.example.reflection;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		Class<String> stringClass = String.class;

		Map<String, Integer> mapObject = new HashMap<>();
		Class<? extends Map> hashMapClass = mapObject.getClass();

		// 내부 클래스는 $로 외부 클래스와 분리한다?
		final Class<?> squareClass = Class.forName("com.example.reflection.Main$Square");

		printClassInfo(stringClass, hashMapClass, squareClass);

	}

	private static void printClassInfo(Class<?> ... classes){

		for(Class<?> clazz : classes){
			System.out.println(String.format("class name : %s , class package name : %s ",
				clazz.getSimpleName(),
				clazz.getPackageName()));

			Class<?>[] implementedInterfaces = clazz.getInterfaces();

			for(Class<?> implementInterface : implementedInterfaces){
				System.out.println(String.format("class : %s , implements : %s ",
					implementInterface.getSimpleName(),
					implementInterface.getPackageName()));
			}

			System.out.println();
			System.out.println();
		}
	}

	private static class Square implements Drawable {

		@Override
		public int getNumberOfCorners() {
			return 0;
		}
	}

	private static interface  Drawable {
		int getNumberOfCorners();
	}

	private enum Color {
		BLUE,
		RED,
		GREEN
	}
}
