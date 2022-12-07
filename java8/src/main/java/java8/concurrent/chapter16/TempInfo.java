package java8.concurrent.chapter16;

import java.util.Random;

public class TempInfo {

	public static final Random random = new Random();

	private final String town;
	private final int temp;

	public TempInfo(final String town, final int temp) {
		this.town = town;
		this.temp = temp;
	}

	public static TempInfo fetch(String town){
		if(random.nextInt(10) == 0){
			throw new RuntimeException("Error!"); // 10분의 1 확률로 온도 가져오기 실패
		}

		return new TempInfo(town, random.nextInt(100));
	}

	@Override
	public String toString() {
		return "TempInfo{" +
			"town='" + town + '\'' +
			", temp=" + temp +
			'}';
	}

	public String getTown() {
		return town;
	}

	public int getTemp() {
		return temp;
	}
}
