package com.example.reflection.annotation.databases;

import com.example.reflection.annotation.annotations.InitializeMethod;
import com.example.reflection.annotation.annotations.InitializerClass;

@InitializerClass
public class DatabaseConnection {

	@InitializeMethod
	public void connectDatabse1() {
		System.out.println("Connection to database 1");
	}

	@InitializeMethod
	public void connectDatabse2() {
		System.out.println("Connection to database 2");
	}

}
