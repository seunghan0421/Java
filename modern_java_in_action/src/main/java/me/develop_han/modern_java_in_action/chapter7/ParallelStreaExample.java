package me.develop_han.modern_java_in_action.chapter7;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.TearDown;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreaExample {
	private static final long N = 10_000_000L;

	/**
	 * 순차적 Sum을 반환하는 메서드
	 * @param n
	 */
	@Benchmark
	public long sequentialSum(long n) {
		return Stream.iterate(1L, I -> I + 1)
			.limit(n)
			.reduce(0L, Long::sum);
	}

	@TearDown(Level.Invocation)
	public void tearDown() {
		System.gc();
	}

	/**
	 * 병렬 스트림을 사용해 Sum을 반환하는 메서드
	 * @param n
	 */
	public long parallelSum(long n) {
		return Stream.iterate(1L, I -> I + 1)
			.limit(n)
			.parallel()
			.reduce(0L, Long::sum);
	}

	public static void main(String[] args) {

	}
}
