package java8.concurrent.chapter16;

import java.util.concurrent.Flow;

public class TempSubscriber implements Flow.Subscriber<TempInfo> {

	private Flow.Subscription subscription;

	@Override
	public void onSubscribe(final Flow.Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	@Override
	public void onNext(final TempInfo tempInfo) {
		System.out.println(tempInfo);
		subscription.request(1);
	}

	@Override
	public void onError(final Throwable throwable) {
		System.out.println(throwable.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println("Done");
	}
}
