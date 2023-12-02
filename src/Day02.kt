import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        var gameId = 0

        for (line in input) {
            val split = line.split(":", ";")
            val currentGameId = split[0].filter { it.isDigit() }.toInt()
            if (isGamePossible(split.drop(1))) {
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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val part2 = part2(testInput)
    println(part2)
    check(part2 == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun isGamePossible(lines: List<String>): Boolean {
    val red = 12
    val green = 13
    val blue = 14
    var gameId = true

    for (element in lines) {
        val map = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        val filter = element.split(",", " ").filter { it.isNotBlank() }

        for (i in filter.indices step 2) {
            var a = map[filter[i + 1]]

            a = a?.plus(filter[i].toInt())
            if (a != null) {
                map[filter[i + 1]] = a
            }
        }
        val currentRed = map["red"]
        val currentGreen = map["green"]
        val currentBlue = map["blue"]

        if (currentRed?.absoluteValue!! > red
            || currentGreen?.absoluteValue!! > green
            || currentBlue?.absoluteValue!! > blue
        ) {
            gameId = false
            break
        }
    }

    return gameId
}

fun neededCubes(lines: List<String>): Int {
    val map = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
    for (element in lines) {
        val filter = element.split(",", " ").filter { it.isNotBlank() }

        for (i in filter.indices step 2) {
            val s = filter[i + 1]
            val s1 = filter[i].toInt()

            if (map[s]?.absoluteValue!! < s1) {
                map[s] = s1
            }
        }
    }

    return map["red"]?.absoluteValue!! * map["green"]?.absoluteValue!! * map["blue"]?.absoluteValue!!
}
