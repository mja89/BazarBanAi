package ir.mjahmadi.bazarban.ai.utils

object Constants {
    
    // API URLs
    const val BASE_URL_CURRENCY = "https://api.exchangerate-api.com/v4/latest/"
    const val BASE_URL_CRYPTO = "https://api.coingecko.com/api/v3/"
    
    // Cache
    const val CACHE_DURATION_MINUTES = 5
    
    // VIP
    const val VIP_CODE_LENGTH = 16
    
    // Signal Filters
    const val RSI_PERIOD = 14
    const val MA_SHORT_PERIOD = 50
    const val MA_LONG_PERIOD = 200
    const val BOLLINGER_PERIOD = 20
    const val BOLLINGER_DEVIATION = 2.0
    
    // Alert Thresholds
    const val PRICE_CHANGE_THRESHOLD = 2.0 // 2%
    const val VOLUME_SPIKE_THRESHOLD = 1.5 // 1.5x average
}
