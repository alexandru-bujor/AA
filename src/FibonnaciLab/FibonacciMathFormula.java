package FibonnaciLab;

public class FibonacciMathFormula {
    public static double fibonacci(int n) {
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        double result = (1 / sqrt5) * (Math.pow(phi, n) - Math.pow(psi, n));

        return Math.round(result);
    }
}
