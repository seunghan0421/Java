package java8.stream.collect;

import static java.util.stream.Collectors.*;
import static java8.stream.util.DishUtil.*;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import java8.stream.domain.Dish;

/**
 * 함수형 프로그래밍의 특징, '무엇'을 원하는지 직접 명시할 수 있다.
 *
 * Collector 인터페이스 구현은 스트림의 요소를 어떤 식으로 도출할 지 지정한다.
 * 컬렉터를 사용하면 collect로 결과를 수집하는 과정을 간단하면서도 유연한 방식으로 정의할 수 있다.
 * 구체적으로 스트림에 collect를 호출하면 스트림의 요소에 리듀싱 연산이 수행된다.
 */
public class Summary {

	public static void main(String[] args) {
		// 리듀싱과 요약
		// counting
		Long howManyDishes = getDishes().stream().collect(counting());
		long howManyDishesCount = getDishes().stream().count();

		// 스트림 값에서 최댓값과 최솟값  검색
		Comparator<Dish> dishCaloriesComparator =
			Comparator.comparingInt(Dish::getCalories);

		Optional<Dish> mostCalorieDish = getDishes().stream()
			.collect(maxBy(dishCaloriesComparator));

		Optional<Dish> mostCalorieDishByMax =
			getDishes().stream().max(dishCaloriesComparator);

		// sum, average 등 여러가지 연산을 한번에 해야 할 때
		IntSummaryStatistics menuStatistics = getDishes().stream()
			.collect(summarizingInt(Dish::getCalories));

		System.out.println(menuStatistics);

		// 문자열 joining
		String shortMenu = getDishes().stream()
			.map(Dish::getName)
			.collect(joining(", "));


	}
}
