package java8.stream.designpattern.strategy;

public class Validator {
	private final ValidationStrategy strategy;

	public Validator(ValidationStrategy strategy){
		this.strategy = strategy;
	}

	public boolean validate(String s){
		return strategy.excute(s);
	}

	public static void main(String[] args) {
		// 람다로 하기 전 이렇게 전략(Strategy) 패턴을 사용할 수 있음
		final Validator numericValidator = new Validator(new IsNumeric());
		boolean b1 = numericValidator.validate("aaaa"); // false 반환
		final Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
		final boolean b2 = lowerCaseValidator.validate("bbbb"); // true 반환

		// 람다 표현식을 이용하면 전략 디자인 패턴에서 발생하는 자잘한 코드를 제거할 수 있다.
		final Validator numericValidator1 = new Validator(s -> s.matches("\\d+"));
		boolean b11 = numericValidator1.validate("aaaa"); // false 반환
		final Validator lowerCaseValidator1 = new Validator(s -> s.matches("[a-z]+"));
		final boolean b21 = lowerCaseValidator1.validate("bbbb"); // true 반환
	}
}
