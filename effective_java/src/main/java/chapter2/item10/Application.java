package chapter2.item10;

import java.math.BigDecimal;

public class Application {
	public static void main(String[] args) {
		Product product = Product.builder().price(new BigDecimal(990000)).name("autoValue").build();
		System.out.println(product); // Product{name=autoValue, price=990000}
	}
}
