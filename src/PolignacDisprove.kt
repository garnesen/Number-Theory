/**
 * Created by Gunnar on 2/5/2017.
 */

fun possible(primes: List<Int>, num: Int): Boolean {
    var pow = 0;
    while (num - Math.pow(2.0, pow.toDouble()) >= 0) {
        if (primes.contains((num - Math.pow(2.0, pow.toDouble())).toInt())) {
            return true;
        }
        pow++;
    }
    return false;
}

fun main(args: Array<String>) {
    val primes = sieve(4000000)
    val counter = (2000000..3000000).toList().filter { it % 2 != 0 }.filter { !possible(primes, it)}.min()
    if (counter != null) {
        println(counter)
        (0..22).toList().map { it -> counter - Math.pow(2.toDouble(), it.toDouble()).toInt() }.forEach { println("$3000011 - 2^$ & $it & Not Prime") }
    }

}