package com.example.reflection.fields.jsonwriter;

import java.lang.reflect.Field;

import com.example.reflection.fields.jsonwriter.data.Address;
import com.example.reflection.fields.jsonwriter.data.Person;

public class Main {

	public static void main(String[] args) throws IllegalAccessException {

		final Address address = new Address("Main Street", (short)1);
		final Person john = new Person("John", true, 29, 100.555f, address);

		final String json = objectToJson(address, 0);
		final String personJson = objectToJson(john, 0);

		System.out.println(json);
		System.out.println(personJson);

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
				stringBuilder.append(formatPrimitiveValue(field, instance));
			} else if (field.getType().equals(String.class)) {
				stringBuilder.append(formatStringValue(field.get(instance).toString()));
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

	private static String formatPrimitiveValue(Field field, Object parentInstance) throws IllegalAccessException {
		if (field.getType().equals(boolean.class)
			|| field.getType().equals(int.class)
			|| field.getType().equals(long.class)
			|| field.getType().equals(short.class)) {
			// JSON 포맷에서 정수, 불린값 등은 큰 따옴표나 특별한 formatting이 필요하지 않는다.
			return field.get(parentInstance).toString();
		} else if (field.getType().equals(double.class) || field.getType().equals(float.class)) {
			// double이나 float과 같은 부동소수점은 소수점 둘째 자리로 제한 -> 메세지를 길게 만들지 않기 위하여
			return String.format("%.02f", field.get(parentInstance));
		}
		// byte 혹은 char 지원하지 않는 원시형이 넘어올 경우 -> exception
		throw new RuntimeException(String.format("Type : %s is unsupported", field.getType().getName()));
	}

	private static String formatStringValue(String value) {
		return String.format("\"%s\"", value);
	}
}
