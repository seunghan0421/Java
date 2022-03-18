package chapter8.item50;

import java.util.Date;

public final class Period {
	private final Date start;
	private final Date end;

	/**
	 *
	 * @param start 시작 시간
	 * @param end 종료 시간
	 * @throws IllegalArgumentException 시작 시간이 종료 시간보다 늦을 때 발생한다
	 */
	public Period(Date start, Date end) {
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());

		if (this.start.compareTo(this.end) > 0) {
			throw new IllegalArgumentException(this.start + "가 " + this.end + "보다 늦다.");
		}
	}

	// 가변 필드의 방어적 복사본을 반
	public Date start() {
		return new Date(this.start.getTime());
	}

	public Date end() {
		return new Date(this.end.getTime());
	}
}
