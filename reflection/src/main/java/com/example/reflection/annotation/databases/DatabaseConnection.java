package com.example.reflection.annotation.databases;

import java.io.IOException;

import com.example.reflection.annotation.annotations.InitializeMethod;
import com.example.reflection.annotation.annotations.InitializerClass;
import com.example.reflection.annotation.annotations.RetryOperation;

@InitializerClass
public class DatabaseConnection {
	private int failCounter = 5;

	@RetryOperation(
		numberOfRetries = 10,
		retryException = IOException.class,
		durationBetweenRetriesMs = 1000,
		failureMessage = "Connection to database 1 failed after retries"
	)
	@InitializeMethod
	public void connectDatabse1() throws IOException {
		System.out.println("Connecting to database 1");
		if (failCounter > 0) {
			failCounter--;
			throw new IOException("Connection failed");
		}

		System.out.println("Connection to database 1 succeeded");
	}

	@InitializeMethod
	public void connectDatabse2() {
		System.out.println("Connection to database 2");
	}

}
