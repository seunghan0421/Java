package java8.stream.dsl;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

public class GroupingBuilder<T, D, K> {
	private final Collector<? super T, ?, Map<K, D>> collector;

	private GroupingBuilder(final Collector<? super T, ?, Map<K, D>> collector) {
		this.collector = collector;
	}

	public Collector<? super T, ?, Map<K, D>> get() {
		return collector;
	}

	public <J> GroupingBuilder<T, Map<K, D>, J> after(Function<? super T, ? extends J> classfier) {
		return new GroupingBuilder<>(groupingBy(classfier, collector));
	}

	public static <T, D, K> GroupingBuilder<T, List<T>, K> groupOn(Function<? super T, ? extends K> classifier) {
		return new GroupingBuilder<>(groupingBy(classifier));
	}
}
