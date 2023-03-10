package com.example.piu.APIs

import com.example.piu.model.DTO.AuthRequest
import com.example.piu.model.DTO.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatAPI {
    @POST("authenticate.php")
    @Headers( "Content-Type: application/json" )
    fun Authenticate(@Body body: AuthRequest): Call<AuthResponse>

        companion object {
        fun create(): ChatAPI{
             val httpInterceptor = HttpLoggingInterceptor().apply{
                // there are different logging levels that provide a various amount of detail
                // we will use the most detailed one
                this.level = HttpLoggingInterceptor.Level.BODY
            }
             val httpClient = OkHttpClient.Builder().apply {
                // add the interceptor to the newly created HTTP client
                this.addInterceptor ( httpInterceptor )
            }.build()
        val retrofit = Retrofit.Builder()
            .baseUrl ( "http://193.226.17.35/chat-piu/" )
            .addConverterFactory ( GsonConverterFactory.create() )
            .client ( httpClient )
            .build()
        val chatAPI = retrofit.create ( ChatAPI::class.java )
        return chatAPI
    }

    }
}