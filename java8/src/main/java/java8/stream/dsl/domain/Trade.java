package java8.stream.dsl.domain;

public class Trade {
	public enum Type {BUY, SELL}

	private Type type;

	private Stock stock;
	private int quantity;
	private double price;

	public Type getType() {
		return type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(final Stock stock) {
		this.stock = stock;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public double getValue(){
		return quantity * price;
	}
}
