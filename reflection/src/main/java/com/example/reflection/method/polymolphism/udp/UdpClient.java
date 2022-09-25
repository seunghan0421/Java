package com.example.reflection.method.polymolphism.udp;

public class UdpClient {
	public void sendAndForget(String requestPayload){
		System.out.println(String.format("Request : %s was sent through UDP", requestPayload));
	}
}
