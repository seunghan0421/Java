package com.example.reflection.annotation.configs;

import com.example.reflection.annotation.annotations.InitializeMethod;
import com.example.reflection.annotation.annotations.InitializerClass;

@InitializerClass
public class ConfigsLoader {

	@InitializeMethod
	public void loadAllConfigs() {
		System.out.println("Loading all configuration files");
	}
}
