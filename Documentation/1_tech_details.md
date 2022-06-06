# Environment setup

* IntelliJ IDEA 2022.1 (Ultimate Edition), Runtime version: 11.0.14.1+1-b2043.25 aarch64 Build #IU-221.5080.210


* JDK 17 (Zulu)
* Maven 4.0.0
* JUnit-jupiter 5.9.0-M1

## Download and run

Download the project as ZIP or clone the repository to your local machine. After that either open the project in IntelliJ IDEA and run the main method inside the Main.java file. In order to get the result click Run at top of the screen. Then click Edit Configurations...
Application -> Main 
There is a Program arguments field in the section Build and run where you can type the arguments.


Another option is to run the jar file. For that open terminal (cmd) at folder out/artifacts/multiplication_jar. Then type java -jar multiplication.jar followed by the arguments

## Arguments
First argument determines which implementation is going to be used. To use the custom implementation type "alg1", to use the BigInteger implementation type "alg2".
Second and third arguments are the numbers to be multiplied. You can pass in positive as well as negative numbers.

Valid argument examples: 
* alg1 112345 -447984
* alg2 112345 -447984
* alg1 -21341 987
* alg2 -21441 4356
* alg1 -181692 11111
  
Invalid argument examples:
* alg 123 123
* alg3 123 123
* alg1 1
* alg1
* alg1 --31 123
* alg2 abcd xyz