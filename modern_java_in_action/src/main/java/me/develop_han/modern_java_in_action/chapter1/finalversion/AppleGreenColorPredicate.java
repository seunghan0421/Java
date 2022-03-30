package me.develop_han.modern_java_in_action.chapter1.finalversion;

public class AppleGreenColorPredicate implements ApplePredicate{
	@Override
	public boolean test(Apple apple) {
		return Color.GREEN.equals(apple);
	}
}
