import kotlin.random.Random
import java.util.Scanner

const val size = 9

class Cell (
    var isMine: Boolean = false,
    var isMarked: Boolean = false,
    var isExplored: Boolean = false,
    var Closed: Int = 0
) {
    override fun toString(): String {
        return when {
            isMarked -> "*"
            isMine -> "."
            isExplored -> {
                if (Closed > 0)
                    Closed.toString()
                else
                    "/"
            }
            else -> {
                "."
            }
        }
    }
}

class Minesweeper(numOfMines: Int) {


    private fun isWithinBoundary(x: Int, y: Int) = x in 0 until size && y in 0 until size

    private val minefield = Array(size) { Array(size) { Cell() } }
    private val numOfMines = minOf(numOfMines, size*size)

    init {
        randomize()
    }

    private fun randomize() {
        var left = 0
        while (left < numOfMines) {
            val row = Random.nextInt(size)
            val col = Random.nextInt(size)
            if (!minefield[row][col].isMine) {
                minefield[row][col].isMine = true
                increaseAllNeighbors(row, col)
                left++
            }
        }
    }

    private fun increaseAllNeighbors(row: Int, col: Int) {
        for (x in (row - 1)..(row + 1)) {
            for (y in (col - 1)..(col + 1)) {
                if (isWithinBoundary(x, y)) {
                    if (minefield[x][y].isMine) {
                        continue
                    }
                    minefield[x][y].Closed++
                }
            }
        }
    }

    fun print() {
        println(" |123456789|")
        println("-|---------|")
        for (row in 0 until size) {
            print("${row + 1}|")
            for (col in 0 until size) {
                print(minefield[row][col])
                }
            print("|")
            println()
        }
        println("-|---------|")
    }

    fun toggleMarker(row: Int, col: Int) {
        minefield[row][col].isMarked = !minefield[row][col].isMarked
    }

    fun isMine(row: Int, col: Int): Boolean {
        return minefield[row][col].isMine
    }

    fun fill(row: Int, col: Int) {
        if (isWithinBoundary(row, col)) {
            val cell = minefield[row][col]
            if (!cell.isMine && !cell.isExplored) {
                minefield[row][col].isExplored = true
                minefield[row][col].isMarked = false

                fill(row - 1, col)
                fill(row + 1, col)
                fill(row, col - 1)
                fill(row, col + 1)
            }
        }
    }


    fun gameWon(): Boolean {
        var countCorrectMarkers = 0
        var countWrongMarkers = 0

        for (row in 0 until size) {
            for (col in 0 until size) {
                val cell = minefield[row][col]

                if (cell.isMarked && cell.isMine) {
                    countCorrectMarkers++
                }
                if (cell.isMarked && !cell.isMine) {
                    countWrongMarkers++
                }
            }
        }
        return countCorrectMarkers == numOfMines && countWrongMarkers == 0
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    println("How many mines do you want on the field?")
    val numOfMines = readln().toInt()
    val mineSweeper = Minesweeper(numOfMines)
    mineSweeper.print()
    do {
        print("Set/delete mines marks (x and y coordinates): ")
        val col = scanner.nextInt() - 1
        val row = scanner.nextInt() - 1
        val action = scanner.next()
        when (action) {
            "free" -> {
                if (mineSweeper.isMine(row, col)) {
                    mineSweeper.print()
                    println("You stepped on a mine and failed")
                    return
                }
                else {
                    mineSweeper.fill(row, col)
                    mineSweeper.print()
                }
            }
            "mine" -> {
                mineSweeper.toggleMarker(row, col)
                mineSweeper.print()
            }
            else -> continue
        }
    } while(!mineSweeper.gameWon())
    println("Congratulations! You found all the mines!")
}