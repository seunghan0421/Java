package java8.stream.parallel.forkjoinexample;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class Main {
	public static void main(String[] args) {
		long[] numbers = LongStream.rangeClosed(1, 10).toArray();
		ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
		System.out.println(new ForkJoinPool().invoke(task));
	}
}
