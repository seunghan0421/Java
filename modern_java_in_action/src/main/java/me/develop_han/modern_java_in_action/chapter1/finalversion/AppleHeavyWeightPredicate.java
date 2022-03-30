package me.develop_han.modern_java_in_action.chapter1.finalversion;

public class AppleHeavyWeightPredicate implements ApplePredicate{
	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}
}
