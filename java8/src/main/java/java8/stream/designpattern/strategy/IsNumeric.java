package java8.stream.designpattern.strategy;

public class IsNumeric implements ValidationStrategy{

	@Override
	public boolean excute(final String s) {
		return s.matches("\\d+");
	}
}
