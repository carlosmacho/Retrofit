package ipvc.estg.retrofit.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilderCountries {
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder() .baseUrl("https://restcountries.com/v3.1/") .addConverterFactory(GsonConverterFactory.create()) .client(client)
        .build()
    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}