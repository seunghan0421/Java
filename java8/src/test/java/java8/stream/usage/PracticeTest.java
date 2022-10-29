package java8.stream.usage;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class PracticeTest {

	Practice practice = new Practice();

	@Test
	void P1Test() {
		List<Integer> transactions = Practice.solution1();

		assertThat(transactions).size().isEqualTo(2);
		assertThat(transactions).containsExactly(300, 400);
	}

	@Test
	void P2Test() {
		List<String> cities = Practice.solution2();

		assertThat(cities).size().isEqualTo(2);
		assertThat(cities).contains("Milan", "Cambridge");
	}

	@Test
	void P3Test() {
		List<String> traderInCambridge = Practice.solution3();

		assertThat(traderInCambridge).size().isEqualTo(3);
		assertThat(traderInCambridge).contains("Alan", "Brian", "Raoul");
	}

	@Test
	void P4Test() {
		List<String> sortedTraders = Practice.solution4();

		assertThat(sortedTraders).size().isEqualTo(4);
		assertThat(sortedTraders).containsExactly("Alan", "Brian", "Mario", "Raoul");
	}

	@Test
	void P5Test() {
		boolean isTraderInMilan = Practice.solution5();

		assertThat(isTraderInMilan).isTrue();
	}

	@Test
	void P7Test() {
		int min = Practice.solution7();

		assertThat(min).isEqualTo(1000);
	}

	@Test
	void P8Test() {
		int max = Practice.solution8();

		assertThat(max).isEqualTo(300);
	}
}
