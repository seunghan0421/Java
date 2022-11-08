package java8.stream.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject{
	private final List<Observer> observers = new ArrayList<>();

	@Override
	public void registerObserver(final Observer o) {
		this.observers.add(o);
	}

	@Override
	public void notifyObservers(final String tweet) {
		observers.forEach(o -> o.notify(tweet));
	}

	public static void main(String[] args) {
		// 람다 사용 전
		Feed f = new Feed();
		f.registerObserver(new NYTimes());
		f.registerObserver(new Guardian());
		f.registerObserver(new LeMonde());
		f.notifyObservers("The Queen said her favorite book is Modern Java In Action");

		// 람다 적용 후
		f.registerObserver((String tweet) -> {
			if(tweet != null && tweet.contains("money")){
				System.out.println("Breaking news in NY!" + tweet);
			}
		});

		f.registerObserver((String tweet) -> {
			if(tweet != null && tweet.contains("queen")){
				System.out.println("Yet more news from London ..." + tweet);
			}
		});
	}
}
