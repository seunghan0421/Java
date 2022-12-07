package java8.concurrent.chapter16;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class TempSubscription implements Flow.Subscription {

	private static final ExecutorService executor = Executors.newSingleThreadExecutor();

	private final Flow.Subscriber<? super TempInfo> subscriber;
	private final String town;

	public TempSubscription(Flow.Subscriber<? super TempInfo> subscriber, String town) {
		this.subscriber = subscriber;
		this.town = town;
	}

	@Override
	public void request(long n) {
		executor.submit(() -> {
			for (long i = 0L; i < n; i++) {
				try {
					subscriber.onNext(TempInfo.fetch(town));
				} catch (Exception e) {
					subscriber.onError(e);
					break;
				}
			}
		});
	}

	@Override
	public void cancel() {
		subscriber.onComplete();
	}
}
