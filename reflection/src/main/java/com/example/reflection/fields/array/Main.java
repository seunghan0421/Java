package com.example.reflection.fields.array;

import java.lang.reflect.Array;

public class Main {

	public static void main(String[] args) {
		inspectArrayObject(new int[] {1, 2});
		inspectArrayObject(new double[][] {{1.5, 2.5}, {3.5, 4.5}});

		System.out.println("========================");

		inspectArrayValues(new int[] {1, 2, 5, 6, 7, 8, 9, 10});
		inspectArrayValues(new double[][] {{1.5, 2.5}, {3.5, 4.5}});
	}

	public static void inspectArrayValues(Object arrayObject) {
		int arrayLength = Array.getLength(arrayObject);

		System.out.print("[");

		for (int i = 0; i < arrayLength; ++i) {
			Object element = Array.get(arrayObject, i);

			if (element.getClass().isArray()) {
				inspectArrayValues(element);
			} else {
				System.out.print(element);
			}

			if (i != arrayLength - 1) {
				System.out.print(" , ");
			}
		}

		System.out.print("]");
	}

	public static void inspectArrayObject(Object arrayObject) {
		Class<?> clazz = arrayObject.getClass();

		System.out.println(String.format("is Array : %s", clazz.isArray()));

		Class<?> arrayComponentType = clazz.getComponentType();

		System.out.println(String.format("This is an array of type : %s", arrayComponentType.getTypeName()));

	}
}
