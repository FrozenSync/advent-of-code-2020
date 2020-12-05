package com.github.frozensync.day2.part2

import java.io.File

private data class ParseResult(val passwordPolicy: PasswordPolicy, val password: Password)
private data class PasswordPolicy(val positions: List<Int>, val char: Char)
private typealias Password = String

fun main() {
    File("src/main/kotlin/com/github/frozensync/day2/input.txt").readLines()
        .filter {
            val (passwordPolicy, password) = parseLine(it)
            password.isValid(passwordPolicy)
        }
        .count()
        .run(::println)
}

private fun parseLine(line: String): ParseResult {
    val s = line.split(' ')
    val positions = s[0].split('-').map(String::toInt)

    return ParseResult(
        PasswordPolicy(
            positions = positions,
            char = s[1].toCharArray()[0]
        ),
        password = s[2]
    )
}

private fun CharSequence.isValid(passwordPolicy: PasswordPolicy): Boolean {
    val (positions, char) = passwordPolicy

    val result1 = this[positions[0] - 1] == char
    val result2 = this[positions[1] - 1] == char

    return result1 != result2
}
