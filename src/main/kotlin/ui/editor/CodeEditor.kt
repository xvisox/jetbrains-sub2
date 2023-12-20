package ui.editor

import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

class CodeEditor {

    fun getFileContent(filePath: String): String {
        return try {
            File(filePath).readText()
        } catch (e: Exception) {
            e.printStackTrace()
            "Initial text"
        }
    }

    fun runKotlinScript(scriptFilePath: String): ProcessBuilder {
        val command = "kotlinc -script $scriptFilePath"

        val processBuilder = ProcessBuilder()
        processBuilder.command("bash", "-c", command)
        processBuilder.redirectErrorStream(true)

        return processBuilder
//            // Read the output of the command
//            val reader = BufferedReader(InputStreamReader(process.inputStream))
//            var line: String?
//
//            while (reader.readLine().also { line = it } != null) {
//                println(line)
//            }
//
//            // Wait for the process to finish
//            return process.waitFor()

    }
}