import java.io.PrintWriter
import java.math.BigInteger
import java.util.*

/**
 * Created by Gunnar on 3/28/2017.
 */

/**
 * Input: Two numbers representing a range.
 * Output: The primality of the pth Mersenne number for all primes p in the input range.
 *
 * Makes use of the Lucas-Lehmer Test.
 */

fun main(args: Array<String>) {
    // Create a scanner to read from stdin
    val kb = Scanner(System.`in`)
    // Read the lower bound of the input range
    val lowerBound = kb.nextInt()
    // Read the upper bound of the input range
    val upperBound = kb.nextInt()

    // A list of primes from the input range
    val p = primesInRange(lowerBound, upperBound)

    // Open a file for output
    val file = PrintWriter("results/LucasLehmer.txt")

    // Perform the Lucas-Lehmer test for the pth Mersenne number
    p.forEach { lucasLehmerTest(file, it) }

    // Close the printwriter
    file.close()
}

fun lucasLehmerTest(file: PrintWriter, p: Int) {
    file.println("Checking if the ${p}th Mersenne number is prime...")

    // Calculate the pth Mersenne number
    val mp = BigInteger.valueOf(2).pow(p).minus(BigInteger.ONE)
    file.println("Remainders are mod $mp")

    // This is the start value of the remainder
    var r = BigInteger.valueOf(4)
    file.println("r_1 = 4")

    // Perform mod on the remainder variable using itself p-1 times
    for (i in 2..(p-1)) {
        r = r.multiply(r).minus(BigInteger.valueOf(2)).mod(mp)
        file.println("r_$i = $r")
    }

    // Check if the last remainder is 0
    if (r.compareTo(BigInteger.ZERO) == 0) {
        file.println("By the LucasLehmer test, the ${p}th Merssen number is prime. " +
                "This is because remainder on step p-1 of the recursion is 0.")
    }
    else {
        file.println("By the LucasLehmer test, the ${p}th Merssen number is composite. " +
                "This is because remainder on step p-1 of the recursion is non-zero.")
    }

    file.println()
}

/**
 * Performs the sieve of Eratosthenes to find the primes in the range.
 */
fun primesInRange(lower: Int, upper: Int): List<Int> {
    // Return an empty list for an invalid range
    if (lower > upper) {
        return emptyList()
    }

    // The largest value that a prime multiple needs to be in the sieve is the square root of the last number n
    val maxValueToCheck = Math.sqrt(upper.toDouble()).toInt()
    // Creates the list of values from 2 to n that the sieve will be performed on
    val list = (2..upper).toList()

    // Recursively remove multiples of primes
    fun removeMultiples(list: List<Int>, i: Int): List<Int> {
        if (list[i] <= maxValueToCheck) {
            return removeMultiples(list.filter { it == list[i] || it % list[i] != 0 }, i + 1)
        }
        return list
    }

    // Return the list of primes with only those in the range specified
    return removeMultiples(list, 0).filter { it >= lower }
}