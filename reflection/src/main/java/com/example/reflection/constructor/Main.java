package com.example.reflection.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 이 부분이 Spring에서 DI를 하는 방식을 듯 잘 이해해 보자
public class Main {

	public static void main(String[] args) throws
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException {
		// Person person = new Person();
		// printConstructorData(person.getClass());

		Person person = (Person)createInstanceWithArguments(Person.class, "Seunghan");
		System.out.println(person);

	}

	public static Object createInstanceWithArguments(Class<?> clazz, Object... args) throws
		InvocationTargetException,
		InstantiationException,
		IllegalAccessException {
		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			if (constructor.getParameterTypes().length == args.length) {
				return constructor.newInstance(args);
			}
		}

		System.out.println("An appropriate constructor was not found");
		return null;
	}

	public static void printConstructorData(Class<?> clazz) {
		Constructor<?>[] constructors = clazz.getDeclaredConstructors();

		System.out.println(
			String.format("class %s has %d declared constructors", clazz.getSimpleName(), constructors.length));

		for (Constructor<?> constructor : constructors) {
			Class<?>[] parameterTypes = constructor.getParameterTypes();

			List<String> paramTypeNames = Arrays.stream(parameterTypes)
				.map(type -> type.getSimpleName())
				.collect(Collectors.toList());

			System.out.println(paramTypeNames);
		}

	}

	public static class Person {
		private final Address address;
		private final String name;
		private final int age;

		public Person() {
			this.address = null;
			this.age = 0;
			this.name = "anonymous";
		}

		public Person(final String name) {
			this.address = null;
			this.age = 0;
			this.name = name;
		}

		public Person(final String name, final int age) {
			this.address = null;
			this.name = name;
			this.age = age;
		}

		public Person(final Address address, final String name, final int age) {
			this.address = address;
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Person{" +
				"address=" + address +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
		}
	}

	public static class Address {
		private String street;
		private int number;

		public Address(final String street, final int number) {
			this.street = street;
			this.number = number;
		}
	}
}
