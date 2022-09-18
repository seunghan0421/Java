package com.example.reflection.introduction;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClassAnalyzer {
	private static final List<String> JDK_PACKAGE_PREFIXES =
		Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");

	public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> clazz) {
		PopupTypeInfo popupTypeInfo = new PopupTypeInfo();

		popupTypeInfo.setPrimitive(clazz.isPrimitive())
			.setInterface(clazz.isInterface())
			.setEnum(clazz.isEnum())
			.setName(clazz.getSimpleName())
			.setJdk(isJdkClass(clazz))
			.addAllInheritedClassNames(getAllInheritedClassNames(clazz));

		return popupTypeInfo;
	}

	/*********** Helper Methods ***************/

	public static boolean isJdkClass(Class<?> inputClass) {
		return JDK_PACKAGE_PREFIXES.stream()
			.anyMatch(packagePrefix ->
				inputClass.getPackage() == null ||
					inputClass.getPackage().getName().startsWith(packagePrefix));
	}

	public static String[] getAllInheritedClassNames(Class<?> inputClass) {
		String[] inheritedClasses;

		if (inputClass.isInterface()) {
			inheritedClasses = Arrays.stream(inputClass.getInterfaces())
				.map(Class::getSimpleName)
				.toArray(String[]::new);
		} else {
			Class<?> inheritedClass = inputClass.getSuperclass();
			inheritedClasses = inheritedClass != null ?
							   new String[] {inputClass.getSuperclass().getSimpleName()} : null;
		}

		return inheritedClasses;
	}

}
