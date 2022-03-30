package me.develop_han.modern_java_in_action.chapter1.finalversion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FilteringApples {
	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(Color.GREEN, 80)
			, new Apple(Color.GREEN, 80)
			, new Apple(Color.GREEN, 80));

		List<Apple> heavyApples = AppleService.filterGreenApples(inventory, new AppleHeavyWeightPredicate());
		List<Apple> greenApples = AppleService.filterGreenApples(inventory, new AppleGreenColorPredicate());

		AppleService.sort(inventory, new Comparator<Apple>() {
			@Override
			public int compare(Apple a1, Apple a2) {
				return Integer.compare(a1.getWeight(), a2.getWeight());
			}
		});

		AppleService.sort(inventory, Comparator.comparingInt(Apple::getWeight));

	}
}
