package java8.stream.usage;

import static java.util.stream.Collectors.*;
import static java8.stream.util.TransactionUtil.*;

import java.util.List;
import java.util.stream.Collectors;

import java8.stream.domain.Trader;
import java8.stream.domain.Transaction;

/*
실전 연습 문제

	Trader - name, city
	Transaction - trader, year, value
 */
public class Practice {

	// Q1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
	public static List<Integer> solution1() {
		return getTransactions().stream()
			.filter(t -> t.getYear() == 2011)
			.map(Transaction::getValue)
			.sorted()
			.collect(toList());
	}

	// Q2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오
	public static List<String> solution2() {
		return getTransactions().stream()
			.map(t -> t.getTrader().getCity())
			.distinct()
			.collect(toList());
	}

	// Q3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름 순으로 나열하시오
	public static List<String> solution3() {
		return getTransactions().stream()
			.filter(t -> t.getTrader().getCity() == "Cambridge")
			.map(t -> t.getTrader().getName())
			.distinct()
			.sorted()
			.collect(toList());
	}

	// Q4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오
	public static List<String> solution4() {
		return getTransactions().stream()
			.map(t -> t.getTrader().getName())
			.sorted()
			.distinct()
			.collect(toList());
	}

	// Q5. 밀라노에 거래자가 있는가?
	public static boolean solution5() {
		return getTransactions().stream()
			.anyMatch(t -> t.getTrader().getCity().equals("Milan"));
	}

	// Q6. 케임브리지에서 거주하는 거래자의 모든 트랜잭션 값을 출력하시오.
	public static void solution6() {
		getTransactions().stream()
			.filter(t -> t.getTrader().getCity().equals("Cambridge"))
			.map(Transaction::getValue)
			.forEach(System.out::println);

	}

	// Q7. 전체 트랜잭션 중 최댓값은 얼마인가?
	public static int solution7() {
		return getTransactions().stream()
			.map(Transaction::getValue)
			.reduce(Integer::max)
			.orElse(0);
	}

	// Q8. 전체 트랜잭션 중 최솟값은 얼마인가?
	public static int solution8() {
		return getTransactions().stream()
			.map(Transaction::getValue)
			.reduce(Integer::min)
			.orElse(0);
	}

}
