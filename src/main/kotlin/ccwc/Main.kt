package org.usmanzaheer1995.ccwc

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import java.io.File
import java.io.FileNotFoundException

fun main(args: Array<String>) {
    App().main(args)
}

class App : CliktCommand() {
    val numOfBytes: String? by option("-c", help = "Number of bytes to read")
    val numOfLines: String? by option("-l", help = "Number of lines to read")
    val fileName: String by option("-f", help = "File to read from").required()

    override fun run() {
        process(fileName)
    }

    private fun process(fileName: String) {
        println("Processing file $fileName...")
        var bytes = emptyArray<Byte>().toByteArray()
        try {
            val file = File("src/main/kotlin/ccwc/$fileName")
            bytes = file.readBytes()
        } catch (e: FileNotFoundException) {
            println("File not found")
        }
        println("Total bytes: ${bytes.size}")
    }
}
