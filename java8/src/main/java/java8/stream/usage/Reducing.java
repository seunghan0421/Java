package java8.stream.usage;

import static java8.stream.DishUtil.*;

import java.util.Arrays;
import java.util.Optional;

public class Reducing {
	public static void main(String[] args) {
		// Reduce 연산 : 스트림 요소를 조합해서 더 복잡한 질의를 표현하는 방법을 설명
		// 모든 스트림 요소를 처리해서 값으로 도출

		// 함수형 프로그래밍 언어 용어로는 이 과정이 마치 종이(우리의 스트림)을
		// 작은 조각이 될 때까지 반복해서 접는 것과 비슷하다는 의미로 폴드(Fold)라고도 부른다.

		// 요소의 합
		Integer product = Arrays.asList(1, 2, 3, 4, 5).stream()
			.reduce(0, (a, b) -> a + b);

		// 초깃값 없음
		// 스트림에 아무 요소도 없으면 reduce는 합계를 반환할 수 없는 경우가 생기기에 Optional로 반환
		Optional<Integer> sum = Arrays.asList(1, 2, 3, 4, 5).stream()
			.reduce((a, b) -> a + b);

		// 최댓값과 최솟값
		Optional<Integer> minValue = Arrays.asList(1, 2, 3, 4, 5).stream()
			.reduce(Integer::min);
		Optional<Integer> maxValue = Arrays.asList(1, 2, 3, 4, 5).stream()
			.reduce(Integer::max);

		// Quiz - map과 reduct 연산을 활용해서 스트림의 요래 개수를 반환하시오
		int count = getDishes().stream()
			.map(d -> 1)
			.reduce(0, (a, b) -> a + b);

	}
}
