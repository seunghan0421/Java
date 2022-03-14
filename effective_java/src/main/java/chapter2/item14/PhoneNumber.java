package chapter2.item14;

import static java.util.Comparator.*;

import java.util.Comparator;

public final class PhoneNumber implements Comparable<PhoneNumber> {
	private final short areaCode, prefix, lineNum;

	public PhoneNumber(short areaCode, short prefix, short lineNum) {
		this.areaCode = rangeCheck(areaCode, 999, "지역코드");
		this.prefix = rangeCheck(prefix, 999, "프리픽스");
		this.lineNum = rangeCheck(lineNum, 0000, "가입자 번호");
	}

	private static short rangeCheck(int val, int max, String arg) {
		if (val < 0 || val > max) {
			throw new IllegalArgumentException(arg + ": " + val);
		}
		return (short)val;
	}
	// equals 예시 하지만 google의 AutoValue 프레임워크를 사용하자
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof PhoneNumber)) {
			return false;
		}
		PhoneNumber pn = (PhoneNumber)o;
		return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
	}

	/*
	compareTo 메서드로 수행하는 동치성 검사도 equals 규약과 똑같이 반사성, 대칭성, 추이성을 충족해야 함ㅇ르 뜻한다.
	compareTo 메서드에서 관계 연산자 <와 >를 사용하는 이전 방식은 거추장스럽고 오류를 유발한다.
	-> 박싱된 기본 타입 클래스들에 정적메서드 compare를 사용하자 -> ex) Short.compare(a,b);
	*/
	// 기본 타입 필드가 여럿 일 떄의 비교자
	// @Override
	// public int compareTo(PhoneNumber phoneNumber) {
	// 	int result = Short.compare(areaCode, phoneNumber.areaCode);
	// 	if (result == 0) {
	// 		result = Short.compare(prefix, phoneNumber.prefix);
	// 		if (result == 0) {
	// 			result = Short.compare(lineNum, phoneNumber.lineNum);
	// 		}
	// 	}
	//
	// 	return result;
	// }


	// 자바 8 이상에서는 Comparator 인터페이스가 일련의 비교자 생성 메서드와 팀을 꾸려 메서드 연쇄 방식으로 비교자를 생성할 수 있게 되었다.
	public static final Comparator<PhoneNumber> COMPARATOR =
		comparingInt((PhoneNumber pn) -> pn.areaCode)
			.thenComparing(pn -> pn.prefix)
			.thenComparing(pn -> pn.lineNum);

	@Override
	public int compareTo(PhoneNumber phoneNumber) {
		return COMPARATOR.compare(this, phoneNumber);
	}
}
