import java.math.BigInteger

/**
 * Created by Gunnar on 4/24/2017.
 */
/**
 * Find a positive integer z that is the hypotenuse for four distinct PPT's.
 */
fun main(args: Array<String>) {

    // Generate PPTs up to a set value.
    val triples =
            // Take values 1-3000 for a
            (1..3000).map { a ->

                // Take values a-3000 for b
                (a..3000).map{b ->

                    // Check if the triple (a,b,c) is a PPT
                    val c = Math.sqrt((a*a+b*b).toDouble())
                    if (c == Math.floor(c) && gcdIsOne(a,b,c.toInt())) {
                        Triple(a,b,c.toInt())
                    }
                    else {
                        Triple(-1,-1,-1)
                    }
                }
            }
            .flatten()                        // Flatten to one list of PPTs
            .filter { it.first != -1 }        // Get rid of invalid PPTs
            .groupBy { it.third }             // Group by the c value of the PPT
            .filter { it.value.size >= 4 }    // Keep PPTs whose c value shows in 4 or more
            .toSortedMap()                    // Sort the data

    // Print the list of PPTs
    println(triples)
}

/**
 * Takes three integers and checks if the GCD of each is 1.
 */
fun gcdIsOne(a: Int, b: Int, c: Int): Boolean {
    // Convert to BigInteger to use the GCD function
    val ba = BigInteger.valueOf(a.toLong())
    val bb = BigInteger.valueOf(b.toLong())
    val bc = BigInteger.valueOf(c.toLong())

    // Check each pair has a GCD of 1.
    return  ba.gcd(bb).equals(BigInteger.ONE) &&
            bb.gcd(bc).equals(BigInteger.ONE) &&
            ba.gcd(bc).equals(BigInteger.ONE)
}