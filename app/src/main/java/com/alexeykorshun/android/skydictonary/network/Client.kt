package com.alexeykorshun.android.skydictonary.network

import android.util.Log
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author Alexei Korshun on 29.09.2020.
 */
class Client {

    private val TAG = "Client"

    fun execute(request: Request): String {

        return (URL(request.url).openConnection() as HttpURLConnection)
            .also { session -> session.requestMethod = request.method }
            .tryFinally(
                { session ->
                    session.inputStream.bufferedReader()
                        .use(BufferedReader::readText)
                },
                { session -> session.disconnect() }
            )
            .also { Log.d(TAG, it) }
    }

    private inline fun <T, R> T.tryFinally(block: (T) -> R, finally: (T) -> Unit): R {
        try {
            return block(this)
        } finally {
            finally(this)
        }
    }

}