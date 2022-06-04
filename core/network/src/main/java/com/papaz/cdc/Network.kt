package com.papaz.cdc

import com.papaz.cdc.config.NetworkBuilder
import com.papaz.cdc.config.NetworkConfig
import javax.inject.Inject
import kotlin.reflect.KClass

class Network @Inject constructor(
        private val config: NetworkConfig
) {

    fun <T:Any> createServiceApi(apiClass:KClass<T>):T = NetworkBuilder(config).retrofit.create(
            apiClass.java
    )

}