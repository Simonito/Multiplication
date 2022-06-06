package multiplication;

public class Main {

	public static void main(String[] args) {
		try {
			if(args[0].equals("alg1")) {
				System.out.println(Multiplication.multiply(args[1], args[2]));
			}
			else if(args[0].equals("alg2")) {
				System.out.println(MultiplicationBigIntegers.multiply(args[1], args[2]));
			}
			else {
				System.out.println("Invalid first argument (valid options are: alg1, alg2)");
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Insufficient number of arguments");
			System.exit(1);
		}
		
	}

}
