package me.develop_han.modern_java_in_action.chapter5;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MappingPractice {
	public static void main(String[] args) {
		List<Integer> nums = Arrays.asList(1,2,3,4,5);

		// 숫자가 주어지만 각 숫자의 제곱근으로 이루어진 리스트를 반환
		nums.stream()
			.map(n -> n*n)
			.forEach(System.out::println);
			// .collect(toList());

		// 두 개의 숫자 리스트가 있을 때 모든 숫자 쌍의 리스트를 구하시오
		List<Integer> number1 = Arrays.asList(1,2,3);
		List<Integer> number2 = Arrays.asList(3,4);

		List<int[]> numbersSSangList = number1.stream()
			.flatMap(i -> number2.stream()
				.map(j -> new int[] {i, j}))
			.collect(toList());

		// 위의 예제에서 숫자의 합이 3으로 나눠지는 경우만 반환 -> filtering
		List<int[]> numListDivideBy3 = number1.stream()
			.flatMap(i -> number2.stream()
				.filter(j -> (i + j) % 3 == 0)
				.map(j -> new int[] {i, j}))
			.collect(toList());

	}
}
