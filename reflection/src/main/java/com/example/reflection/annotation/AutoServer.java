package com.example.reflection.annotation;

import com.example.reflection.annotation.annotations.InitializeMethod;
import com.example.reflection.annotation.annotations.InitializerClass;

@InitializerClass
public class AutoServer {

	@InitializeMethod
	public void startAutoSavingThreads() {
		System.out.println("Start automatic data saving to disk");
	}
}
