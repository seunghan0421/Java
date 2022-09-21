package com.example.reflection.fields.sizecalculator;

public class Main {

	public static void main(String[] args) throws IllegalAccessException {
		AccountSummary accountSummary = new AccountSummary("John", "Smith", 20, 100_000L);

		System.out.println(new ObjectSizeCalculator().sizeOfObject(accountSummary));
	}
}
