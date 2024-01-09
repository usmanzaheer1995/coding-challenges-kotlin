package org.usmanzaheer1995.ccwc

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
    App().main(args)
}

class App : CliktCommand() {
    val numOfBytes by option("-c", "--numOfBytes", help = "Number of bytes to read").flag()
    val numOfLines by option("-l", help = "Number of lines to read").flag()
    val numOfWords by option("-w", help = "Number of words to read").flag()
    val numOfCharacters by option("-m", help = "Number of characters to read").flag()
    val filename by argument()

    override fun run() {
        val file = File("src/main/kotlin/ccwc/$filename")
        if (!file.exists()) {
            println("File not found")
            return
        }

        if (numOfBytes) {
            println("Total bytes: ${readNumOfBytes(file)}")
        }

        if (numOfLines) {
            println("Total lines: ${readNumOfLines(file)}")
        }

        if (numOfWords) {
            println("Total words: ${readNumOfWords(file)}")
        }

        if (numOfCharacters) {
            println("Total characters: ${readNumOfCharacters(file)}")
        }
    }

    private fun readNumOfBytes(file: File): Int {
        return file.readBytes().size
    }

    private fun readNumOfLines(file: File): Int {
        val reader = BufferedReader(FileReader(file))
        var lines = 0
        while (reader.readLine() != null) lines++

        return lines
    }

    private fun readNumOfWords(file: File): Int {
        val text = file.readText().trim()
        return text.split("\\s+".toRegex()).size
    }

    private fun readNumOfCharacters(file: File): Int {
        val text = file.readText()
        return text.length
    }
}
