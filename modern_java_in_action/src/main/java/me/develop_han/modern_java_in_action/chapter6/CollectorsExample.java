package me.develop_han.modern_java_in_action.chapter6;

import static java.util.stream.Collectors.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import me.develop_han.modern_java_in_action.chapter4.Dish;

public class CollectorsExample {
	public static void main(String[] args) {
		List<Dish> menu = DishFactoryClass.getDishes();

		Long collect = menu.stream().collect(counting());

		// 스트림 값에서 퇴댓값, 최솟값 검색
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> maxDish = menu.stream().collect(maxBy(dishCaloriesComparator));

		int collect1 = menu.stream().collect(summingInt(Dish::getCalories));

		// 문자열 연결 !! 앞으로 StringBuilder를 사용하지 말고 collect(joining)을 하용하자 -> 내부적으로 StringBuilder를 사용하고 있음
	String menu_names = menu.stream().map(Dish::getName).collect(joining(","));

		Map<Dish.Type, List<Dish>> collect2 = menu.stream().collect(groupingBy(Dish::getType,filtering(dish -> dish.getCalories() > 500,toList())));

		menu.stream()
			.collect(groupingBy(Dish::getType,
				groupingBy(dish -> {
					if(dish.getCalories() <= 400){
						return "DIET";
					}else if ( dish.getCalories() <= 700){
						return "NORMAL";
					}else{
						return "FAT";
					}
				})));

	}
}
