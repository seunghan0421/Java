package me.develop_han.modern_java_in_action.chapter5;

import java.util.List;

import me.develop_han.modern_java_in_action.chapter4.Dish;

public class ReducingExample {
	public static void main(String[] args) {
		List<Dish> dishes = DishFactoryClass.getDishes();

		int sum = dishes.stream().mapToInt(dish -> 1)
			.reduce(0, Integer::sum);

		System.out.println(sum);
	}
}
