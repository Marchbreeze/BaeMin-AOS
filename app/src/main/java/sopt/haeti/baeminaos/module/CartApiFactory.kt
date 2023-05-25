package sopt.haeti.baeminaos.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import sopt.haeti.baeminaos.BuildConfig
import sopt.haeti.baeminaos.data.service.CartService

object CartApiFactory {
    private const val BASE_URL = BuildConfig.BASE_URL

    @OptIn(ExperimentalSerializationApi::class)
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val cartService = CartApiFactory.create<CartService>()
}