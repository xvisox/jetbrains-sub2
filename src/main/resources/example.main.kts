#!/usr/bin/env kotlin

fun fibonacci(n: Int): Int {
    return if (n <= 1) {
        n
    } else {
        fibonacci(n - 1) + fibonacci(n - 2)
    }
}

val n = 10
val result = fibonacci(n) // terrible implementation
println("The $n-th Fibonacci number is: $result")
