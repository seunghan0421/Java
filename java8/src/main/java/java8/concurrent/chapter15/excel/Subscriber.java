package java8.concurrent.chapter15.excel;

public interface Subscriber<T> {
	void onNext(T t);
}
