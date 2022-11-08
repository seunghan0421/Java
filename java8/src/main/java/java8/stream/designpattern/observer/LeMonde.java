package java8.stream.designpattern.observer;

public class LeMonde implements Observer{

	@Override
	public void notify(final String tweet) {
		if(tweet != null && tweet.contains("wine")){
			System.out.println("Today cheese, wine, and news " + tweet);
		}
	}
}
