# Laboratory Work 1: Study and Empirical Analysis of Algorithms for Determining Fibonacci N-th Term

## Overview
This project presents the empirical analysis of four different algorithms for determining the N-th term of the Fibonacci sequence. The aim is to compare the performance of each algorithm based on their time complexity and evaluate their efficiency for different input sizes.

## Table of Contents
- [Objective](#objective)
- [Tasks](#tasks)
- [Theoretical Notes](#theoretical-notes)
- [Introduction](#introduction)
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
- [Comparison of Algorithms](#comparison-of-algorithms)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## Objective
The main objective of this laboratory work is to study and analyze different algorithms for determining the Fibonacci N-th term and compare their efficiency using empirical analysis.

## Tasks
1. Implement at least 3 algorithms for determining Fibonacci N-th term.
2. Decide properties of input format for algorithm analysis.
3. Define the comparison metric for the algorithms.
4. Analyze the algorithms empirically.
5. Present the results.
6. Draw conclusions based on the results.

## Theoretical Notes
The empirical analysis of algorithms is used to gain insights into their time complexity and efficiency. The performance of the algorithms is measured by recording the time taken for their execution on various input sizes.

## Introduction
The Fibonacci sequence is defined as each term being the sum of the two preceding terms. It starts as follows:
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, ...

Several methods exist to calculate the N-th term of the sequence, including:
- Recursive Method
- Dynamic Programming Method
- Matrix Power Method
- Binet Formula Method

## Algorithms Implemented

### Recursive Method
Recursivity is the most simple and most unreliable method, because if the number exceeds 50, it will be really slow.
#### Pseudocode:

public static int fibRecursive(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    return fibRecursive(n - 1) + fibRecursive(n - 2);
}
Memorized Recursion
The dynamic programming method optimizes the recursive method by storing previously computed Fibonacci terms in an array to avoid redundant calculations.

Pseudocode:
shell
Copiază
Editează
Fibonacci(n): 
    Array A; 
    A[0] <- 0; 
    A[1] <- 1; 
    for i <- 2 to n-1: 
        A[i] <- A[i-1] + A[i-2]; 
    return A[n-1]
Matrix Power Method
This method leverages matrix multiplication to calculate Fibonacci terms using the property of matrix exponentiation.

Pseudocode:
shell
Copiază
Editează
Fibonacci(n): 
    F <- [] 
    vec <- [[0], [1]] 
    Matrix <- [[0, 1], [1, 1]] 
    F <- power(Matrix, n) 
    F <- F * vec 
    return F[0][0]
Binet Formula Method
The Binet formula uses the Golden Ratio to directly compute the N-th Fibonacci number, but it has limitations in precision for large N due to rounding errors.

Pseudocode:
sql
Copiază
Editează
Fibonacci(n): 
    phi <- (1 + sqrt(5)) 
    phi1 <- (1 - sqrt(5)) 
    return (pow(phi, n) - pow(phi1, n)) / (pow(2, n) * sqrt(5))
Generating Function
An advanced method involving generating functions and recurrence relations, typically used for very large n.

Comparison Metric
The performance of each algorithm is measured by the execution time (T(n)) required to calculate the Fibonacci N-th term. Time complexity is used as the main metric to compare the efficiency of each algorithm.

Results
The algorithms were tested using two input series:

Small input series for the recursive method: (5, 7, 10, 12, 15, 17, 20, 22, 25, 27, 30, 32, 35, 37, 40, 42, 45)
Larger input series for comparing all methods: (501, 631, 794, 1000, 1259, 1585, 1995, 2512, 3162, 3981, 5012, 6310, 7943, 10000, 12589, 15849)
Recursive Method Results
The recursive method shows exponential time complexity, with performance degrading significantly for larger values of N.

Dynamic Programming Method Results
The dynamic programming method performs much better, exhibiting linear time complexity (T(n)).

Matrix Power Method Results
The matrix power method performs well for large N, with a time complexity of O(log n).

Binet Formula Method Results
The Binet formula is very fast but suffers from precision issues for large N, making it unreliable for values beyond N = 70.

Conclusion
Through empirical analysis, it was observed that:

The recursive method is inefficient and impractical for large inputs.
The dynamic programming method offers the best balance of efficiency and accuracy for practical use.
The matrix power method performs well for large inputs but is slower than dynamic programming.
The Binet formula is the fastest but suffers from precision issues for large Fibonacci numbers.
Future improvements may include optimizing the Binet formula to handle larger values or further optimizing matrix power multiplication techniques.

Comparison of Algorithms
Algorithm	Time Complexity (T(n))	Space Complexity (S(n))	Remarks
Recursive (Naïve)	O(2^n)	O(n)	🔴 Very slow for large n
Memoized Recursion	O(n)	O(n)	✅ Fast but uses extra memory
Iterative DP	O(n)	O(1)	✅ Best for small/moderate n
Matrix Exponentiation	O(log n)	O(1)	⚡ Best for large n
Binet’s Formula	O(1)	O(1)	⚡ Instant but may be inaccurate for large n
Generating Function	O(n)	O(n)	✅ Best for very large n