package ir.mjahmadi.bazarban.ai.data.repository

import ir.mjahmadi.bazarban.ai.data.api.RetrofitClient
import ir.mjahmadi.bazarban.ai.data.api.TgjuApi
import ir.mjahmadi.bazarban.ai.data.model.Currency
import ir.mjahmadi.bazarban.ai.data.model.Gold
import ir.mjahmadi.bazarban.ai.data.model.IranMarketItem

class DataRepository {

    private val apiService = RetrofitClient.currencyApi

    suspend fun getIranMarketData(): Result<List<IranMarketItem>> {
        return TgjuApi.getIranMarketData()
    }

    suspend fun getGlobalRates(): Result<List<Currency>> {
        return try {
            val response = apiService.getExchangeRates("USD")
            if (response.isSuccessful) {
                val body = response.body()
                val currencies = body?.rates?.map { (code, rate) ->
                    Currency(
                        code = code,
                        nameFa = getCurrencyNameFa(code),
                        rate = rate
                    )
                } ?: emptyList()
                Result.success(currencies)
            } else {
                Result.failure(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getGoldPrices(): Result<List<Gold>> {
        return try {
            val golds = listOf(
                Gold("18ayar", "طلا ۱۸ عیار", 2850000.0, 1.2, System.currentTimeMillis()),
                Gold("24ayar", "طلا ۲۴ عیار", 3800000.0, 1.5, System.currentTimeMillis()),
                Gold("sekeh", "سکه امامی", 38000000.0, 0.8, System.currentTimeMillis())
            )
            Result.success(golds)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getCurrencyNameFa(code: String): String {
        return when (code) {
            "USD" -> "دلار آمریکا"
            "EUR" -> "یورو"
            "GBP" -> "پوند انگلیس"
            "JPY" -> "ین ژاپن"
            "CNY" -> "یوان چین"
            "TRY" -> "لیر ترکیه"
            "AED" -> "درهم امارات"
            "IRR" -> "ریال ایران"
            else -> code
        }
    }
}
