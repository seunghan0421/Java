package java8.stream.designpattern.templatemethod;

import java.util.function.Consumer;

public class OnlineBankingWithLambda {

	public void processCustomer(int id, Consumer<Integer> makeCustomerHappy){ // 원래 Consumer<Customer>가 들어가야 함
		// Customer c = Database.getCustomerWithId(id);
		// makeCustomerHappy.accept(c);

	}

	public static void main(String[] args) {
		new OnlineBankingWithLambda().processCustomer(1337, c -> System.out.println("Hello"));
	}
}
