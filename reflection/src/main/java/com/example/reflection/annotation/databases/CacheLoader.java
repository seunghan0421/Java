package com.example.reflection.annotation.databases;

import com.example.reflection.annotation.annotations.InitializeMethod;
import com.example.reflection.annotation.annotations.InitializerClass;

@InitializerClass
public class CacheLoader {

	@InitializeMethod
	public void loadCache() {
		System.out.println("Loading data from cache");
	}

	public void reloadCache() {
		System.out.println("Reload cache");
	}

}
