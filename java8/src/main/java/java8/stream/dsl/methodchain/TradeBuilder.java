package java8.stream.dsl.methodchain;

import java8.stream.dsl.domain.Trade;

public class TradeBuilder {
	private final MethodChainingOrderBuilder builder;
	private final Trade trade = new Trade();

	private TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity){
		this.builder = builder;
		trade.setType(type);
		trade.setQuantity(quantity);
	}

	public StockBuilder stock(String symbol){
		return new StockBuilder(builder, trade, symbol);
	}
}
