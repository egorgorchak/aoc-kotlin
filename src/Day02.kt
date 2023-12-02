import kotlin.math.absoluteValue

const val RED = "red"
const val GREEN = "green"
const val BLUE = "blue"

fun main() {
    fun part1(input: List<String>): Int {
        var gameId = 0
        for (line in input) {
            val splitLines = line.split(":", ";")
            if (isGamePossible(splitLines.drop(1))) {
                val currentGameId = splitLines[0].filter { it.isDigit() }.toInt()
                gameId += currentGameId
            }
        }

        return gameId
    }

    fun part2(input: List<String>): Int {
        var gameId = 0

        for (line in input) {
            val split = line.split(":", ";")
            gameId += neededCubes(split.drop(1))
        }

        return gameId
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun isGamePossible(lines: List<String>): Boolean {
    var isGamePossible = true

    for (element in lines) {
        val cubeCount = mutableMapOf(RED to 0, GREEN to 0, BLUE to 0)
        val game = element.split(",", " ").filter { it.isNotBlank() }

        for (i in game.indices step 2) {
            var cubes = cubeCount[game[i + 1]]

            cubes = cubes?.plus(game[i].toInt())
            if (cubes != null) {
                cubeCount[game[i + 1]] = cubes
            }
        }

        if (cubeCount[RED]?.absoluteValue!! > 12
            || cubeCount[GREEN]?.absoluteValue!! > 13
            || cubeCount[BLUE]?.absoluteValue!! > 14
        ) {
            isGamePossible = false
            break
        }
    }

    return isGamePossible
}

fun neededCubes(lines: List<String>): Int {
    val cubeCount = mutableMapOf(RED to 0, GREEN to 0, BLUE to 0)
    for (element in lines) {
        val game = element.split(",", " ").filter { it.isNotBlank() }

        for (i in game.indices step 2) {
            val color = game[i + 1]
            val quantity = game[i].toInt()

            if (cubeCount[color]?.absoluteValue!! < quantity) {
                cubeCount[color] = quantity
            }
        }
    }

    return cubeCount[RED]?.absoluteValue!! * cubeCount[GREEN]?.absoluteValue!! * cubeCount[BLUE]?.absoluteValue!!
}
