package com.example.reflection.method.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.example.reflection.method.api.Product;

public class ProductTest {

	public static void main(String[] args) {
		testGetters(Product.class);
	}

	public static void testGetters(Class<?> dataClass) {
		Field[] fields = dataClass.getDeclaredFields();

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
