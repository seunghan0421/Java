package java8.concurrent.chapter15.excel;

import java.util.ArrayList;
import java.util.List;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
	private int value = 0;
	private String name;
	private List<Subscriber> subscribers = new ArrayList<>();

	public SimpleCell(final String name) {
		this.name = name;
	}

	@Override
	public void subscribe(final Subscriber<? super Integer> subscriber) {
		subscribers.add(subscriber);
	}

	private void notifyAllSubscribers() {
		subscribers.forEach(subscriber -> subscriber.onNext(this.value));
	}

	@Override
	public void onNext(final Integer newValue) {
		this.value = newValue;
		System.out.println(this.name + " : " + this.value);
		notifyAllSubscribers();
	}

}
