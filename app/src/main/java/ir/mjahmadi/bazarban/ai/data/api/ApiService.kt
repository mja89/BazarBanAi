package ir.mjahmadi.bazarban.ai.data.api

import ir.mjahmadi.bazarban.ai.data.model.Currency
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query  // ← این خط اضافه شد!

interface ApiService {

    // Exchange Rate API - رایگان
    @GET("v4/latest/{base}")
    suspend fun getExchangeRates(
        @Path("base") baseCurrency: String = "USD"
    ): Response<ExchangeRateResponse>

    // CoinGecko API - ارز دیجیتال
    @GET("api/v3/coins/markets")
    suspend fun getCryptoMarkets(
        @Query("vs_currency") currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 50,
        @Query("page") page: Int = 1
    ): Response<List<CryptoResponse>>
}

// Response models
data class ExchangeRateResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)

data class CryptoResponse(
    val id: String,
    val symbol: String,
    val name: String,
    val current_price: Double,
    val price_change_percentage_24h: Double,
    val market_cap: Double,
    val total_volume: Double
)
