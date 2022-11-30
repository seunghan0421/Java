package java8.concurrent.shop.v1;

import static java8.concurrent.shop.util.Util.*;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
	private final String name;
	private final Random random;

	public Shop(final String name) {
		this.name = name;
		random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
	}

	public double getPrice(String product) {
		return calculatePrice(product);
	}

	private double calculatePrice(String product) {
		delay();
		return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
	}

	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			try {
				double pricee = calculatePrice(product);
				futurePrice.complete(pricee);
			} catch (Exception ex) {
				futurePrice.completeExceptionally(ex);
			}
		}).start();

		return futurePrice;
	}

	public String getName() {
		return name;
	}
}
