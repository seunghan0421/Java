package java8.stream.usage;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;

public class Mapping {

	public static void main(String[] args) {
		// Mapping
		// 각 요소에 적영되며 적용한 결과가 새로운 요소로 매핑된다.
		List<Integer> stringLengths = asList("Modern", "Java", "In", "Action").stream()
			.map(String::length)
			.collect(toList());

		// 스트림 평면화 - FlatMap - 매우중요- 살짝쿵 어려움
		List<String> helloworld = asList("Hello", "World");

		// FlatMap은 각 배열을 스트림이 아니라 스트림의 콘텐츠로 매핑한다.
		List<String> flattedWord = helloworld.stream()
			.map(word -> word.split(""))
			.flatMap(Arrays::stream)
			.distinct()
			.collect(toList());

		// Quiz1 - 숫자 리스트가 주어졌을 때 각 숫자의 제곱근으로 이루어진 리스트를 반환하시오
		List<Integer> nums = asList(1, 2, 3, 4, 5);

		List<Double> squares = nums.stream()
			.map((i -> Math.pow(i, 2)))
			.collect(toList());

		// Quiz2 - 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 반환하시오
		// flatMap을 어떻게 사용할 수 있을 지 알 수있는 예제
		List<Integer> list1 = asList(1, 2, 3);
		List<Integer> list2 = asList(3, 4);

		List<int[]> pairs = list1.stream()
			.flatMap(i -> list2.stream()
				.map(j -> new int[] {i, j}))
			.collect(toList());

		// Quiz3 - Quiz2 + 합이 3으로 떨어지는 것  추출
		List<int[]> pairsDivideWith3 = list1.stream()
			.flatMap(i -> list2.stream()
				.filter(j -> (i + j) % 3 == 0)
				.map(j -> new int[] {i, j}))
			.collect(toList());

	}
}
