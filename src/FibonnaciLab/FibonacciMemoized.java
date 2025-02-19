package FibonnaciLab;
import java.util.*;

public class FibonacciMemoized {
    private static HashMap<Integer, Integer> memo = new HashMap<>();

    public static int fibMemoized(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo.containsKey(n)) return memo.get(n);

        int result = fibMemoized(n - 1) + fibMemoized(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        System.out.println("Fibonacci number: " + fibMemoized(n));
    }
}

