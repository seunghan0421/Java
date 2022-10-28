package java8.stream.usage;

import static java.util.stream.Collectors.*;
import static java8.stream.DishUtil.*;

import java.util.Arrays;
import java.util.List;

import java8.stream.Dish;

public class Filtering {

	public static void main(String[] args) {
		// Filtering
		// Predicate로 필터링
		List<Dish> menu1 = getDishes().stream()
			.filter(Dish::isVegetarian)
			.collect(toList());

		// 고유 요서 필터링 by distinct
		List<Integer> distinctNums = Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream()
			.distinct()
			.collect(toList());

		// Slicing
		// TakeWhile(Java 9) - Predicate를 만족하는 작업을 만났을 때 중단한다.
		List<Dish> sliceMenu1 = getDishes().stream()
			.takeWhile(dish -> dish.getCalories() < 320)
			.collect(toList());

		// takeWhile의 정 반대 작업을 수행한다.
		List<Dish> sliceMenu2 = getDishes().stream()
			.dropWhile(dish -> dish.getCalories() < 320)
			.collect(toList());

		// 스트림 축소 - limit
		List<Dish> limitedDish = getDishes().stream()
			.filter(dish -> dish.getCalories() > 300)
			.limit(3)
			.collect(toList());

		// 요소 건너뛰기 - skip
		List<Dish> skippedDish = getDishes().stream()
			.filter(dish -> dish.getCalories() > 300)
			.skip(3)
			.collect(toList());

	}
}
