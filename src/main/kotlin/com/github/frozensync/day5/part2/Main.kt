package com.github.frozensync.day5.part2

import java.io.File

private data class BoardingPass(val rowSequence: String, val columnSequence: String)
private data class Seat(val row: Int, val column: Int, val id: Int = row * 8 + column)

fun main() {
    File("src/main/kotlin/com/github/frozensync/day5/input.txt").readLines()
        .map {
            BoardingPass(
                rowSequence = it.substring(0 until it.length - 3),
                columnSequence = it.substring(startIndex = it.length - 3)
            )
        }
        .map { Seat(computeRow(it.rowSequence), computeColumn(it.columnSequence)) }
        .map(Seat::id)
        .sorted()
        .findMissingSeat()
        .run(::print)
}

private fun List<Int>.findMissingSeat(): Int? =
    fold<Int, Int?>(null) { current, next ->
        when (current) {
            null -> next
            else -> if (next - current == 2) return current + 1 else next
        }
    }.let { if (it == lastOrNull()) null else it }

private fun computeRow(rowSequence: String): Int = rowSequence
    .map {
        when (it) {
            'F' -> '0'
            'B' -> '1'
            else -> throw IllegalArgumentException("Illegal char in sequence")
        }
    }
    .joinToString(separator = "")
    .toInt(radix = 2)


private fun computeColumn(columnSequence: String) = columnSequence
    .map {
        when (it) {
            'L' -> '0'
            'R' -> '1'
            else -> throw IllegalArgumentException("Illegal char in sequence")
        }
    }
    .joinToString(separator = "")
    .toInt(radix = 2)
