package java8.stream.dsl;

import static java8.stream.dsl.methodchain.MethodChainingOrderBuilder.*;
import static java8.stream.dsl.nestedfunction.NestedFunctionOrderBuilder.*;

import java8.stream.dsl.domain.Order;
import java8.stream.dsl.domain.Stock;
import java8.stream.dsl.domain.Trade;

public class Main {

	public static void main(String[] args) {
		// dsl 적용 전 - 매우 장황하다.
		Order order = new Order();
		order.setCustomer("Big Bank");

		Trade trade1 = new Trade();
		trade1.setType(Trade.Type.BUY);

		Stock stock1 = new Stock();
		stock1.setSymbol("IBM");
		stock1.setMarket("NYSE");

		trade1.setStock(stock1);
		trade1.setPrice(125.00);
		trade1.setQuantity(80);
		order.addTrade(trade1);

		Trade trade2 = new Trade();
		trade2.setType(Trade.Type.BUY);

		Stock stock2 = new Stock();
		stock2.setSymbol("GOOGLE");
		stock2.setMarket("NASDAQ");

		trade2.setStock(stock2);
		trade2.setPrice(375.00);
		trade2.setQuantity(50);
		order.addTrade(trade2);

		// 메서드 체인 방식 dsl
		forCustomer("BigBank")
			.buy(80)
			.stock("IBM")
			.on("NYSE")
			.at(125.00)
			.sell(50)
			.stock("GOOGLE")
			.on("NASDAQ")
			.at(375.00)
			.end();

		// 중첩된 함수 dsl
		// order("BigBank",
		// 	buy(80, stock("IBM", on("NYSE")), at(125.00))),
		// 	sell(50, stock("GOOGLE", on("NASDAQ")), at(375.00));


	}
}
