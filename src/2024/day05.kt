import java.io.BufferedReader
import java.io.File
import java.util.stream.Stream

fun main() {
    val buffer: BufferedReader = File("src/2024/data/input05.txt").bufferedReader()
    val stream: Stream<String> = buffer.lines()
    var rules = true
    val listRules: MutableList<List<String>> = mutableListOf()
    val listUpdWindows1: MutableList<List<String>> = mutableListOf()
    val listUpdWindows2: MutableList<List<String>> = mutableListOf()

    stream.forEach {
        if (it.isEmpty()) {
            rules = false
        }
        else if (rules) {
            listRules.add(it.split("|"))
        } else {
            listUpdWindows1.add(it.split(","))
        }
    }
    buffer.close()

    var ans1 = 0
    var ans2 = 0

    fun checkRules(inp: List<String>): Int {
        val windows = inp.windowed(2)
        for (j in 0..<windows.size) {
            if (windows[j] !in listRules) {
                listUpdWindows2.add(inp)
                return 0
            }
        }
        return inp[inp.size/2].toInt()
    }

    fun checkRules2(inp: List<String>): Int {
        val workList = inp.toMutableList()
        val windows = inp.windowed(2)
        for (j in 0..<windows.size) {
            if (windows[j] !in listRules) {
                val left = windows[j][0]
                val right = windows[j][1]
                val lIndex = workList.indexOf(left)
                val rIndex = workList.indexOf(right)
                workList[lIndex] = right
                workList[rIndex] = left
                return checkRules2(workList)
            }
        }
        return inp[inp.size/2].toInt()
    }

    for (i in 0..<listUpdWindows1.size) {
        ans1 += checkRules(listUpdWindows1[i])
    }

    for (i in 0..<listUpdWindows2.size) {
        ans2 += checkRules2(listUpdWindows2[i])
    }

    println(ans1)
    println(ans2)
}