package java8.concurrent.chapter15.excel;

import java.util.concurrent.Flow;

public interface Publisher<T> {
	void subscribe(Subscriber<? super T> subscriber);
}
