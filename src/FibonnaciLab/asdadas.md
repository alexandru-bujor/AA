# Laboratory Work 1: Study and Empirical Analysis of Algorithms for Determining Fibonacci N-th Term

## Overview
This project presents the empirical analysis of four different algorithms for determining the N-th term of the Fibonacci sequence. The aim is to compare the performance of each algorithm based on their time complexity and evaluate their efficiency for different input sizes.

## Table of Contents
- [Analysis](#analysis) 
- [Objective](#objective) 
- [Tasks](#tasks) 
- [Theoretical Notes](#theoretical-notes)
- [Introduction](#introduction)
- [Comparison Metric](#comparison-metric)
- [Input Format](#input-format)
- [Algorithms Implemented](#algorithms-implemented)
    - [Recursive Method](#recursive-method)
    - [Memorized Recursion](#memorized-recursion)
    - [Iterative DP](#iterative-dp)
    - [Matrix Exponentiation](#matrix-exp)
    - [Binet’s Formula](#binet-formula-method)
    - [Generating Function](#generating-func)
- [Comparison Metric](#comparison-metric)
- [Results](#results)
- [Conclusion](#conclusion)

## Objective

Study and analyze different algorithms for determining the Fibonacci n-th term.

## Tasks

1. Implement at least 3 algorithms for determining Fibonacci n-th term;
2. Decide properties of input format that will be used for algorithm analysis;
3. Decide the comparison metric for the algorithms;
4. Analyze empirically the algorithms;
5. Present the results of the obtained data;
6. Deduce conclusions of the laboratory.

## Theoretical Notes

An alternative to mathematical analysis of complexity is empirical analysis. This may be useful for:

- Obtaining preliminary information on the complexity class of an algorithm;
- Comparing the efficiency of two (or more) algorithms for solving the same problems;
- Comparing the efficiency of several implementations of the same algorithm;
- Obtaining information on the efficiency of implementing an algorithm on a particular computer.

In the empirical analysis of an algorithm, the following steps are usually followed:

1. **Establish the Purpose of the Analysis:** Determine what the analysis will measure.
2. **Choose the Efficiency Metric:** This can be the number of executions of an operation or the time of execution of all or part of the algorithm.
3. **Establish Properties of the Input Data:** Define the properties of the input data, such as size or specific characteristics.
4. **Implement the Algorithm:** Write the algorithm in a programming language.
5. **Generate Multiple Sets of Input Data:** Prepare several sets of data for testing the algorithm.
6. **Run the Program:** Execute the algorithm for each input data set.
7. **Analyze the Results:** Process the obtained data and analyze it, calculating synthetic quantities such as mean and standard deviation, or plot a graph with pairs of points (problem size, efficiency measure).

The choice of the efficiency measure depends on the goal of the analysis. If the aim is to obtain information on the complexity class or check the accuracy of a theoretical estimate, the number of operations performed is useful. For assessing the behavior of an algorithm's implementation, execution time is more appropriate.

## Introduction

The Fibonacci sequence is the series of numbers where each number is the sum of the two preceding numbers. Mathematically, it can be described as:

- \( F(0) = 0 \)
- \( F(1) = 1 \)
- \( F(n) = F(n-1) + F(n-2) \) for \( n > 1 \)

For example, the Fibonacci sequence begins as:

`0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, ...`

The sequence is often attributed to Leonardo Fibonacci, an Italian mathematician from the 12th century, though there are claims that the sequence appears in earlier works.

In computer science, several methods have been developed to calculate Fibonacci numbers efficiently. These methods can be classified into four categories:

- **Recursive Methods**
- **Dynamic Programming Methods**
- **Matrix Power Methods**
- **Binet Formula Methods**

Each method can be implemented either naively or with optimization, which improves performance.

## Empirical Analysis

In this laboratory, the focus is on empirically analyzing 4 naïve algorithms for computing Fibonacci numbers.

## Comparison Metric

The comparison metric for this laboratory work will be the execution time of each algorithm, denoted as \( T(n) \), for calculating Fibonacci numbers.

## Input Format

Each algorithm will receive two series of Fibonacci numbers to calculate:

1. **Smaller Scope Input:** This set is designed for the recursive method with smaller Fibonacci numbers:
    - 5, 7, 10, 12, 15, 17, 20, 22, 25, 27, 30, 32, 35, 37, 40, 42, 45

2. **Larger Scope Input:** This series is designed to compare the performance of the other algorithms:
    - 501, 631, 794, 1000, 1259, 1585, 1995, 2512, 3162, 3981, 5012, 6310, 7943, 10000, 12589, 15849


## Algorithms Implementation

### Recursive Method
Recursivity is the most simple and most unreliable method, because if the number exceeds 50, it will be really slow.
#### Pseudocode:
    public static int fibRecursive(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibRecursive(n - 1) + fibRecursive(n - 2);
    }

### Memorized Recursion
The dynamic programming method optimizes the recursive method by storing previously computed Fibonacci terms in an array to avoid redundant calculations.

#### Pseudocode:
    public static int fibMemoized(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo.containsKey(n)) return memo.get(n);

        int result = fibMemoized(n - 1) + fibMemoized(n - 2);
        memo.put(n, result);
        return result;
    }
### Iterative DP
This method builds up the solution iteratively, starting from F(0) and F(1) and iteratively computing each subsequent Fibonacci number until reaching F(n)
### Pseudocode:
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
### Matrix Power Method
This method leverages matrix multiplication to calculate Fibonacci terms using the property of matrix exponentiation.

#### Pseudocode:
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

### Binet Formula Method
The Binet formula uses the Golden Ratio to directly compute the N-th Fibonacci number, but it has limitations in precision for large N due to rounding errors.

#### Pseudocode:
    public static double fibonacci(int n) {
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        double result = (1 / sqrt5) * (Math.pow(phi, n) - Math.pow(psi, n));

        return Math.round(result);
    }

## Comparison Metric
The performance of each algorithm is measured by the execution time (T(n)) required to calculate the Fibonacci N-th term. Time complexity is used as the main metric to compare the efficiency of each algorithm.

## Results
The algorithms were tested using two input series:
1. Small input series for the recursive method: (5, 7, 10, 12, 15, 17, 20, 22, 25, 27, 30, 32, 35, 37, 40, 42, 45)
2. Larger input series for comparing all methods: (501, 631, 794, 1000, 1259, 1585, 1995, 2512, 3162, 3981, 5012, 6310, 7943, 10000, 12589, 15849)

### Recursive Method Results
The recursive method shows exponential time complexity, with performance degrading significantly for larger values of N.

### Dynamic Programming Method Results
The dynamic programming method performs much better, exhibiting linear time complexity (T(n)).

### Matrix Power Method Results
The matrix power method performs well for large N, with a time complexity of O(log n).

### Binet Formula Method Results
The Binet formula is very fast but suffers from precision issues for large N, making it unreliable for values beyond N = 70.

## Conclusion
Through empirical analysis, it was observed that:
- The recursive method is inefficient and impractical for large inputs.
- The dynamic programming method offers the best balance of efficiency and accuracy for practical use.
- The matrix power method performs well for large inputs but is slower than dynamic programming.
- The Binet formula is the fastest but suffers from precision issues for large Fibonacci numbers.

Future improvements may include optimizing the Binet formula to handle larger values or further optimizing matrix power multiplication techniques.
## Comparison of Algorithms

| **Algorithm**               | **Time Complexity (T(n))** | **Space Complexity (S(n))** | **Remarks**                                  |
|-----------------------------|----------------------------|-----------------------------|----------------------------------------------|
| **Recursive (Naïve)**        | O(2^n)                     | O(n)                        | 🔴 **Very slow** for large `n`                |
| **Memoized Recursion**       | O(n)                       | O(n)                        | ✅ **Fast** but uses extra memory            |
| **Iterative DP**             | O(n)                       | O(1)                        | ✅ **Best for small/moderate `n`**            |
| **Matrix Exponentiation**    | O(log n)                   | O(1)                        | ⚡ **Best for large `n`**                    |
| **Binet’s Formula**          | O(1)                       | O(1)                        | ⚡ **Instant** but may be inaccurate for large `n` |
| **Generating Function**      | O(n)                       | O(n)                        | ✅ **Best for very large `n`**               |

---

Elaborated by FAF-231 Student: 

Bujor Alexandru
