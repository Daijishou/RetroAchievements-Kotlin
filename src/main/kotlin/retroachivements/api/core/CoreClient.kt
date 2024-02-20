package retroachivements.api.core

import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.sun.org.slf4j.internal.LoggerFactory
import retroachivements.api.data.RetroCredentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retroachivements.api.core.DateFormatAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class CoreClient(credentials: RetroCredentials, baseUrl: String, debugging: Boolean = false) {
    // custom [OkHttpClient] client to add an authentication interceptor
    private var httpClient: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(AuthenticatorInterceptor(credentials))

        if (debugging) {
            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
        }
    }.build()

    private val gson = GsonBuilder()
        .registerTypeAdapterFactory(DateFormatAdapterFactory())
        .create()

    // custom [Retrofit] client to add custom [OkHttpClient] and add Gson (JSON) support
    protected val retroClient: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .baseUrl(baseUrl)
        .client(httpClient)
        .build()

    companion object {
        val logger = LoggerFactory.getLogger(CoreClient::class.java)
    }
}