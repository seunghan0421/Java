package com.example.reflection.method.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.reflection.method.api.Product;

public class ProductTest {

	public static void main(String[] args) {
		testGetters(Product.class);
		testSetters(Product.class);
	}

	public static void testSetters(Class<?> dataClass) {
		List<Field> fields = getAllFields(dataClass);

		for (Field field : fields) {
			String setterName = "set" + capitalizeFirstLetter(field.getName());

			Method setterMethod = null;
			try {
				setterMethod = dataClass.getMethod(setterName, field.getType());
			} catch (NoSuchMethodException e) {
				throw new IllegalStateException(
					String.format("Setter : %s not found", setterName)
				);
			}

			if (!setterMethod.getReturnType().equals(void.class)) {
				throw new IllegalStateException(
					String.format("Setter method : %s has to return void", setterName)
				);
			}
		}
	}

	public static void testGetters(Class<?> dataClass) {
		List<Field> fields = getAllFields(dataClass);

		Map<String, Method> methodNameToMethod = mapMethodNameToMethod(dataClass);

		for (Field field : fields) {
			String getterName = "get" + capitalizeFirstLetter(field.getName());

			// 검증 1 : 필드에 대한 Getter 메서드가 존재해야 한다.
			if (!methodNameToMethod.containsKey(getterName)) {
				throw new IllegalStateException(
					String.format("Field : %s doesn't have a getter method", field.getName()));
			}

			Method getter = methodNameToMethod.get(getterName);

			// 검증 2 : getter 메서드의 Return 타입과 필드의 타입은 같아야 한다.
			if (!getter.getReturnType().equals(field.getType())) {
				throw new IllegalStateException(
					String.format("Getter method : %s() has return type %s but expected %s",
						getter.getName(),
						getter.getReturnType().getTypeName(),
						field.getType().getTypeName()));
			}

			// 검증 3 : getter 메서드는 매개변수를 사용하지 않았어야 함
			if (getter.getParameterCount() > 0) {
				throw new IllegalStateException(
					String.format("Getter method : %s() has %d arguments", getterName, getter.getParameterCount()));
			}
		}

	}

	// Field 뿐만 아니라 Method, Constructor에도 적용할 수 있는 강력한 알고리즘 - 기억해두기
	private static List<Field> getAllFields(Class<?> clazz) {
		if (clazz == null || clazz.equals(Object.class)) {
			return Collections.emptyList();
		}

		Field[] curruentClassFields = clazz.getDeclaredFields();

		List<Field> inheritedFields = getAllFields(clazz.getSuperclass());

		List<Field> allFields = new ArrayList<>();
		allFields.addAll(Arrays.asList(curruentClassFields));
		allFields.addAll(inheritedFields);

		return allFields;
	}

	private static String capitalizeFirstLetter(final String letter) {
		return letter.substring(0, 1).toUpperCase() + letter.substring(1);
	}

	private static Map<String, Method> mapMethodNameToMethod(Class<?> dataClass) {
		Method[] allMethods = dataClass.getMethods();

		Map<String, Method> nameToMethod = new HashMap<>();

		for (Method method : allMethods) {
			nameToMethod.put(method.getName(), method);
		}

		return nameToMethod;
	}
}
