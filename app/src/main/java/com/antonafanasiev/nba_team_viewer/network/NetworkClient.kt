package com.antonafanasiev.nba_team_viewer.network

import android.app.Application
import okhttp3.*
import java.io.File
import java.io.IOException

class NetworkClient(private val application: Application) {
    private val client = buildClient()

    fun request(url: String, success: (Response) -> Unit, fail: (IOException) -> Unit) {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    fail(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    success(response)
                }
            })
    }

    private fun buildClient(): OkHttpClient {
        val httpCacheDirectory =
            File(application.applicationContext.cacheDir, "http-cache")
        val cacheSize = 5 * 1024 * 1024L
        return OkHttpClient.Builder().addNetworkInterceptor(CacheInterceptor()).cache(
            Cache(httpCacheDirectory, cacheSize)
        ).build()
    }
}