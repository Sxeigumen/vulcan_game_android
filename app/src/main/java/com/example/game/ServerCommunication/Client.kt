package com.example.game.ServerCommunication

import android.accounts.NetworkErrorException
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import java.util.concurrent.Executors

data class ServerInfo(val hostAddress: String, val port: Int)

class Client(_serverInfo: ServerInfo) : Thread() {
    val connected: Boolean get() = socket != null

    /** Передатчик сообщений в UI*/
    val messageEmitter = PublishSubject.create<String>()

    private val serverInfo = _serverInfo
    private var socket: Socket? = null
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    /** executor для запуска отдельного потока */
    private val executor = Executors.newSingleThreadExecutor()

    private object HttpRequests {
        private val header = """GET /device HTTP/1.1
            |Host: ?
            |New Device: ?
            |
        """.trimMargin()
        val init = header + ""
        val mix = header + ""
        val heat = header + ""
        val cool = header + ""
        val shine = header + ""
        val electrolyze = header + ""
        val disconnect = header + "stop"
    }

    private object HttpAnswers {
        private val header = """GET /device HTTP/1.1
            |Host: ?
            |New Device: ?
            |
        """.trimMargin()
    }


    /** Отправка на сервер */
    suspend fun send(string: String) = withContext(Dispatchers.IO) {
        try {
            outputStream.write(string.toByteArray())
        } catch (ex: Exception) {
            messageEmitter.onError(ex)
        }
    }

    /** Запуск потока и бесконечного цикла для чтения входящей информации */
    override fun run() {
        executor.execute(kotlinx.coroutines.Runnable {
            kotlin.run {

                socket = Socket(serverInfo.hostAddress, serverInfo.port)
                if (socket == null) {
                    messageEmitter.onError(NetworkErrorException("Connection refused"))
                    executor.shutdownNow()
                }
                inputStream = socket!!.getInputStream()
                outputStream = socket!!.getOutputStream()

                /** Сообщение инициализации */
                CoroutineScope(Dispatchers.IO).launch { send(HttpRequests.init) }
                val buffer = ByteArray(1024)
                var lastByteNum: Int
                while (true) {
                    try {
                        lastByteNum = inputStream.read(buffer)
                        if (lastByteNum > 0) {
                            val tmpMessage = String(buffer, 0, lastByteNum)
                            if (tmpMessage == "stop") {
                                close()
                                break
                            }

                            /** Отправка сообщения в UI */
                            messageEmitter.onNext(tmpMessage)
                        }
                    } catch (ex: Exception) {
                        messageEmitter.onError(ex)
                        break
                    }
                }
            }
        })
    }

    fun close() {
        if (connected) {
            CoroutineScope(Dispatchers.IO).launch {
                send(HttpRequests.disconnect)

                executor.shutdownNow()
                inputStream.close()
                outputStream.close()
                socket!!.close()
                socket = null
            }
        }
    }
}