package ui.editor

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ui.common.Constants
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.concurrent.atomic.AtomicBoolean

class CodeEditor {

    // Weighted Moving Average (WMA)
    fun estimateRunningTimeSecs(previousElapsedTimes: LongArray, initialBatchSize: Int, batchSize: Int): Double {
        val weights = (1..(initialBatchSize - batchSize)).map { it.toLong() }

        val estimatedRunningTime = previousElapsedTimes.zip(weights)
            .sumOf { (elapsedTime, weight) -> elapsedTime * weight }

        return estimatedRunningTime.times(batchSize) / (1000.0 * weights.sum())
    }

    fun getFileContent(filePath: String): String {
        return try {
            File(filePath).readText()
        } catch (e: Exception) {
            e.printStackTrace()
            Constants.INVALID_FILE_PATH_MESSAGE
        }
    }

    suspend fun runKotlinScript(outputLines: MutableList<String>, codeText: String, result: (Int) -> Unit) =
        withContext(Dispatchers.IO) {
            val process = getKotlinScriptRunnerProcessBuilder(createTempFile(codeText)).start()
            val reader = getProcessOutputReader(process)
            val buffer = mutableListOf<String>()
            val mutex = Mutex(locked = false)
            val running = AtomicBoolean(true)

            launch {
                outputLines.clear()
                while (running.get()) {
                    delay(Constants.BATCH_UPDATE_INTERVAL)
                    mutex.withLock {
                        withContext(Dispatchers.Default) {
                            outputLines.addAll(buffer)
                            buffer.clear()
                        }
                    }
                }
            }

            while (true) {
                val line = reader.readLine() ?: break
                mutex.withLock { buffer.add(line) }
            }

            running.set(false)
            result(process.waitFor())
        }

    private fun getProcessOutputReader(process: Process) = BufferedReader(InputStreamReader(process.inputStream))

    private fun createTempFile(codeText: String): String {
        val tempFile = File.createTempFile(Constants.TEMP_FILE_PREFIX, Constants.TEMP_FILE_SUFFIX)
        tempFile.writeText(codeText)
        return tempFile.toPath().toString()
    }

    private fun getKotlinScriptRunnerProcessBuilder(scriptFilePath: String): ProcessBuilder {
        val command = "${Constants.KOTLINC_SCRIPT_COMMAND}${scriptFilePath}"

        val processBuilder = ProcessBuilder()
        processBuilder.command("bash", "-c", command)
        processBuilder.redirectErrorStream(true)

        return processBuilder
    }
}