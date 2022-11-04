package java8.stream.parallel.spliterator;

public class WordCounter {
	private final int counter;
	private final boolean lastSpace;

	public WordCounter(final int counter, final boolean lastSpace) {
		this.counter = counter;
		this.lastSpace = lastSpace;
	}

	public WordCounter accumulate(Character c) {
		if (Character.isWhitespace(c)) {
			return lastSpace ? this : new WordCounter(counter, true);
		} else {
			return lastSpace ? new WordCounter(counter + 1, false) : this;
		}
	}

	public WordCounter combine(WordCounter wordCounter) {
		return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
	}

	public int getCounter() {
		return counter;
	}
}
