fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (line in input) {
            val numbers = line.filter { it.isDigit() }
            val first = "${numbers[0]}${numbers[numbers.length - 1]}"

            result += first.toInt()
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val toRegex = "(one|two|three|four|five|six|seven|eight|nine)".toRegex()
        var result = 0
        for (line in input) {
            var dup = line
            while (toRegex.find(dup) != null) {
                val matchResult = toRegex.find(dup)
                dup = dup.replaceRange(
                    matchResult!!.range.first,
                    matchResult.range.first + 1,
                    numbersAsStrings[matchResult.value].toString()
                )
                matchResult.next()
            }
            val numbers = dup.filter { it.isDigit() }
            val first = "${numbers[0]}${numbers[numbers.length - 1]}"

            result += first.toInt()
        }

        return result
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
