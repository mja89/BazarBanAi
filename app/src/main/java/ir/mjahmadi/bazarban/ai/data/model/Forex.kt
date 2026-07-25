package ir.mjahmadi.bazarban.ai.data.model

data class Forex(
    val pair: String,
    val nameFa: String,
    val rate: Double,
    val changePercent: Double,
    val lastUpdate: Long
)
