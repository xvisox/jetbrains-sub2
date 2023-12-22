package ui.editor

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ui.common.Constants
import java.io.BufferedReader
import java.io.File
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

    suspend fun runKotlinScript(lines: MutableList<String>, codeText: String, result: (Boolean, Int) -> Unit) =
        withContext(Dispatchers.IO) {
            val process = getKotlinScriptRunnerProcessBuilder(createTempFile(codeText)).start()
            val reader = getProcessOutputReader(process)
            val buffer = mutableListOf<String>()
            val mutex = Mutex(locked = false)

            launch {
                lines.clear()
                while (isActive) {
                    delay(Constants.BATCH_UPDATE_INTERVAL)
                    mutex.withLock {
                        withContext(Dispatchers.Default) {
                            lines.addAll(buffer)
                            buffer.clear()
                        }
                    }
                }
            }

            while (true) {
                val line = reader.readLine() ?: break
                mutex.withLock { buffer.add(line) }
            }

            result(false, process.waitFor())
        }

    private fun getProcessOutputReader(process: Process) = BufferedReader(InputStreamReader(process.inputStream))

    private fun createTempFile(codeText: String): String {
        val tempFile = File.createTempFile(Constants.TEMP_FILE_PREFIX, Constants.TEMP_FILE_SUFFIX)
        tempFile.writeText(codeText)
        return tempFile.toPath().toString()
    }

    private fun getKotlinScriptRunnerProcessBuilder(scriptFilePath: String): ProcessBuilder {
        val command = "${Constants.KOTLINC_SCRIPT_COMMAND}$scriptFilePath"

        val processBuilder = ProcessBuilder()
        processBuilder.command("bash", "-c", command)
        processBuilder.redirectErrorStream(true)

        return processBuilder
    }
}