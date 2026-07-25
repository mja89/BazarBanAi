package ir.mjahmadi.bazarban.ai.data.model

data class Stock(
    val symbol: String,
    val name: String,
    val nameFa: String,
    val price: Double,
    val changePercent: Double,
    val volume: Long,
    val lastUpdate: Long
)
