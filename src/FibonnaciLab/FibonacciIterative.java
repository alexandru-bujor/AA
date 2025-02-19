package FibonnaciLab;
import java.util.Scanner;

public class FibonacciIterative {
    public static int fibIterative(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int prev2 = 0, prev1 = 1, result = 0;
        for (int i = 2; i <= n; i++) {
            result = prev1 + prev2;
            prev2 = prev1;
            prev1 = result;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        System.out.println("Fibonacci number: " + fibIterative(n));
    }
}
