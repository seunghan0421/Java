package chapter2.item10;

import java.math.BigDecimal;

import com.google.auto.value.AutoValue;

/**
 * AutoValue는 코드 자동 생성 라이브러리로 자바 코드를 줄일 수 있으며,
 * AutoValue는 Reflection을 이용한 런타임 방식이 아닌 apt를 이용하여 컴파일 타임에 코드를 생성해주기 때문에 성능상에 불이익은 없다.
 *
 * AutoValue는 불변정보(Immutable)를 전달하는 클래스 인스턴스를 생성할때 사용할 수 있다.
 * AutoValue_Product를 보면 final 클래스이면서 변수도 final 인 것을 볼 수 있다.
 * 그러므로, AutoValue는 멤버 변수 값이 항상 바뀔 수 있는 인스턴스에서는 사용하면 안된다.
 */
@AutoValue
public abstract class Product {
	public abstract String name();
	public abstract BigDecimal price();


	@AutoValue.Builder
	public  abstract  static class Builder{
		public abstract Builder name(String name);
		public abstract Builder price(BigDecimal price);
		public abstract Product build();
	}

	public static Product.Builder builder(){
		return new AutoValue_Product.Builder();
	}

}
