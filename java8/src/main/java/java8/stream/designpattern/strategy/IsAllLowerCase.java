package java8.stream.designpattern.strategy;

public class IsAllLowerCase implements ValidationStrategy{

	@Override
	public boolean excute(final String s) {
		return s.matches("[a-z]+");
	}
}
