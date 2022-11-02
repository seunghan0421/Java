package java8.stream.util;

import java.util.List;

public class PrimeValidator {

	public static boolean isPrime(List<Integer> primes, int candidate){
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return primes.stream()
			.takeWhile(i -> i <= candidateRoot)
			.noneMatch(i -> candidate % i == 0);
	}
}
