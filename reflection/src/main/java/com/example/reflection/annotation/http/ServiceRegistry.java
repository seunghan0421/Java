package com.example.reflection.annotation.http;

import com.example.reflection.annotation.annotations.InitializeMethod;
import com.example.reflection.annotation.annotations.InitializerClass;

@InitializerClass
public class ServiceRegistry {

	@InitializeMethod
	public void registerService() {
		System.out.println("Service successfully registered");
	}
}
