package com.github.frozensync.day6.part2

import java.io.File

private data class Group(var questions: MutableSet<Char>? = null) {
    val numberOfQuestions get() = questions?.size ?: 0
}

fun main() {
    File("src/main/kotlin/com/github/frozensync/day6/input.txt").readLines()
        .fold(mutableListOf(Group())) { groups, line ->
            if (line.isEmpty()) {
                groups.apply { add(Group()) }
            } else {
                val questions = line.toCharArray().toMutableSet()
                groups.apply {
                    val lastGroup = last()
                    lastGroup.questions?.retainAll(questions) ?: run { lastGroup.questions = questions }
                }
            }
        }
        .also { println(it) }
        .map(Group::numberOfQuestions)
        .sum()
        .run(::print)
}
