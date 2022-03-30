package me.develop_han.modern_java_in_action.chapter1.quiz;

import me.develop_han.modern_java_in_action.chapter1.finalversion.Apple;

public class AppleSimpleFormatter implements AppleFormatter{
	@Override
	public String accept(Apple apple) {
		return "An Apple is " + apple.getWeight() + "g";
	}
}
