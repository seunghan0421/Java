package java8.stream.designpattern.chainofresponsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Main {
	public static void main(String[] args) {
		// 람다 적용 전
		ProcessingObject<String> p1 = new HeaderTextProcessing();
		ProcessingObject<String> p2 = new SpellCheckerProcessing();

		p1.setSuccessor(p2);

		String result = p1.handle("Aren't labdas really sexy??");

		System.out.println(result);

		// 람다 적용 후
		UnaryOperator<String> headerProcessing =
			(String text) -> "From Raoul, Mario and Alan: " + text;
		UnaryOperator<String> spellCheckerProcessing =
			(String text) -> text.replaceAll("labda", "lambda");

		Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

		String result2 = pipeline.apply("Aren't labdas really sexy?!");
	}
}
