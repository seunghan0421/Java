package java8.concurrent.shop;

import static java8.concurrent.shop.util.Util.*;

import java8.concurrent.shop.util.Util;

public class ExchangeService {

	public static final double DEFAULT_RATE = 1.35;

	public enum Money {
		USD(1.0), EUR(1.35387), GBP(1.69715), CAD(0.92106), MXN(0.07683);

		private final double rate;

		Money(final double rate) {
			this.rate = rate;
		}
	}

	public static double getRate(Money source, Money destination) {
		return getRateWithMoney(source, destination);
	}

	private static double getRateWithMoney(Money source, Money destination) {
		delay();
		return destination.rate / source.rate;
	}
}
