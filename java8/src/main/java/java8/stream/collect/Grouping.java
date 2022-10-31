package java8.stream.collect;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static java8.stream.collect.Grouping.CaloricLevel.*;
import static java8.stream.util.DishUtil.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import java8.stream.domain.Dish;

/**
 * 그룹화
 * 데이터 집합을 하나 이상의 특성으로 분류해서 그룹화하는 연산
 * 자바로 하면 복잡한 코드를 작성해야 하지만 자바 8의 함수형을 이용한다면 가독성 좋은 1줄로 구현할 수 있다.
 *
 */
public class Grouping {
	enum CaloricLevel {DIET, NORMAL, FAT}
	public static void main(String[] args) {
		// 단순한 그룹화
		Map<Dish.Type, List<Dish>> dishesByType = getDishes().stream()
			.collect(groupingBy(Dish::getType));

		// 단순한 속성 접근자 대신 더 복잡한 분류 기준이 필요한 상황에서는 메서드 참조를 분류로 사용할 수 없다.

		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = getDishes().stream().collect(
			groupingBy(dish -> {
				if (dish.getCalories() <= 400)
					return DIET;
				else if (dish.getCalories() <= 700)
					return NORMAL;
				else
					return FAT;
			})
		);

		// 중요 잘 이해하기! 그룹화 된 항목을 조작
		// filtering 메서드를 통해 각 그룹의 요소와 필터링 된 요소를 재그룹화
		Map<Dish.Type, List<Dish>> caloricDishesByType = getDishes().stream()
			.collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));

		// mapping 함수를 이용해 요소를 변환하는 작업
		 Map<Dish.Type, List<String>> dishNamesByType = getDishes().stream()
			.collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

		 // flatmap 이용
		// getDishes().stream()
		// 	.collect(groupingBy(Dish::getType,
		// 		flatMapping(dish -> dishTags.get(dish.getName)).stream()), toSet());

		// 다수준 그룹화
		final Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = getDishes().stream()
			.collect(groupingBy(Dish::getType,
				groupingBy(dish -> {
					if (dish.getCalories() <= 400) {
						return DIET;
					} else if (dish.getCalories() <= 700) {
						return NORMAL;
					}
					return FAT;
				})));

		// 서브 그룹으로 데이터 수집 -> 이것도 매우 자주 사용된다.
		// Type마다 count 구하기
		final Map<Dish.Type, Long> typesCount = getDishes().stream()
			.collect(groupingBy(Dish::getType, counting()));

		// Type에 따라 가장 높은 칼로리를 가진 요리를 찾는 프로그램
		final Map<Dish.Type, Optional<Dish>> mostCaloricByType = getDishes().stream()
			.collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

		// groupingBy 컬렉터는 스트림의 첫 번째 요소를 찾은 이후에야 그룹화 맵에 새로운 키를 (게으르게) 추가한다.
		//  따라서 Optional 래퍼를 굳이 사용할 필요는 없다.
		final Map<Dish.Type, Dish> mostClaoricByType = getDishes().stream()
			.collect(groupingBy(Dish::getType,
				collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

		// mapping 메서드로 만들어진 컬렉터도 groupingBy와 자주 사용된다.
		getDishes().stream().collect(
			groupingBy(Dish::getType, mapping(dish -> {
				if(dish.getCalories() <= 400) return DIET;
				else if(dish.getCalories() <= 700) return NORMAL;
				return FAT;
			}, toSet()))
		);
	}
}
