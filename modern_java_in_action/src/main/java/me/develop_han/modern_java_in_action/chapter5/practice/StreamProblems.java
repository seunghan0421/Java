package me.develop_han.modern_java_in_action.chapter5.practice;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class StreamProblems {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
		);

		// Problem 1 : 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
		transactions.stream()
			.filter( t -> t.getYear() == 2011)
			.sorted(comparing(Transaction::getValue))
			.collect(toList());

		// Problem 2 : 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
		System.out.println("Problem 2 : 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.");
		transactions.stream()
			.map(t -> t.getTrader().getCity())
			.distinct()
			.collect(toList());

		// Problem 3 : 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
		System.out.println("Problem 3 : 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.");
		transactions.stream()
			.map(Transaction::getTrader)
			.filter(trader -> trader.getCity().equals("Cambridge"))
			.distinct()
			.sorted(comparing(Trader::getName))
			.collect(toList());

		// Problem 4 : 모든 거래자의 이름을 알파벳 순으로 정렬해서 반환하시오
		transactions.stream()
			.map(t -> t.getTrader().getName())
			.distinct()
			.sorted(String::compareTo)
			.collect(Collectors.toList());


		// Problem 5 : 케임브리지에서 거주하는 거래자의 모든 트랜잭션 값을 출력하시오
		transactions.stream()
			.filter(t -> t.getTrader().getCity().equals("Cambridge"))
			.map(Transaction::getValue)
			.forEach(System.out::println);

		// Problem 6 : 밀라노에 거래자가 있는가?
		transactions.stream()
			.anyMatch(t -> t.getTrader().getCity().equals("Milano"));

		// Problem 7 : 전체 트랜잭션 중 최댓값은 얼마인가?
		Optional<Integer> max_value = transactions.stream()
			.map(Transaction::getValue)
			.reduce(Integer::max);

		OptionalInt max = transactions.stream()
			.mapToInt(Transaction::getValue)
			.max();

		// Problem 8 : 전체 트랜잭션 중 최솟값은 얼마인가?
		transactions.stream()
			.map(Transaction::getValue)
			.reduce(Integer::min);
	}
}
