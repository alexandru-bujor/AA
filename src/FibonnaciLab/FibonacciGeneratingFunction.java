package FibonnaciLab;
import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciGeneratingFunction {
    public static BigInteger fibGeneratingFunction(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger[] F = new BigInteger[n + 1];
        F[0] = BigInteger.ZERO;
        F[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            F[i] = F[i - 1].add(F[i - 2]);
        }

        return F[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        System.out.println("Fibonacci number: " + fibGeneratingFunction(n));
    }
}
