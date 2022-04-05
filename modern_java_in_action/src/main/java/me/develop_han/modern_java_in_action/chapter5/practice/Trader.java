package me.develop_han.modern_java_in_action.chapter5.practice;

public class Trader {
	private final String name;
	private final String city;

	public Trader(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "Trader{" +
			"name='" + name + '\'' +
			", city='" + city + '\'' +
			'}';
	}
}
