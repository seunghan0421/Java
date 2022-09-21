package com.example.reflection.fields.jsonwriter;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import com.example.reflection.fields.jsonwriter.data.Actor;
import com.example.reflection.fields.jsonwriter.data.Address;
import com.example.reflection.fields.jsonwriter.data.Movie;
import com.example.reflection.fields.jsonwriter.data.Person;

public class Main {

	public static void main(String[] args) throws IllegalAccessException {

		final Address address = new Address("Main Street", (short)1);
		final Person john = new Person("John", true, 29, 100.555f, address);

		System.out.println(objectToJson(address, 0));
		System.out.println(objectToJson(john, 0));

		System.out.println("====================");
		System.out.println("check array is activated");

		Actor actor1 = new Actor("Elijah Wood", new String[] {"Lord of the Rings", "The Good Son"});
		Actor actor2 = new Actor("Tom Cruise", new String[] {"Mission Impossible", "The Good Son"});
		Actor actor3 = new Actor("Seunghan Hwang", new String[] {"Lord of the Rings", "The Good Son"});
		Movie movie = new Movie("Lord or the Rings", 8.8f,
			new String[] {"Action", "Adventure", "Drama"},
			new Actor[] {actor1, actor2, actor3});

		System.out.println(objectToJson(actor1, 0));
		System.out.println(objectToJson(movie, 0));

	}

	public static String objectToJson(Object instance, int indentSize) throws IllegalAccessException {
		Field[] fields = instance.getClass().getDeclaredFields();
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(indent(indentSize) + "{" + "\n");

		for (int i = 0; i < fields.length; ++i) {
			Field field = fields[i];
			field.setAccessible(true);

			if (field.isSynthetic()) {
				continue;
			}

			stringBuilder.append(indent(indentSize + 1));
			stringBuilder.append(formatStringValue(field.getName()));
			stringBuilder.append(" : ");

			if (field.getType().isPrimitive()) {
				stringBuilder.append(formatPrimitiveValue(field.get(instance), field.getType()));
			} else if (field.getType().equals(String.class)) {
				stringBuilder.append(formatStringValue(field.get(instance).toString()));
			} else if (field.getType().isArray()) {
				stringBuilder.append(arrayToJson(field.get(instance), indentSize + 1));
			} else {
				stringBuilder.append(objectToJson(field.get(instance), indentSize + 1));
			}

			if (i != fields.length - 1) {
				stringBuilder.append(",");
			}
			stringBuilder.append("\n");
		}

		stringBuilder.append(indent(indentSize) + "}");
		return stringBuilder.toString();
	}

	private static String indent(int indentSize) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < indentSize; ++i) {
			stringBuilder.append("\t");
		}

		return stringBuilder.toString();
	}

	private static String formatPrimitiveValue(Object instance, Class<?> type) throws IllegalAccessException {
		if (type.equals(boolean.class)
			|| type.equals(int.class)
			|| type.equals(long.class)
			|| type.equals(short.class)) {
			// JSON 포맷에서 정수, 불린값 등은 큰 따옴표나 특별한 formatting이 필요하지 않는다.
			return instance.toString();
		} else if (type.equals(double.class) || type.equals(float.class)) {
			// double이나 float과 같은 부동소수점은 소수점 둘째 자리로 제한 -> 메세지를 길게 만들지 않기 위하여
			return String.format("%.02f", instance);
		}
		// byte 혹은 char 지원하지 않는 원시형이 넘어올 경우 -> exception
		throw new RuntimeException(String.format("Type : %s is unsupported", type.getName()));
	}

	private static String arrayToJson(Object arrayInstance, int indentSize) throws IllegalAccessException {
		StringBuilder stringBuilder = new StringBuilder();
		int arrayLength = Array.getLength(arrayInstance);
		Class<?> componentType = arrayInstance.getClass().getComponentType();

		stringBuilder.append("[");
		stringBuilder.append("\n");
		for (int i = 0; i < arrayLength; ++i) {
			Object element = Array.get(arrayInstance, i);

			if (componentType.isPrimitive()) {
				stringBuilder.append(indent(indentSize + 1));
				stringBuilder.append(formatPrimitiveValue(element, element.getClass()));
			} else if (componentType.equals(String.class)) {
				stringBuilder.append(indent(indentSize + 1));
				stringBuilder.append(formatStringValue((String)element));
			}else{
				stringBuilder.append(objectToJson(element, indentSize + 1));
			}

			if (i != arrayLength - 1) {
				stringBuilder.append(" , ");
			}
			stringBuilder.append("\n");
		}

		stringBuilder.append(indent(indentSize));
		stringBuilder.append("]");

		return stringBuilder.toString();
	}

	private static String formatStringValue(String value) {
		return String.format("\"%s\"", value);
	}
}
