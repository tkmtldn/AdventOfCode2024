import java.io.BufferedReader
import java.io.File
import java.util.stream.Stream

fun main() {
    val buffer: BufferedReader = File("src/2024/data/input04.txt").bufferedReader()
    val stream: Stream<String> = buffer.lines()
    val list: MutableList<List<String>> = mutableListOf()
    stream.forEach { list.add(it.chunked(1)) }
    buffer.close()
    val lSize = list.size
    var countXmas = 0
    var countMas = 0

    fun checkingXmas(list: List<List<String>>, i: Int, j:Int): Int {
        var count = 0
        //n
        if (list.getOrNull(i+1)?.getOrNull(j) == "M" &&
            list.getOrNull(i+2)?.getOrNull(j) == "A" &&
            list.getOrNull(i+3)?.getOrNull(j) == "S") { count++ }
        //ne
        if (list.getOrNull(i+1)?.getOrNull(j+1) == "M" &&
            list.getOrNull(i+2)?.getOrNull(j+2) == "A" &&
            list.getOrNull(i+3)?.getOrNull(j+3) == "S") { count++ }
        //e
        if (list.getOrNull(i)?.getOrNull(j+1) == "M" &&
            list.getOrNull(i)?.getOrNull(j+2) == "A" &&
            list.getOrNull(i)?.getOrNull(j+3) == "S") { count++ }
        //se
        if (list.getOrNull(i-1)?.getOrNull(j+1) == "M" &&
            list.getOrNull(i-2)?.getOrNull(j+2) == "A" &&
            list.getOrNull(i-3)?.getOrNull(j+3) == "S") { count++ }
        //s
        if (list.getOrNull(i-1)?.getOrNull(j) == "M" &&
            list.getOrNull(i-2)?.getOrNull(j) == "A" &&
            list.getOrNull(i-3)?.getOrNull(j) == "S") { count++ }
        //sw
        if (list.getOrNull(i-1)?.getOrNull(j-1) == "M" &&
            list.getOrNull(i-2)?.getOrNull(j-2) == "A" &&
            list.getOrNull(i-3)?.getOrNull(j-3) == "S") { count++ }
        //w
        if (list.getOrNull(i)?.getOrNull(j-1) == "M" &&
            list.getOrNull(i)?.getOrNull(j-2) == "A" &&
            list.getOrNull(i)?.getOrNull(j-3) == "S") { count++ }
            //nw
        if (list.getOrNull(i+1)?.getOrNull(j-1) == "M" &&
            list.getOrNull(i+2)?.getOrNull(j-2) == "A" &&
            list.getOrNull(i+3)?.getOrNull(j-3) == "S") { count++ }
        return count
    }

    fun checkingMas(list: List<List<String>>, i: Int, j:Int): Int {
        var count = 0
        // UP M
        if (list.getOrNull(i-1)?.getOrNull(j-1) == "M" &&
            list.getOrNull(i-1)?.getOrNull(j+1) == "M" &&
            list.getOrNull(i+1)?.getOrNull(j-1) == "S" &&
            list.getOrNull(i+1)?.getOrNull(j+1) == "S") { count++ }
        // DOWN M
        if (list.getOrNull(i+1)?.getOrNull(j-1) == "M" &&
            list.getOrNull(i+1)?.getOrNull(j+1) == "M" &&
            list.getOrNull(i-1)?.getOrNull(j-1) == "S" &&
            list.getOrNull(i-1)?.getOrNull(j+1) == "S") { count++ }
        // LEFT M
        if (list.getOrNull(i+1)?.getOrNull(j-1) == "M" &&
            list.getOrNull(i-1)?.getOrNull(j-1) == "M" &&
            list.getOrNull(i+1)?.getOrNull(j+1) == "S" &&
            list.getOrNull(i-1)?.getOrNull(j+1) == "S") { count++ }
        // RIGHT M
        if (list.getOrNull(i+1)?.getOrNull(j+1) == "M" &&
            list.getOrNull(i-1)?.getOrNull(j+1) == "M" &&
            list.getOrNull(i+1)?.getOrNull(j-1) == "S" &&
            list.getOrNull(i-1)?.getOrNull(j-1) == "S") { count++ }
        return count
    }

    for (i in 0..lSize) {
        for (j in 0..lSize) {
            if (list.getOrNull(i)?.getOrNull(j) == "X") {
                countXmas += checkingXmas(list,i,j)
            }
            if (list.getOrNull(i)?.getOrNull(j) == "A") {
                countMas += checkingMas(list,i,j)
            }
        }
    }

    println(countXmas)
    println(countMas)
}