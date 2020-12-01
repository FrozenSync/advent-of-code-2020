package com.github.frozensync.day1

import java.io.File

fun main() {
    val entries = File("src/main/kotlin/com/github/frozensync/day1/input.txt")
        .readLines()
        .map(String::toInt)

    outer@for (entryA in entries) {
        for (entryB in entries) {
            val entryC = entries.firstOrNull { it + entryA + entryB == 2020 }

            if (entryC != null) {
                println("$entryA * $entryB * $entryC = ${entryA * entryB * entryC}")
                break@outer
            }
        }
    }
}
