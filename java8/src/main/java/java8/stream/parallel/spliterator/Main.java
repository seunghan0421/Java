package java8.stream.parallel.spliterator;

import java.util.stream.Stream;

public class Main {

	public int countWords(Stream<Character> stream) {
		return stream.reduce(
				new WordCounter(0, true),
				WordCounter::accumulate,
				WordCounter::combine)
			.getCounter();
	}
}
