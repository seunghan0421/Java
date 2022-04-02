package me.develop_han.modern_java_in_action.chapter5;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.develop_han.modern_java_in_action.chapter4.Dish;

public class MappingExample {
	public static void main(String[] args) {
		MappingExample me = new MappingExample();
		List<Dish> dishes = me.initDishes();

		String[] words = new String[]{"Hello","World"};

		// 이렇게 할 경우 String[] 가 반환이 된다 -> flatmap을 사용하여 스트림 평면화가 가능하다.
		List<String[]> collect = Arrays.stream(words)
			.map(word -> word.split(""))
			.distinct()
			.collect(toList());

		List<String> wordList = new ArrayList<>();
		wordList.add("Hello");
		wordList.add("World");

		// 이렇게 했음에도 해결되지 않았다
		List<Stream<String>> collect1 = wordList.stream()
			.map(word -> word.split(""))
			.map(Arrays::stream)
			.distinct()
			.collect(toList());

		List<String> collect2 = wordList.stream()
			.map(word -> word.split(""))
			.flatMap(Arrays::stream)
			.distinct()
			.collect(toList());

		if(dishes.stream().anyMatch(Dish::isVegetarian)){
			System.out.println("The menu is (somewhat) vegetarian friendly");
		}

	}


	public List<Dish> initDishes() {
		return Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH)
		);
	}
}
