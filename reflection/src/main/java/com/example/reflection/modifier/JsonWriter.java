package com.example.reflection.modifier;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class JsonWriter {

	/**
	 * Serializes the provided instance into a JSON String representation
	 * Update this method as per instructions.
	 * Do not change the method's signature.
	 */
	public static String objectToJson(Object instance) throws IllegalAccessException {
		Field[] fields = instance.getClass().getDeclaredFields();
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("{");

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			int modifiers = field.getModifiers();
			if(Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)){
				continue;
			}

			field.setAccessible(true);

			if (field.isSynthetic()) {
				continue;
			}

			stringBuilder.append(formatStringValue(field.getName()));

			stringBuilder.append(":");

			if (field.getType().isPrimitive()) {
				stringBuilder.append(formatPrimitiveValue(field, instance));
			} else if (field.getType().equals(String.class)) {
				stringBuilder.append(formatStringValue(field.get(instance).toString()));
			}

			if (i != fields.length - 1) {
				stringBuilder.append(",");
			}
		}

		stringBuilder.append("}");
		return stringBuilder.toString();
	}

	private static String formatPrimitiveValue(Field field, Object parentInstance) throws IllegalAccessException {
		if (field.getType().equals(boolean.class)
			|| field.getType().equals(int.class)
			|| field.getType().equals(long.class)
			|| field.getType().equals(short.class)) {
			return field.get(parentInstance).toString();
		} else if (field.getType().equals(double.class) || field.getType().equals(float.class)) {
			return String.format("%.02f", field.get(parentInstance));
		}

		throw new RuntimeException(String.format("Type : %s is unsupported", field.getType().getName()));
	}

	private static String formatStringValue(String value) {
		return String.format("\"%s\"", value);
	}
}
