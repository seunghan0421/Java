package com.example.reflection.method.polymolphism;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.reflection.method.polymolphism.database.DatabaseClient;
import com.example.reflection.method.polymolphism.http.HttpClient;
import com.example.reflection.method.polymolphism.logging.FileLogger;
import com.example.reflection.method.polymolphism.udp.UdpClient;

public class Main {
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
		DatabaseClient databaseClient = new DatabaseClient();
		HttpClient httpClient1 = new HttpClient("123.456.678.0");
		HttpClient httpClient2 = new HttpClient("11.33.55.0");
		FileLogger fileLogger = new FileLogger();
		UdpClient udpClient = new UdpClient();

		String requestBody = "request data";

		List<Class<?>> methodParameterTypes = Arrays.asList(new Class<?>[] {String.class});

		Map<Object, Method> requestExecutors = groupExecutors(
			Arrays.asList(databaseClient, httpClient1, httpClient2, fileLogger, udpClient), methodParameterTypes);

		executeAll(requestExecutors, requestBody);

	}

	public static void executeAll(Map<Object, Method> requestExecutors, String requestBody) throws
		InvocationTargetException,
		IllegalAccessException {
		for (Map.Entry<Object, Method> requestExecutorEntry : requestExecutors.entrySet()) {
			Object requestExecutor = requestExecutorEntry.getKey();
			Method method = requestExecutorEntry.getValue();

			Boolean result = (Boolean)method.invoke(requestExecutor, requestBody);

			if(result != null && result.equals(Boolean.FALSE)){
				System.out.println("Recieved negative result. Aborting...");
				return;
			}
		}
	}

	public static Map<Object, Method> groupExecutors(List<Object> requestExecutors,
		List<Class<?>> methodParameterTypes) {
		Map<Object, Method> instanceToMethod = new HashMap<>();

		for (Object requestExecutor : requestExecutors) {
			Method[] allMethods = requestExecutor.getClass().getDeclaredMethods();

			for (Method method : allMethods) {
				if (Arrays.asList(method.getParameterTypes()).equals(methodParameterTypes)) {
					instanceToMethod.put(requestExecutor, method);
				}
			}
		}

		return instanceToMethod;
	}
}
