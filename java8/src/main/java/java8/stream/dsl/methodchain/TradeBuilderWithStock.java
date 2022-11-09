package java8.stream.dsl.methodchain;

import java8.stream.dsl.domain.Trade;

public class TradeBuilderWithStock {
	private final MethodChainingOrderBuilder builder;
	private final Trade trade;

	public TradeBuilderWithStock(final MethodChainingOrderBuilder builder, final Trade trade) {
		this.builder = builder;
		this.trade = trade;
	}

	public MethodChainingOrderBuilder at(double price){
		trade.setPrice(price);
		return builder.addTrade(trade);
	}
}
