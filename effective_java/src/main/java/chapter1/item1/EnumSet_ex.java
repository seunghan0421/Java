package chapter1.item1;

import java.util.Calendar;
import java.util.EnumSet;

public class EnumSet_ex {

	public enum TestValue {
		ONE, TWO, THREE
	}

	public static void main(String[] args) {
		//이렇게 정적 팩터리 메서드를 통해 인스턴스를 생성할 수 있다.
		EnumSet.of(TestValue.ONE, TestValue.TWO, TestValue.THREE);
		Calendar instance = Calendar.getInstance();
	}
}
