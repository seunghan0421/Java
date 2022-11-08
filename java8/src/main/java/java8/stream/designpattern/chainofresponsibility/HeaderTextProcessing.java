package java8.stream.designpattern.chainofresponsibility;

public class HeaderTextProcessing extends ProcessingObject<String>{

	@Override
	protected String handleWork(final String text) {
		return "From Raoul, Mario and Alan " + text ;
	}
}
