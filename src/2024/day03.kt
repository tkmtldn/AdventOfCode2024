import java.io.File

fun main() {
    fun readFileAsLinesUsingUseLines(fileName: String): List<String>
            = File(fileName).useLines { it.toList() }
    val content = readFileAsLinesUsingUseLines("src/2024/data/input03.txt").toString()

    println(regexSearch(content))

    val changedContent = content.split("do()").toMutableList()

    var secondContent = ""
    changedContent.forEach {
        if (it.contains("don't()")) {
            val limit = it.indexOf("don't()") - 1
            secondContent += it.slice(0..limit)
        } else {
            secondContent += it
        }
    }
    println(regexSearch(secondContent))
}


fun regexSearch(inp: String): Int {
    val regex = Regex("mul[(]([0-9]*)[,]([0-9]*)[)]")
    val matches = regex.findAll(inp)

    return matches.map { it.groupValues }.map { it[1].toInt() * it[2].toInt()}.sum()
}