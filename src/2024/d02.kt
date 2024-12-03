import java.io.File

fun main() {
    var sum1 = 0
    var sum2 = 0

    fun readFileLineByLineUsingForEachLine(fileName: String)
            = File(fileName).forEachLine { val n = it.split(" ")
        if (checkReports(n)) {
            sum1++
        } else {
            var summed = false
            for (i in 0..n.size-1){
                var anotherList = n.toMutableList()
                anotherList.removeAt(i)
                if (checkReports(anotherList)) {summed = true}
            }
            if (summed) {sum2++}
        }
    }

    readFileLineByLineUsingForEachLine("src/2024/d02.txt")
    println(sum1)
    println(sum1+sum2)
}

fun checkReports(inp: List<String>): Boolean {
    var check = emptyList<Int>()
    for (i in 0..inp.size-2){
        check = check + (inp[i].toInt()-inp[i+1].toInt())
    }
    val allpos = check.all( { it >= 0 })
    val allneg = check.all( { it <= 0 })
    val allnotcont = check.all( { it in -3..-1 || it in 1..3 })
    return ((allpos && allnotcont) || (allneg && allnotcont))
}