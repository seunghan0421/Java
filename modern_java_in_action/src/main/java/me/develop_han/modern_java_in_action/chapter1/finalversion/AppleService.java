package me.develop_han.modern_java_in_action.chapter1.finalversion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.develop_han.modern_java_in_action.chapter1.quiz.AppleFormatter;

public class AppleService {

	// 이렇게 메서드를 넘겨줌으로써 기존의 메서드를 무한정 활용할 수 있구나
	// 요구사항이 변경하여도 동작을 파라미터화 시켰기 때문에 재사용할 수 있다.
	public static List<Apple> filterGreenApples(List<Apple> inventory,ApplePredicate applePredicate){
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if(applePredicate.test(apple)){
				result.add(apple);
			}
		}

		return result;
	}

	public static List<Apple> sort(List<Apple> inventory, Comparator<Apple> comparator){
		Collections.sort(inventory,comparator);
		return inventory;
	}

	public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
		for (Apple apple : inventory) {
			String output = formatter.accept(apple);
			System.out.println(output);
		}
	}

}
