package com.papaz.cdc.config

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkBuilder(config: NetworkConfig) {

    companion object {
        private const val DEFAULT_TIMEOUT = 59L
        private const val DEFAULT_TIMEOUT_FOR_CONNECTION = 10L
    }

    private val gsonBuilder by lazy {
        GsonBuilder()
    }

    private val networkClient by lazy {
        val builder = OkHttpClient.Builder()

        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        builder.connectTimeout(DEFAULT_TIMEOUT_FOR_CONNECTION, TimeUnit.SECONDS)

        builder.retryOnConnectionFailure(retryOnConnectionFailure = true)
        config.getInterceptors().forEach {
            builder.addInterceptor(it)
        }
        builder.build()
    }


    val retrofit = Retrofit.Builder()
            .baseUrl(config.baseUrl())
            .client(networkClient)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()

}