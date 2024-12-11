import java.io.BufferedReader
import java.io.File
import java.util.stream.Stream

fun main() {
    val buffer: BufferedReader = File("src/2024/data/input06.txt").bufferedReader()
    val stream: Stream<String> = buffer.lines()
    val list: MutableList<List<String>> = mutableListOf()
    stream.forEach { list.add(it.chunked(1)) }
    buffer.close()

    var listMoves: MutableList<List<Int>> = mutableListOf()
    var moves: MutableList<List<Int>> = mutableListOf(
        listOf(-1, 0), listOf(0,1), listOf(1,0), listOf(0,-1))

    var dir = 0
    var currentX = 0
    var currentY = 0
    val width = list[0].size
    val depth = list.size

    for (i in 0..<depth){
        for (j in 0..<width) {
            if (list[i][j] == "^") { currentX = i; currentY =j }
        }
    }

    while (true) {
        val nextX = currentX + moves[dir%4][0]
        val nextY = currentY + moves[dir%4][1]
        if (nextX<0 || nextY<0 || nextX>=depth || nextY>=width) {
            listMoves.add(listOf(currentX, currentY))
            break
        }
        if (list[nextX][nextY] == "#") {
            dir++
            listMoves.add(listOf(currentX, currentY))
            currentX += moves[dir%4][0]
            currentY += moves[dir%4][1]
        } else {
            listMoves.add(listOf(currentX, currentY))
            currentX = nextX
            currentY = nextY
        }
    }

    println(listMoves.toSet().size)
    println(dir)
}