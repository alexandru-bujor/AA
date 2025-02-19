package FibonnaciLab;
import java.util.Scanner;

public class FibonacciMatrix {
    public static int fibMatrix(int n) {
        if (n == 0) return 0;
        int[][] F = {{1, 1}, {1, 0}};
        power(F, n - 1);
        return F[0][0];
    }

    private static void power(int[][] F, int n) {
        if (n == 0 || n == 1) return;
        int[][] M = {{1, 1}, {1, 0}};

        power(F, n / 2);
        multiply(F, F);

        if (n % 2 != 0) multiply(F, M);
    }

    private static void multiply(int[][] F, int[][] M) {
        int x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
        int y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
        int z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
        int w = F[1][0] * M[0][1] + F[1][1] * M[1][1];

        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        System.out.println("Fibonacci number: " + fibMatrix(n));
    }
}
