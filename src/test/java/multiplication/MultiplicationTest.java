package multiplication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplicationTest {

	/*
	 * Testing of subtraction of numbers as strings
	 */
	@Test
	void _1234567890_minus_1111111111_shouldEqual_123456779() {
		assertEquals("123456779", Multiplication.diffTwoNums("1234567890", "1111111111"));
	}
	@Test
	void _101_minus_5_shouldEqual_96(){
		assertEquals("96", Multiplication.diffTwoNums("101", "5"));
	}
	@Test
	void _788_minus_599_shouldEqual_189(){
		assertEquals("189", Multiplication.diffTwoNums("788", "599"));
	}

	/*
	 * Testing of addition of numbers as strings
	 */
	@Test
	void _999_plus_2_shouldEqual_1001(){
		assertEquals("1001", Multiplication.sumTwoNums("999", "2"));
	}
	@Test
	void _1111_plus_2222_shouldEqual_1001(){
		assertEquals("3333", Multiplication.sumTwoNums("1111", "2222"));
	}
	@Test
	void _555_plus_555_shouldEqual_1110(){
		assertEquals("1110", Multiplication.sumTwoNums("555", "555"));
	}

	/*
	 * Testing the multiply method
	 */
	@Test
	void fiveTimesThousandShouldEqual5k(){
		assertEquals("5000", Multiplication.multiply("5", "1000"));
	}
	@Test
	void _12345678901234567890_times_11111111111111111111_shouldEqual_137174210013717420998628257899862825790(){
		assertEquals("137174210013717420998628257899862825790", Multiplication.multiply("12345678901234567890", "11111111111111111111"));
	}
	@Test
	void _146123_times_352120_shouldEqual_51452830760(){
		assertEquals("51452830760", Multiplication.multiply("146123", "352120"));
	}
	@Test
	void _146123_times_minus352120_shouldEqual_minus51452830760(){
		assertEquals("-51452830760", Multiplication.multiply("146123", "-352120"));
	}
	@Test
	void _minus146123_times_352120_shouldEqual_minus51452830760(){
		assertEquals("-51452830760", Multiplication.multiply("-146123", "352120"));
	}
	@Test
	void _minus146123_times_minus352120_shouldEqual_51452830760(){
		assertEquals("51452830760", Multiplication.multiply("-146123", "-352120"));
	}






}
