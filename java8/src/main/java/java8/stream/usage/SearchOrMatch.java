package java8.stream.usage;

import static java8.stream.DishUtil.*;

import java.util.Optional;

import java8.stream.Dish;

public class SearchOrMatch {

	public static void main(String[] args) {
		// Search
		// Predicate가 적어도 한 요소와 일치하는지 확인
		boolean isvegiterian = getDishes().stream().anyMatch(Dish::isVegetarian);

		// Predicate가 모든 요소와 일치하는지 검사
		boolean allVegiterian = getDishes().stream().allMatch(Dish::isVegetarian);

		// Match - 요소 검색
		// Optional - isPresent(), ifPresent(Consumer), T get(), T orElse(T other) 주로 쓰인다.

		// 임의의 요소 찾기
		Optional<Dish> anyVesiterianDish = getDishes().stream()
			.filter(Dish::isVegetarian)
			.findAny();

		// 첫 번째 요소 찾기
		Optional<Dish> firstVesiterianDish = getDishes().stream()
			.filter(Dish::isVegetarian)
			.findFirst();

		/*
		findFirst와 findAny 메서드가 모두 필요할까? 병렬성 때문에 필요하다.

		병렬 실행에서는 첫 번째 요소를 찾기 어렵기때문에, 따라서 요소의 반환 순서가 상관 없다면 병렬 스트림에서는 findAny()를 사용하자
		 */
	}
}
