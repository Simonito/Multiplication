package multiplication;

import java.math.BigInteger;

public class MultiplicationBigIntegers {
	public static void main(String[] args) {
		
		BigInteger num1 = new BigInteger("12345678901234567890");
		System.out.println(num1.multiply(new BigInteger("11111111111111111111")));
		
//		predicted : 51452830760
//		calculated: 51452830760
	}
}
