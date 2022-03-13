package chapter1.item2;

/**
 * 빌더 패턴 - Builder Pattern
 * 클라이언트는 필요한 객체를 직접 만드는 대신, 필수 매개변수 만으로 생성자(혹은 정적 팩터리)를 호출해 빌더객체를 얻는다.
 * 계층적으로 설계된 클래스를 빌더 패턴으로 적용하는 것은 서적에 나와있으니 필요할 시 참고할 것
 */
public class PencilCase {
	private final int caseSize;

	private final int pen;
	private final int eraser;
	private final int pencil;

	public static class Builder {
		// 필수 매개변수
		private final int caseSize;

		// 선택 매개변수 - 기본값으로 초기화한다.
		private int pen = 0;
		private int eraser = 0;
		private int pencil = 0;

		public Builder(int caseSize) {
			this.caseSize = caseSize;
		}

		public Builder pens(int val) {
			this.pen = val;
			return this;
		}

		public Builder erasers(int val) {
			this.eraser = val;
			return this;
		}

		public Builder pencils(int val) {
			this.pencil = val;
			return this;
		}

		public PencilCase build() {
			return new PencilCase(this);
		}

	}

	private PencilCase(Builder builder) {
		caseSize = builder.caseSize;
		pen = builder.pen;
		eraser = builder.eraser;
		pencil = builder.pencil;
	}
}
