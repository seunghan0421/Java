package java8.stream.designpattern.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import java8.stream.designpattern.factory.domain.Bond;
import java8.stream.designpattern.factory.domain.Loan;
import java8.stream.designpattern.factory.domain.Product;
import java8.stream.designpattern.factory.domain.Stock;

public class ProductFactory {
	// 람다 적용 후
	final static Map<String, Supplier<Product>> map = new HashMap<>();
	static {
		map.put("loan", Loan::new);
		map.put("stock", Stock::new);
		map.put("bond", Bond::new);
	}

	public static Product createProductWithLambda(String name){
		Supplier<Product> p = map.get(name);
		if(p != null)
			return p.get();

		throw new IllegalArgumentException("No Such product " + name);
	}

	// 람다 저용 전
	public static Product createProduct(String name){
		switch (name){
			case "loan" :
				return new Loan();
			case "stock" :
				return new Stock();
			case "bond" :
				return new Bond();
			default:
				throw new RuntimeException("No such product " + name);
		}
	}
}
