package multiplication;

import java.util.Collections;

public class Multiplication {
	
	
	/**
	 * Recursive method that takes two integer numbers (any number of digits) and returns the product
	 * of their multiplication. It uses Karatsuba algorithm, which is more effective than classic 
	 * multiplication (multiplying each digit of one number with every number of other number) that 
	 * has time complexity O(nˆ2). This algorithm uses an approach named divide and conquer, which 
	 * makes this algorithm more effective and shrinks the time complexity of multiplication down to
	 * O(nˆ(log 3))
	 * @param num1 1st number, to be multiplied by 2nd
	 * @param num2 2nd number, to be multiplied by 1st
	 * @return the multiplication of the two parameters
	 */
	public static String karatsuba(String num1, String num2) {
		// base case
		if(num1.length() == 1 || num2.length() == 1) {
			return Integer.toString(Integer.parseInt(num1) * Integer.parseInt(num2));
		}
		
		//calculate length of each number
		int numOneLength = num1.length();
		int numTwoLength = num2.length();

		//find the longest number (in terms of number of digits)
		int maxLength = Math.max(numOneLength, numTwoLength);

		// substring point
		int halfLengthString = maxLength / 2;

		//store the half length
		int halfLength = halfLengthString + (maxLength % 2);


		// a => left part of num1
		String a = num1.substring(0, halfLengthString);
		// b => right part of num1
		String b = num1.substring(halfLengthString, numOneLength);
		// c => left part of num2
		String c = num2.substring(0, halfLengthString);
		// d => right part of num2
		String d = num2.substring(halfLengthString, numTwoLength);


		// a * c
		String ac = karatsuba(a, c);

		// b * d
		String bd = karatsuba(b, d);

		/*
		 * the karatsuba algorithm in order to be efficient compared to classical multiplication
		 * 	calculates K = [(a+b)*(c+d)] and from there calculate (a*d + b*c) as K - ac - bd
		 */
		String aPb_times_cPd = karatsuba(sumTwoNums(a,b), sumTwoNums(c, d));

		String ad_plus_bc = diffTwoNums(aPb_times_cPd, ac);
		ad_plus_bc = diffTwoNums(ad_plus_bc, bd);

		ac += String.join("", Collections.nCopies(2*halfLength, "0"));
		ad_plus_bc += String.join("", Collections.nCopies(halfLength, "0"));

		String res = sumTwoNums(ac, ad_plus_bc);
		res = sumTwoNums(res, bd);

		//return string without first leading zeros
		return res.replaceFirst("^0+(?!$)", "");
	}
	
	/**
	 * This method sums two (positive) numbers. These are passed as Strings, which is also the return
	 * type.
	 * @param num1 first enumerator
	 * @param num2 second enumerator
	 * @return sum of the parameters
	 */
	public static String sumTwoNums(String num1, String num2) {
		
		// first make sure the num2 is the longer one
		if (num1.length() > num2.length()){
	        String temp = num1;
	        num1 = num2;
	        num2 = temp;
	    }
		
		//determine the lengths of the numbers
		int n1 = num1.length(), n2 = num2.length();
		
		//find the difference in length
		int diff = n2 - n1;
		
		//set the number that gets transfered to the next summation
		int transfer = 0;
		
		
		//define an empty string
		String str = "";
		
		//start from end and sum the individual numbers
		//basic summation that we all know from 2nd grade
		for (int i = n1 - 1; i >=0; i--) {
			
			// make sure to "shift" which digit is being added from the shorter number
			int sum = ((int)(num1.charAt(i)-'0') +
		            (int)(num2.charAt(i+diff)-'0') + transfer);
			
			//add the sum result (the last digit and transfer the rest)
		    str += (char)(sum % 10 + '0');
		    //transfer the rest 
		    transfer = sum / 10;
		}
		
		//add the what remains from the longer number
		for (int i = n2 - n1 - 1; i >=0; i--) {
			int sum = ((int)(num2.charAt(i) - '0') + transfer);
	        str += (char)(sum % 10 + '0');
	        transfer = sum / 10;
		}
		
		// make sure to add remaining transfer
	    if (transfer > 0) {
	    	str += (char)(transfer + '0');
	    }
	    
	    // reverse resultant String
	    String result = new StringBuilder(str).reverse().toString();
	    
	    //return string without leading zeros
	    return result.replaceFirst("^0+(?!$)", "");
	}
}
