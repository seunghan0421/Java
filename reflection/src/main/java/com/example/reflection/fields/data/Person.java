package com.example.reflection.fields.data;

public class Person {

	private final String name;
	private final boolean employed;
	private final int age;
	private final float salary;
	private final Address address;

	public Person(final String name, final boolean employed, final int age, final float salary, final Address address) {
		this.name = name;
		this.employed = employed;
		this.age = age;
		this.salary = salary;
		this.address = address;
	}
}
