package com.example.reflection.method.polymolphism.http;

public class HttpClient {
	private String serverAddress;

	public HttpClient(final String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public boolean serverRequest(String data){
		System.out.println(String.format("Request with body : %s was successfully sent to server with address : %s",
			data,
			serverAddress));

		return true;

	}
}
