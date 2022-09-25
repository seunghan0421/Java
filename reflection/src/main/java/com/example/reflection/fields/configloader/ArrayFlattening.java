package com.example.reflection.fields.configloader;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayFlattening {

	public <T> T concat(Class<?> type, Object... arguments) {

		// arguments가 없다면 -> null 반환
		if (arguments.length == 0) {
			return null;
		}

		ArrayList<Object> elements = new ArrayList<>();

		for (Object argument : arguments) {
			if (argument.getClass().isArray()) {
				int length = Array.getLength(argument);

				for (int i = 0; i < length; ++i) {
					elements.add(Array.get(argument, i));
				}
			} else {
				elements.add(argument);
			}
		}

		Object flattenedArray = Array.newInstance(type, elements.size());

		for (int i = 0; i < elements.size(); ++i) {
			Array.set(flattenedArray, i, elements.get(i));
		}

		return (T)flattenedArray;
	}

}
