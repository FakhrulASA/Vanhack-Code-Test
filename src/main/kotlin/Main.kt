fun main(args: Array<String>) {
//   println(sortCsvColumns("Adam,Beth,Charles,Danielle,Eric\n3907,17945,10091,10088,10132\n48,2,12,13,11"))
//    println(decode("MDCLXVI"))
//    println(numerals(2008))
}

fun sortCsvColumns(csvData: String): String {
    var rowB: String = ""
    var rowA: String = ""
    var rowC: String = ""
    val hashMap: HashMap<String, List<String>> = HashMap<String, List<String>>()
    val lstValues: List<String> = csvData.split("\n").map { it -> it.trim() }
    val list_a: List<String> = lstValues[0].split(",")
    val list_b: List<String> = lstValues[1].split(",")
    val list_c: List<String> = lstValues[2].split(",")

    for ((x, a) in list_a.withIndex()) {
        hashMap[a] = listOf(list_b[x], list_c[x])
    }
    val key = hashMap.toSortedMap().keys.toList()

    for ((x, a) in key.withIndex()) {
        if (x == 0) {
            rowA += a
        } else if (x == hashMap.size - 1) {
            rowA = "$rowA,$a\n"
        } else {
            rowA = "$rowA,$a"
        }
    }
    var hashMapSorted = hashMap.toSortedMap()

    for ((y, a) in hashMap.toSortedMap().keys.withIndex()) {
        if (y == 0) {
            rowB += hashMapSorted[a]!![0]
            rowC += hashMapSorted[a]!![1]
        } else if (y == hashMap.size - 1) {
            rowB = rowB + "," + hashMapSorted[a]!![0] + "\n"
            rowC = rowC + "," + hashMapSorted[a]!![1] + "\n"
        } else {
            rowB = rowB + "," + hashMapSorted[a]!![0]
            rowC = rowC + "," + hashMapSorted[a]!![1]
        }
    }
    val s = rowA
    val d = rowB
    val m = rowC
    return s + d + m
}

fun decode(roman: String): Int {
    fun value(r: Char): Int {
        if (r == 'I') return 1
        if (r == 'V') return 5
        if (r == 'X') return 10
        if (r == 'L') return 50
        if (r == 'C') return 100
        if (r == 'D') return 500
        return if (r == 'M') 1000 else -1
    }

    var res = 0
    var i = 0
    while (i < roman.length) {

        val s1 = value(roman[i])

        if (i + 1 < roman.length) {
            val s2 = value(roman[i + 1])

            if (s1 >= s2) {
                res += s1
            } else {
                res = res + s2 - s1
                i++
            }
        } else {
            res += s1
        }
        i++
    }
    return res
}

fun numerals(num: Int): String {
    val `val` = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val rom = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    var N = num
    val ans = StringBuilder()
    var i = 0
    while (N > 0) {
        while (N >= `val`[i]) {
            ans.append(rom[i])
            N -= `val`[i]
        }
        i++
    }
    return ans.toString()
}


