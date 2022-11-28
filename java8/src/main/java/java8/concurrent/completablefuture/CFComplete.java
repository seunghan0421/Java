package java8.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFComplete {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		int x = 1337;

		CompletableFuture<Integer> a = new CompletableFuture<>();
		executorService.submit(() -> a.complete(f(x)));
		int b = g(x);
		System.out.println(a.get() + b);

		executorService.shutdown();
	}
}
