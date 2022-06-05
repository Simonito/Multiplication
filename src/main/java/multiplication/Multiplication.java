package multiplication;

public class Multiplication {
	
	
	public static String karatsuba(String num1, String num2) {
		// base case
		if(num1.length() == 1 || num2.length() == 1) {
			return Integer.toString(Integer.parseInt(num1) * Integer.parseInt(num2));
		}
	}
}
