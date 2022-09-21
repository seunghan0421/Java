package com.example.reflection.fields.sizecalculator;

import java.lang.reflect.Field;

public class ObjectSizeCalculator {
	private static final long HEADER_SIZE = 12;
	private static final long REFERENCE_SIZE = 4;

	public long sizeOfObject(Object input) throws IllegalAccessException {
		long size = HEADER_SIZE + REFERENCE_SIZE;

		for (Field field : input.getClass().getDeclaredFields()) {
			// private도 접근할 수 있도록 허가
			field.setAccessible(true);

			// Synthetic 필드라면 건드리지 X
			if (field.isSynthetic()) {
				continue;
			}

			if (field.getType().isPrimitive()) {
				size += sizeOfPrimitiveType(field.getType());
			} else if (field.getType().equals(String.class)) {
				size += sizeOfString((String)field.get(input));
			} else {
				size += sizeOfObject(field.get(input));
			}
		}

		return size;

	}

	/*************** Helper methods ********************************/
	private long sizeOfPrimitiveType(Class<?> primitiveType) {
		if (primitiveType.equals(int.class)) {
			return 4;
		} else if (primitiveType.equals(long.class)) {
			return 8;
		} else if (primitiveType.equals(float.class)) {
			return 4;
		} else if (primitiveType.equals(double.class)) {
			return 8;
		} else if (primitiveType.equals(byte.class)) {
			return 1;
		} else if (primitiveType.equals(short.class)) {
			return 2;
		}
		throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
	}

	private long sizeOfString(String inputString) {
		int stringBytesSize = inputString.getBytes().length;
		return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
	}
}
