package com.github.frozensync.day2.part1

import java.io.File

private data class ParseResult(val passwordPolicy: PasswordPolicy, val password: Password)
private data class PasswordPolicy(val min: Int, val max: Int, val char: Char)
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
    val boundaries = s[0].split('-')

    return ParseResult(
        PasswordPolicy(
            min = boundaries[0].toInt(),
            max = boundaries[1].toInt(),
            char = s[1].toCharArray()[0]
        ),
        password = s[2]
    )
}

private fun Password.isValid(passwordPolicy: PasswordPolicy): Boolean {
    val count = count { it == passwordPolicy.char }
    return count >= passwordPolicy.min && count <= passwordPolicy.max
}
