package java8.stream.dsl.methodchain;

import java8.stream.dsl.domain.Stock;
import java8.stream.dsl.domain.Trade;

public class StockBuilder {
	private final MethodChainingOrderBuilder builder;
	private final Trade trade;
	private final Stock stock = new Stock();

	public StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol) {
		this.builder = builder;
		this.trade = trade;
		stock.setSymbol(symbol);
	}

	public TradeBuilderWithStock on(String market){
		stock.setMarket(market);
		trade.setStock(stock);
		return new TradeBuilderWithStock(builder, trade);
	}
}
