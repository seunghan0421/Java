package me.develop_han.modern_java_in_action.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import me.develop_han.modern_java_in_action.chapter4.Dish;

public class SlicingExample {

	public static void main(String[] args) {
		List<Dish> dishes = DishFactoryClass.getDishes();
		// Predicate를 활용한 슬라이싱 -> filter, takeWhile, dropWhile
		// 320 calories 이하의 요리를 선택하는 방법
		List<Dish> over320Dishes = dishes.stream().filter(dish -> dish.getCalories() <= 320)
			.collect(Collectors.toList());

		//TakeWhile 활용 -> 만약 리스트가 정렬되어있다면 takewhile을 통해 만족하지 않는 조건이 나올떄까지 반복할 수 있다
		List<Dish> takewhileExample = dishes.stream()
			.takeWhile(d -> d.getCalories() < 320)
			.collect(Collectors.toList());

		//dropwhile 사용 -> 나머지 요소를 선택해야 할 때
		List<Dish> over320dishes = dishes.stream()
			.dropWhile(d -> d.getCalories() < 320)
			.collect(Collectors.toList());

		// 스트림 축소 -> limit() 사용
		List<Dish> streamdish3 = dishes.stream()
			.filter(dish -> dish.getCalories() > 300)
			.limit(3)
			.collect(Collectors.toList());

		// 요소 건너뛰기 -> 300 칼로리 이상의 처음 두 요리를 건너 뛴 다음 나머지 요리를 반환
		List<Dish> over300DishesSkip2 = dishes.stream()
			.filter(dish -> dish.getCalories() > 300)
			.skip(2)
			.collect(Collectors.toList());



	}


}
