package chapter1.item1;

/**
 * 정적 퍅토리 메서드는
 * 1. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있따.
 * 2. 입력 매개 변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
 *
 */
public class GameCalculator {
	static Grade of(int score){
		if(score >= 50){
			return new A();
		}

		return new B();
	}
}

interface Grade {
	String toText();
}

class A implements Grade {

	@Override
	public String toText() {
		return "A";
	}
}

class B implements Grade {

	@Override
	public String toText() {
		return "B";
	}
}


