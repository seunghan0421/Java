package java8.stream.dsl.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private String customer;
	private List<Trade> trades = new ArrayList<>();

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(final String customer) {
		this.customer = customer;
	}

	public void addTrade(Trade trade){
		trades.add(trade);
	}

	public double getValue(){
		return trades.stream().mapToDouble(Trade::getValue).sum();
	}
}
