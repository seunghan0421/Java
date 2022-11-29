package java8.concurrent.chapter15.excel;

public class Main {

	public static void main(String[] args) {
		SimpleCell c1 = new SimpleCell("C1");
		SimpleCell c2 = new SimpleCell("C2");
		SimpleCell c3 = new SimpleCell("C3");

		c1.subscribe(c3);

		c1.onNext(10);
		c2.onNext(20);

		System.out.println("===========================");
		// "C3=C1+C2" 예제
		// 이거 제대로 동작 안함 다시 한번 살펴봐야 함
		ArithmeticCell ac1 = new ArithmeticCell("AC1");
		SimpleCell c4 = new SimpleCell("C4");
		SimpleCell c5 = new SimpleCell("C5");

		c4.subscribe(ac1::setLeft);
		c5.subscribe(ac1::setLeft);

		c4.onNext(10);
		c5.onNext(20);
		c4.onNext(15);
	}
}
