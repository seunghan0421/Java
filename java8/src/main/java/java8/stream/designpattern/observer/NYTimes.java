package java8.stream.designpattern.observer;

public class NYTimes implements Observer{

	@Override
	public void notify(final String tweet) {
		if(tweet != null && tweet.contains("money")){
			System.out.println("Breaking news in NY!" + tweet);
		}
	}
}
