/**
 * Created by Gunnar on 3/2/2017.
 */
fun main(args: Array<String>) {

    /* According to a local legend, a band of seven pirates have found a hoard of gold
       coins and are trying to share the coins equally among themselves. Alas, there
       are six coins left over, and in the ensuing fight, one pirate is slain. The
       remaining six pirates, still unable to share equally because two coins are left
       over, fight again — and another pirate lies dead. The remaining pirates attempt
       to share and share alike, except that one coin is left over. One more fight, one
       more dead pirate, and it is only now that an equal sharing is possible */

    // Max number we will check. Can be increased if needed.
    val maxCheck = 1000

    // Calculate the answer
    val coins = (1..maxCheck)              // List from 1-maxCheck
            .filter { it % 4 == 0 }        // Keep those that are congruent to 0 (mod 4)
            .filter { (it - 1) % 5 == 0 }  // Keep those that are congruent to 1 (mod 5)
            .filter { (it - 2) % 6 == 0 }  // Keep those that are congruent to 2 (mod 6)
            .filter { (it - 6) % 7 == 0 }  // Keep those that are congruent to 6 (mod 7)
            .min()                         // Take the minimum

    // Print out the answer
    println(coins)
}