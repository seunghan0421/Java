package com.example.reflection.constructor;

import java.lang.reflect.Constructor;

import com.example.reflection.constructor.web.WebServer;

public class ObjectCreationMain {
	public static void main(String[] args) throws Exception {
		initConfiguration();
		WebServer webServer = new WebServer();
		webServer.startServer();

	}

	// Spring에서도 초기 SpringBean 설정
	public static void initConfiguration() throws Exception {
		Constructor<ServerConfiguration> constructor =
			ServerConfiguration.class.getDeclaredConstructor(int.class, String.class);

		constructor.setAccessible(true); // private 생성자에 접근할 것이기 때문에
		constructor.newInstance(8080, "Good Day!");
	}
}
