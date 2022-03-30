package me.develop_han.modern_java_in_action.chapter1.quiz;

import me.develop_han.modern_java_in_action.chapter1.finalversion.Apple;

public class AppleFancyFormatter implements AppleFormatter {
	@Override
	public String accept(Apple apple) {
		String charatistics = apple.getWeight() > 150 ? "Heavy" : "light";

		return "A " + charatistics + " " + apple.getColor() + " apple ";
	}
}
