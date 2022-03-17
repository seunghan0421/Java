package chapter6.item34;

import static chapter6.item34.PayrollDay.PayType.*;

// 전략 열거 타입 패턴 - 열거 타입 상수 일부가 같은 동작을 공유한다면 사용해라!! 기억해!!
public enum PayrollDay {
	MONDAY(WEEKDAY) , TUESDAY(WEEKDAY), WEDNESDAY(WEEKDAY) , THURSDAY(WEEKDAY),
	FRIDAY(WEEKDAY), SATURDAY(WEEKEND), SUNDAY(WEEKEND);

	private final PayType paytype;

	PayrollDay(PayType paytype) {
		this.paytype = paytype;
	}

	int pay(int minutesWorked, int payRate){
		return paytype.pay(minutesWorked, payRate);
	}

	enum PayType {
		WEEKDAY {
			int overtimePay(int minsWorked, int payRate){
				return minsWorked <= MINS_PER_SHIFT ? 0 : (minsWorked - MINS_PER_SHIFT) * payRate / 2;
			}
		},
		WEEKEND {
			int overtimePay(int minsWorked, int payRate){
				return minsWorked * payRate / 2;
			}
		};

		abstract int overtimePay(int mins, int payRate);
		private static final int MINS_PER_SHIFT = 8*60;

		int pay (int minsWorked, int payRate) {
			int basePay = minsWorked * payRate;
			return basePay + overtimePay(minsWorked, payRate);
		}
	}


}
