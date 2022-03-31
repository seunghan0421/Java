package me.develop_han.modern_java_in_action.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
	public static void main(String[] args) {
		Menu menu = new Menu();
		List<Dish> dishes = menu.initDishes();

		/*
		task 의 결과
		filtering : pork
		mapping : pork
		filtering : beef
		mapping : beef
		filtering : chicken
		mapping : chicken
		[pork, beef, chicken]
		 */
		List<String> threeHighCalcoricDishNames =dishes.stream()
			.filter(m -> {
				System.out.println("filtering : " + m.getName());
				return m.getCalories() > 300;
			})
			.map(m -> {
				System.out.println("mapping : " + m.getName());
				return m.getName();
			})
			.limit(3)
			.collect(Collectors.toList());

		System.out.println(threeHighCalcoricDishNames);
	}

	public List<Dish> initDishes(){
		return Arrays.asList(
			new Dish("pork",false,800,Dish.Type.MEAT),
			new Dish("beef",false,700,Dish.Type.MEAT),
			new Dish("chicken",false,400,Dish.Type.MEAT),
			new Dish("french fries",true,530,Dish.Type.OTHER),
			new Dish("rice",true,350,Dish.Type.OTHER),
			new Dish("season fruit",true,120,Dish.Type.OTHER),
			new Dish("pizza",true,550,Dish.Type.OTHER),
			new Dish("prawns",false,300,Dish.Type.FISH),
			new Dish("salmon",false,450,Dish.Type.FISH)
		);
	}
}
