package com.example.reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Exercise {

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface OpenResources {
	}

	public Set<Method> getAllAnnotatedMethods(Object input) {
		Set<Method> annotatedMethods = new HashSet<>();

		Method[] methods = input.getClass().getDeclaredMethods();

		for(Method method : methods){
			if (method.isAnnotationPresent(OpenResources.class)) {
				annotatedMethods.add(method);
			}
		}

		return annotatedMethods;
	}

}
