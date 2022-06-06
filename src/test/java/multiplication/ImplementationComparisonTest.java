package multiplication;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ImplementationComparisonTest {

    /*
     * It is not recommended to use random values inside unit test because the tests are not reproducible,
     * however in this particular example we are evaluating whether the two implementations produce the same result.
     * By using random numbers we are making sure that the result is not manipulated by subjective judgement
     */
    @Test
    void customImplementationMultiplication_comparedToBigIntegerMultiplication(){
        int determineNegation = new Random().nextInt();
        BigInteger num1 = new BigInteger(64, new Random());
        BigInteger num2 = new BigInteger(64, new Random());

        if(determineNegation % 2 == 0) num1.negate();
        if(determineNegation % 3 == 0) num2.negate();

        assertEquals(MultiplicationBigIntegers.multiply(num1.toString(), num2.toString()),
                Multiplication.multiply(num1.toString(), num2.toString()));
    }

    /*
     * Some more test that evaluate whether the two implementation produce the same result. This time
     * without the use of random numbers. Here the negative number multiplication is tested too.
     */
    // CIM - Custom Implementation Multiplication
    // BIM - Big Integer Multiplication
    @Test
    void CIM_comparedTo_BIM_100_times_123(){
        assertEquals(MultiplicationBigIntegers.multiply(new BigInteger("100").toString(), new BigInteger("123").toString()),
                Multiplication.multiply("100", "123"));
    }
    @Test
    void CIM_comparedTo_BIM_minus100_times_123(){
        assertEquals(MultiplicationBigIntegers.multiply(new BigInteger("-100").toString(), new BigInteger("123").toString()),
                Multiplication.multiply("-100", "123"));
    }
    @Test
    void CIM_comparedTo_BIM_100_times_minus123(){
        assertEquals(MultiplicationBigIntegers.multiply(new BigInteger("100").toString(), new BigInteger("-123").toString()),
                Multiplication.multiply("100", "-123"));    }
    @Test
    void CIM_comparedTo_BIM_minus100_times_minus123(){
        assertEquals(MultiplicationBigIntegers.multiply(new BigInteger("-100").toString(), new BigInteger("-123").toString()),
                Multiplication.multiply("-100", "-123"));    }
}