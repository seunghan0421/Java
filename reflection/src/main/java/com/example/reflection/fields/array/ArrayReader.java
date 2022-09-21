package com.example.reflection.fields.array;

import java.lang.reflect.Array;

public class ArrayReader {

	public Object getArrayElement(Object array, int index) {
		if (index >= 0) {
			return Array.get(array, index);
		}
		int arrayLength = Array.getLength(array);
		return Array.get(array, arrayLength + index);
	}
}
