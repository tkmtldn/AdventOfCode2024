import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {

    var numbers1 = emptyList<Int>()
    var numbers2 = emptyList<Int>()
    var sum1 = 0
    var sum2 = 0

    fun readFileLineByLineUsingForEachLine(fileName: String)
            = File(fileName).forEachLine { val n = it.split("   ")
            numbers1 = numbers1 + n[0].toInt()
            numbers2 = numbers2 + n[1].toInt()
            }
    readFileLineByLineUsingForEachLine("src/2024/d01.txt")

    numbers1 = numbers1.sorted()
    numbers2 = numbers2.sorted()

    for (i in 0..numbers1.size-1) {
        sum1 += abs(numbers1[i]-numbers2[i])
        sum2 += numbers1[i] * numbers2.count{it == numbers1[i]}
    }

    println(sum1)
    println(sum2)

}