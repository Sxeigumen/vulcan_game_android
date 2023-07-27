package com.example.game.ServerCommunication

import android.accounts.NetworkErrorException
import io.reactivex.rxjava3.subjects.PublishSubject
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import java.util.concurrent.Executors

object ServerInfo {
    const val hostAddress = "10.0.41.64"
    const val port = 8080
}

class Client : Thread() {
    val connected: Boolean get() = socket != null

    /** Передатчик сообщений в UI*/
    val messageEmitter = PublishSubject.create<String>()

    private var socket: Socket? = null
    private lateinit var inputStream: InputStream
    private lateinit var outputStream: OutputStream

    /** executor для запуска отдельного потока */
    private val executor = Executors.newSingleThreadExecutor()

    private companion object {
        const val headerRequest = "GET /device HTTP/1.1\r\n" +
                "Host: ${ServerInfo.hostAddress}:${ServerInfo.port}\r\n" +
                "Device: Controller\r\n" +
                "Topic: instruction\r\n" +
                "Info: "
        const val end = "\r\n\r\n"
    }

    object HttpRequests {
        const val mix = headerRequest + "mix" + end
        const val heat = headerRequest + "heat" + end
        const val cool = headerRequest + "cool" + end
        const val shine = headerRequest + "shine" + end
        const val electrolyze = headerRequest + "electrolyze" + end
    }

    object HttpAnswers {
        const val success = "success$end"
        const val unsuccess = "unsuccess$end"
    }

    private object ControllerServices {
        const val Rotate: Byte = 0x00
        const val Accelerate: Byte = 0x01
        const val HeatUp: Byte = 0x02
        const val CoolDown: Byte = 0x03
        const val Illuminate: Byte = 0x04
        const val Darken: Byte = 0x05
        const val Vibrate: Byte = 0x06
        const val Smoke: Byte = 0x07
        const val ButtonPress0: Byte = 0x08
        const val ButtonPress1: Byte = 0x09
        const val ButtonPress2: Byte = 0x10
        const val ButtonPress3: Byte = 0x11
        const val ButtonPress4: Byte = 0x12
        const val ButtonPress5: Byte = 0x13
        const val ButtonPress6: Byte = 0x14
        const val ButtonPress7: Byte = 0x15
        const val ButtonPress8: Byte = 0x16
        const val Empty: Byte = 0xf
    }

    /** Отправка на сервер */
    fun send(string: String) {
        Executors.newSingleThreadExecutor().execute(kotlinx.coroutines.Runnable {
            try {
                outputStream.write(string.toByteArray())
            } catch (ex: Exception) {
                messageEmitter.onError(ex)
            }
        })
    }

    /** Запуск потока и бесконечного цикла для чтения входящей информации */
    override fun run() {
        executor.execute(kotlinx.coroutines.Runnable {

            try {
                socket = Socket(ServerInfo.hostAddress, ServerInfo.port)
                if (socket == null) {
                    messageEmitter.onError(NetworkErrorException("Connection refused"))
                    executor.shutdownNow()
                }
                inputStream = socket!!.getInputStream()
                outputStream = socket!!.getOutputStream()
            } catch (ex: Exception) {
                socket = null
                executor.shutdownNow()
            }

            val buffer = ByteArray(1024)
            var lastByteNum: Int
            while (true) {
                try {
                    lastByteNum = inputStream.read(buffer)
                    if (lastByteNum > 0) {
                        val tmpMessage = String(buffer, 0, lastByteNum)

                        /** Отправка сообщения в UI */
                        messageEmitter.onNext(tmpMessage)
                    }
                } catch (ex: Exception) {
                    socket = null
                    break
                }
            }
        })
    }

    fun close() {
        if (connected) {
            executor.shutdownNow()
            inputStream.close()
            outputStream.close()
            socket!!.close()
            socket = null
        }
    }
}
