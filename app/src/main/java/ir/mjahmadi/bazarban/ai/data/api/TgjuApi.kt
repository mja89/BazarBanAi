package ir.mjahmadi.bazarban.ai.data.api

import ir.mjahmadi.bazarban.ai.data.model.IranMarketItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

object TgjuApi {

    private const val PRIMARY_URL = "https://call.tgju.org/ajax.json?client=app&key=public"
    private const val ALT_URL = "https://api.tgju.org/v1/market/indicator/list"

    suspend fun getIranMarketData(): Result<List<IranMarketItem>> = withContext(Dispatchers.IO) {
        try {
            var data = fetchData(PRIMARY_URL)
            if (data == null) {
                data = fetchData(ALT_URL)
            }
            
            if (data == null) {
                return@withContext Result.failure(Exception("اتصال به سرور ناموفق"))
            }

            val items = parseData(data)
            if (items.isEmpty()) {
                Result.failure(Exception("داده‌ای یافت نشد"))
            } else {
                Result.success(items)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun fetchData(urlString: String): String? {
        return try {
            val url = URL(urlString)
            val connection = url.openConnection()
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 10)")
            connection.setRequestProperty("Accept", "application/json")
            connection.connectTimeout = 15000
            connection.readTimeout = 15000
            connection.getInputStream().bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            null
        }
    }

    private fun parseData(jsonString: String): List<IranMarketItem> {
        val items = mutableListOf<IranMarketItem>()
        try {
            val json = JSONObject(jsonString)
            
            // نگاشت کلیدها
            val mappings = mapOf(
                "price_dollar_rl" to Pair("دلار آمریکا", "💵"),
                "price_eur" to Pair("یورو", "💶"),
                "price_gbp" to Pair("پوند انگلیس", "💷"),
                "price_aed" to Pair("درهم امارات", "🇦🇪"),
                "price_try" to Pair("لیر ترکیه", "🇹🇷"),
                "price_cny" to Pair("یوان چین", "🇨🇳"),
                "price_gold_18" to Pair("طلا ۱۸ عیار", "🥇"),
                "price_gold_24" to Pair("طلا ۲۴ عیار", "🥇"),
                "price_gold_mesghal" to Pair("مثقال طلا", "⚖️"),
                "price_gold_gram" to Pair("گرم طلا", "⚖️"),
                "price_sekhe_emami" to Pair("سکه امامی", "🪙"),
                "price_sekhe_bahar_azadi" to Pair("سکه بهار آزادی", "🪙"),
                "price_nim" to Pair("نیم سکه", "🪙"),
                "price_rob" to Pair("ربع سکه", "🪙")
            )

            for ((key, pair) in mappings) {
                val itemJson = json.optJSONObject(key)
                if (itemJson != null) {
                    val priceStr = itemJson.optString("p", "0").replace(",", "").replace("٬", "")
                    val price = priceStr.toDoubleOrNull() ?: 0.0
                    val changeStr = itemJson.optString("dp", "0").replace("%", "").replace("٪", "")
                    val change = changeStr.toDoubleOrNull() ?: 0.0

                    if (price > 0) {
                        items.add(IranMarketItem(
                            id = key,
                            name = pair.first,
                            icon = pair.second,
                            price = price,
                            changePercent = change
                        ))
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return items
    }

    // داده آزمایشی واقع‌گرایانه
    fun getMockData(): List<IranMarketItem> {
        return listOf(
            IranMarketItem("price_dollar_rl", "دلار آمریکا", "💵", 19200.0, 1.2),
            IranMarketItem("price_eur", "یورو", "💶", 20800.0, 0.8),
            IranMarketItem("price_gbp", "پوند انگلیس", "💷", 24200.0, -0.3),
            IranMarketItem("price_aed", "درهم امارات", "🇦🇪", 5280.0, 0.5),
            IranMarketItem("price_try", "لیر ترکیه", "🇹🇷", 580.0, -1.2),
            IranMarketItem("price_cny", "یوان چین", "🇨🇳", 2650.0, 0.3),
            IranMarketItem("price_gold_18", "طلا ۱۸ عیار", "🥇", 2850000.0, 0.8),
            IranMarketItem("price_gold_24", "طلا ۲۴ عیار", "🥇", 3800000.0, 1.1),
            IranMarketItem("price_gold_mesghal", "مثقال طلا", "⚖️", 12350000.0, 0.9),
            IranMarketItem("price_gold_gram", "گرم طلا", "⚖️", 855000.0, 0.7),
            IranMarketItem("price_sekhe_emami", "سکه امامی", "🪙", 38500000.0, 0.5),
            IranMarketItem("price_sekhe_bahar_azadi", "سکه بهار آزادی", "🪙", 38200000.0, 0.4),
            IranMarketItem("price_nim", "نیم سکه", "🪙", 21000000.0, 0.6),
            IranMarketItem("price_rob", "ربع سکه", "🪙", 12500000.0, 0.3)
        )
    }
}
