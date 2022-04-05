package me.develop_han.modern_java_in_action.chapter5;

import java.util.stream.Stream;

public class InfiniteStreamExample {
	public static void main(String[] args) {

		// iterate는 초기값과 UnaryOperator 필요
		Stream
			.iterate(0, n -> n+2)
			.limit(5)
			.forEach(System.out::println);

		// 파보나치 수열의 집합
		Stream.iterate(new int[]{0,1},a -> new int[]{a[1],a[0] + a[1]})
			.limit(20)
			.forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));


		// limit 대신 프레디케이트로 중단하는 iterator
		Stream
			.iterate(0, n -> n < 100, n -> n + 4)
			.forEach(System.out::println);

		// 아래와 같이 필터를 사용하면 안된다! 필터는 언제 작업을 중단해야 할지 모른다.
		//쓰면 안되낟
		/*Stream
			.iterate(0, n -> n+4)
			.filter(n -> n < 100)
			.forEach(System.out::println);*/
	}
}
