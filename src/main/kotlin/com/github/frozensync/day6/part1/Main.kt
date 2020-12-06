package com.github.frozensync.day6.part1

import java.io.File

private data class Group(val questions: MutableSet<Char> = mutableSetOf()) {
    val numberOfQuestions get() = questions.size
}

fun main() {
    File("src/main/kotlin/com/github/frozensync/day6/input.txt").readLines()
        .fold(mutableListOf(Group())) { groups, line ->
            if (line.isEmpty()) {
                groups.apply { add(Group()) }
            } else {
                val questions = line.toCharArray().toSet()
                groups.apply { last().questions += questions }
            }
        }
        .map(Group::numberOfQuestions)
        .sum()
        .run(::print)
}
