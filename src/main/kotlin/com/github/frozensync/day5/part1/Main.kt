package com.github.frozensync.day5.part1

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
        .maxByOrNull { it.id }
        ?.run { print("row $row, column $column, seat ID $id") }
}

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
