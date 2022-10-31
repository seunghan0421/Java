package java8.stream.usage;

import static java8.stream.util.DishUtil.*;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import java8.stream.domain.Dish;

/**
 * reduce 메서드로 스트림 요소의 합을 구하는 예제는 [박싱 비용]이 숨어져 있다.
 *
 * 스트림 API는 숫자 스트림을 효율적으로 처리할 수 있도록 기본형 특화 스트림(primitive stream specialization)을 제공한다.
 * 박싱 비용을 피할 수 있는 기본형 스트림은 3가지가 있다. : IntStream, DoubleStream, LongStream
 */
public class NumberStream {

	public static void main(String[] args) {
		// 숫자 스트림으로 매핑
		// 기본형 스트림은 기본적으로 max, min, average 등 다양한 유틸리티 메서드도 지원한다.
		int caloriesSum = getDishes().stream()
			.mapToInt(Dish::getCalories)
			.sum();

		// 객체 스트림으로 복원하기
		IntStream intStream = getDishes().stream()
			.mapToInt(Dish::getCalories);
		Stream<Integer> boxedIntStream = intStream.boxed();

		// 기본값 : OptionalInt
		OptionalInt maxCalories = getDishes().stream()
			.mapToInt(Dish::getCalories)
			.max();

		// 숫자 범위 생성하기
		// IntStream과 LongStream은 range와 rangeClosed 두 가지 정적 메서드를 제공한다.
		IntStream evenNumbers = IntStream.rangeClosed(1, 100)
			.filter(i -> i % 2 == 0);

		// 숫자 스트림 활용 : 피타고리스 수
		IntStream.rangeClosed(1, 100).boxed()
			.flatMap(a ->
				IntStream.rangeClosed(1, 100)
						.mapToObj(b -> new int[] {a, b, (int)Math.sqrt(a * a + b * b)})
					.filter(t -> t[2] % 1 == 0)
			);

	}

}
