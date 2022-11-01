package java8.stream.collect.customize;

import static java.util.stream.Collector.Characteristics.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Stream<T>의 모든 요소를 List<T>로 수집하는 ToListCollector<T> 클래스
 *
 * T : 수집될 스트림 항목의 제너릭 형식
 * A : 누적자, 즉 수집 과정에서 중간 결과를 누적하는 객체의 형식
 * R : 수집 연산 결과 객체의 형식(항상 그런 것은 아니지만 대개 컬렉션 형식)
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	/**
	 * 수집 과정에서 빈 누적자 인스턴스를 만드는 파라미터가 없는 함수
	 * supplier 메서드는 빈 결과로 이루어진 Supplier를 반환해야 한다.
	 */
	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	/**
	 * 스트림에서 n번째 요소를 탐색할 때 두 인수, 즉 누적자(스트림의 첫 n-1개 항목을 수집한 상태)와 n번째 요소를 함수에 적용한다.
	 * accumulator 메서드는 리듀싱 연산을 수행하는 함수를 반환한다.
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	/**
	 * finisher 메서드는 스트림 탐색을 끝내고 누적자 객체를 최종 결과로 변환하면서 누적 과정을 끝낼 때 호출할 함수를 반환해야 한다.
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	/**
	 * combiner는 스트림의 서로 다른 서브파트를 병렬로 처리할 때 누적자가 이 결과를 어떻게 처리할지 정의한다.
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		};
	}

	/**
	 * Characteristics 메서드는 컬렉터의 연산을 정의하는 Characteristics 형식의 불변 집합을 반환한다.
	 * Characteristics는 스트림을 병렬로 리듀스 할 것인지 그리고 병렬로 리듀스한다면 어떤 최적화를 선택해야 할지 힌트를 제공한다.
	 * Characteristics는 다음 세 항목을 포함하는 열거형이다.
	 *
	 * UNORDERED : 리듀싱 결과는 스트림 요소의 방문 순서나 누적 순서에 영향을 받지 않는다.
	 * CONCURRENT :
	 * 다중 스레드에서 accumulator 함수를 동시에 호출할 수 있으며 이 컬렉터는 스트림의 병렬 리듀싱을 수행할 수 있다.
	 * 컬렛터의 플래그에 UNORDERED를 함께 설정하지 않았다면 데이터 소스가 정렬되어 있지 않은(즉 집합처럼 요소의 순서가 무의미한) 상황에서만 병렬 리듀싱을 할 수 있다.
	 * IDENTITY_FINISH :
	 * finish 메서드가 반환하는 함수는 단순히 identity를 적용할 뿐이므로 이를 생략할 수 있다.
	 * 따라서 리듀싱 과정의 최종 결과로 누적자 객체를 바로 사용할 수 있다. 또한 누적자 A를 결과 R로 안전하게 형변환 할 수 있다.
	 */
	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
	}
}
