package seki.com.app.exampleandroidcoroutine

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.net.URL
import javax.inject.Singleton

interface ApiClient {

    companion object {
        const val URL_BASE = "https://api.github.com/"
        private const val USER = "users/n-seki"
    }

    @GET(USER)
    suspend fun getUser(): UserResponse
}

@Module
object ClientModule {

    @Provides
    @Singleton
    fun provideClient(): ApiClient {
        return Retrofit.Builder()
            .baseUrl(ApiClient.URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }
}