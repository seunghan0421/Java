package java8.concurrent.shop.v1;

import static java.util.stream.Collectors.*;

import java.lang.reflect.Executable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class BestPriceFinder {

	private final List<Shop> shops = Arrays.asList(
		new Shop("BestPrice"),
		new Shop("LetsSaveBig"),
		new Shop("MyFavoriteShop"),
		new Shop("BuyItAll")
	);

	private final Executor executor = Executors.newFixedThreadPool(shops.size(), (Runnable r) -> {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	});

	public List<String> findPricesSequential(String product){
		return shops.stream()
			.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
			.collect(toList());
	}

	public List<String> findPricesParallel(String product){
		return shops.parallelStream()
			.map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
			.collect(toList());
	}

	public List<String> findPricesFuture(String product){
		List<CompletableFuture<String>> priceFutures =
			shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), executor))
				.collect(toList());

		return priceFutures.stream()
			.map(CompletableFuture::join)
			.collect(toList());
	}

}
