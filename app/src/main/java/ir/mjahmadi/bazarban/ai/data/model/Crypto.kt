package ir.mjahmadi.bazarban.ai.data.model

data class Crypto(
    val id: String,
    val symbol: String,
    val name: String,
    val nameFa: String,
    val price: Double,
    val changePercent24h: Double,
    val marketCap: Double,
    val volume24h: Double,
    val lastUpdate: Long
)
