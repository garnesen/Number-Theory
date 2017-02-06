/**
 * Created by Gunnar on 1/26/2017.
 * Number Theory
 */

import java.io.File
import java.util.Scanner

/**
 * Performs the sieve of Eratosthenes up to n. Returns the list of leftover primes.
 */
fun sieve(n: Int): List<Int> {
    // The largest value that a prime multiple needs to be in the sieve is the square root of the last number n.
    val maxValueToCheck = Math.sqrt(n.toDouble()).toInt()
    // Creates the list of values from 2 to n that the sieve will be performed on.
    val list = (2..n).toList()

    // Recursively remove multiples of primes.
    fun removeMultiples(list: List<Int>, i: Int): List<Int> {
        if (list[i] <= maxValueToCheck) {
            return removeMultiples(list.filter { it == list[i] || it % list[i] != 0 }, i + 1)
        }
        return list
    }
    return removeMultiples(list, 0);
}

fun main(args: Array<String>) {
    // Create a scanner to read in input from stdin.
    val kb = Scanner(System.`in`)
    // Get the number from stdin (user input). The sieve will find primes up to this number.
    val maxNumber = kb.nextInt()
    // Perform the sieve and store the result.
    val primes = sieve(maxNumber)

    val sequences = primes.mapIndexed { i, p ->
        primes.subList(i+1, primes.size).map {
            val dist = it - p
            fun findPrimes(list: List<Int>): List<Int> {
                if (primes.contains(p + dist * list.size)) {
                    return findPrimes(list + (p + dist * list.size))
                }
                return list
            }
            findPrimes(listOf(p, it))
        }.filter { it.size >= 3 }
    }.flatten().sortedBy { it.size }

    // Print results to a file.
    File("results/ArithmeticSequenceOfPrimes.txt").printWriter().use { out ->
        sequences.forEach {
            out.println("${it.size} $it")
        }
    }
}