package com.github.frozensync.day1

import java.io.File

fun main() {
    val entries = File("src/main/kotlin/com/github/frozensync/day1/input.txt")
        .readLines()
        .map(String::toInt)

    for (entryA in entries) {
        val entryB = entries.firstOrNull { it + entryA == 2020 }

        if (entryB != null) {
            println("$entryA * $entryB = ${entryA * entryB}")
            break
        }
    }
}
