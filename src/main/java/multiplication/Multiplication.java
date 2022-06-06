package multiplication;

import java.util.ArrayList;
import java.util.Collections;

public class Multiplication {
	
	public static void main(String[] args) {
		
		System.out.println(multiply("123", "10000"));
		
//		predicted : 51452830760
//		calculated: 51452830760
	}
	
	public static String multiply(String num1, String num2) {
		try{
			//if the first character of the num1 is not minus sign and is not a digit -> throw exception
			if(num1.charAt(0) != '-' && !Character.isDigit(num1.charAt(0))){
				throw new NonNumericalValueException();
			}
			
			//if the first character of the num2 is not minus sign and is not a digit -> throw exception
			if(num2.charAt(0) != '-' && !Character.isDigit(num2.charAt(0))){
				throw new NonNumericalValueException();
			}
			
			//if one of the chars of num1 from index 1 is not a digit -> throw exception
			for(char c : num1.substring(1).toCharArray()) {
				if(!Character.isDigit(c)) throw new NonNumericalValueException();
			}
			
			//if one of the chars of num2 from index 1 is not a digit -> throw exception
			for(char c : num2.substring(1).toCharArray()) {
				if(!Character.isDigit(c)) throw new NonNumericalValueException();
			}
			
		}catch(NonNumericalValueException e) {
			System.out.println("The arguments contain invalid character(s) - non-numerical value");
			System.exit(1);
		}
		
		boolean positive = true;
		
		if(num1.charAt(0) == '-' && num2.charAt(0) == '-') {
			return karatsuba(num1.substring(1), num2.substring(1));
		}
		else if(num1.charAt(0) == '-' && num2.charAt(0) != '-') {
			return "-"+karatsuba(num1.substring(1), num2);
		}
		else if(num1.charAt(0) != '-' && num2.charAt(0) == '-') {
			return "-"+karatsuba(num1, num2.substring(1));
		}
		else {
			return karatsuba(num1, num2);
		}
		
	}
	
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
		
		/*
		 * first we need to make sure we are applying karatsuba algorithm to equal sized 
		 * (in terms of digit number) numbers - split the larger number if needed and perform 
		 * separate karatsuba multiplication on these parts
		*/
		if(num1.length() > num2.length()) {
//			result = chunkify(num1, num2);
//			System.out.println("chunk: "+result);
			return chunkify(num1, num2);
			
		}
		else if(num2.length() > num1.length()) {
//			result = chunkify(num2, num1);
//			System.out.println("chunk: "+result);
			return chunkify(num2,num1);
		}
		else {

			// base case
			if(num1.length() == 1 && num2.length() == 1) {
				return Integer.toString(Integer.parseInt(num1) * Integer.parseInt(num2));
			}

			//calculate length the numbers -> they are the same length
			int numLength = num1.length();

			// substring point
			int halfLengthString = numLength / 2;

			//store the half length
			int halfLength = halfLengthString + (numLength % 2);


			// a => left part of num1
			String a = num1.substring(0, halfLengthString);
			// b => right part of num1
			String b = num1.substring(halfLengthString, numLength);
			// c => left part of num2
			String c = num2.substring(0, halfLengthString);
			// d => right part of num2
			String d = num2.substring(halfLengthString, numLength);


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
	}
	
	/**
	 * This method is executed if there there is a multiplication of two number with different
	 * number of digits. It splits the larger number into n/m chunks, each with m digits. 
	 * Then the smaller number is multiplied by each chunk using Karatsuba’s algorithm, and then
	 * the resulting partial products are summed with appropriate shifts.
	 * @param larger number with larger number of digits
	 * @param smaller number with smaller number of digits
	 * @return the result of multiplication of the two numbers
	 */
	public static String chunkify(String larger, String smaller) {
//		System.out.println(larger +" ; " +smaller);
		/*
		 * this splits string into strings of length smaller.length() 
		 * (the last string might be smaller, but that is going to be handled in the next recursion)
		 */
		String str_chunks[] = larger.split("(?<=\\G.{"+smaller.length()+"})");
		ArrayList<String> mul_chunks = new ArrayList<String>();
		
		
		//this variable keeps track of the powers so that we can than build back the number
		int power = larger.length();
		for(int i=0; i<str_chunks.length; i++) {
			
			power -= smaller.length();
			if(power < 0) power = 0;
			
			
			String temp = karatsuba(str_chunks[i], smaller);
			/*
			 * here we are adding the zeros based on the power variable
			 * num1 * 10^power -> n=power:  num1 with n zeros
			 */
			temp += String.join("", Collections.nCopies(power, "0"));
			
			mul_chunks.add(temp);
			
			
			
		}
		// sum all the partial products into one result
		String result = sumTwoNums(mul_chunks.get(0),mul_chunks.get(1));
		for(int i=2; i<mul_chunks.size(); i++) {
			result = sumTwoNums(mul_chunks.get(i), result);
		}
		return result;
	}
	
	/**
	 * This method sums two (positive) numbers. These are passed as Strings, which is also the return
	 * type.
	 * @param num1 first addent
	 * @param num2 second addent
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
	
	/**
	 * This method returns the num1 subtracted by num2. These numbers are passed as Strings, 
	 * which is also the return type.
	 * @param num1 minuend
	 * @param num2 subtrahend
	 * @return difference
	 */
	public static String diffTwoNums(String num1, String num2) {
		
		// first make sure the 1 is the longer one
		if (num1.length() < num2.length()){
			String temp = num1;
			num1 = num2;
			num2 = temp;
	    }
		//determine the lengths of the numbers
		int n1 = num1.length(), n2 = num2.length();
				
		//find the difference in length
		int diff = n1 - n2;
				
		//set the number that gets transfered to the next summation
		int transfer = 0;
				
				
		//define an empty string
		String str = "";
		
		//start from end and sub the individual numbers
		//basic difference that we all know from 2nd grade
		for (int i = n2 - 1; i >=0; i--) {
					
			//
			int sub = (((int)num1.charAt(i + diff) - (int)'0')
					- ((int)num2.charAt(i) - (int)'0')
					- transfer);
			if (sub < 0) {
            	sub = sub + 10;
            	transfer = 1;
        	}
        	else {
        		transfer = 0;
        	}

        	str += (char)(sub + '0');
		}
		
		// sub the rest of the string
		for (int i = n1 - n2 - 1; i >= 0; i--) {
            if (num1.charAt(i) == '0' && transfer > 0) {
                str += "9";
                continue;
            }
            int sub = (((int)num1.charAt(i) - (int)'0') - transfer);
            
            if (i > 0 || sub > 0) { // remove preceding 0's
            	str += (char)(sub + '0');
            }
                
            transfer = 0;
        }
		
		// reverse resultant String
		String result = new StringBuilder(str).reverse().toString();
		
		//return string without leading zeros
        return result.replaceFirst("^0+(?!$)", "");
	}
}
