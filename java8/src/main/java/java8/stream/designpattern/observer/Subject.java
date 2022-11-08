package java8.stream.designpattern.observer;

public interface Subject {
	void registerObserver(Observer o);
	void notifyObservers(String tweet);
}
