package com.papaz.cdc.config

import okhttp3.Interceptor


interface NetworkConfig {
    fun baseUrl():String
    fun getInterceptors():List<Interceptor>
}