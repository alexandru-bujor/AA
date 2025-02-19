package FibonnaciLab;

import java.util.Scanner;

public class FibonacciMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt(); // Read input from user

       int result2 = (int) FibonacciMathFormula.fibonacci(n);
        System.out.println("Fibonacci(" + n + ") = " + result2); // Display result



        scanner.close();
    }
}
