package lab1.Fibo

fun main(args: Array<String>) {
    val n: Int = readLine()!!.toInt()

    if (n < 1 || n > 45) return

    print(FiboKotlin.numbersTo(n).joinToString(", "))
}

object FiboKotlin {
    fun numbersTo(n: Int): IntArray {
        val array = IntArray(n)

        for (i in 0 until n) {
            array[i] = if (i == 0 || i == 1) 1 else array[i - 1] + array[i - 2]
        }

        return array
    }
}
