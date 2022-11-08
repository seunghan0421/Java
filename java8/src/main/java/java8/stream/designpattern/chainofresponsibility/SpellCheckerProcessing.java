package java8.stream.designpattern.chainofresponsibility;

public class SpellCheckerProcessing extends ProcessingObject<String>{

	@Override
	protected String handleWork(final String text) {
		return text.replaceAll("labda","lambda");
	}
}
