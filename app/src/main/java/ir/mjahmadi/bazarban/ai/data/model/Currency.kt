package ir.mjahmadi.bazarban.ai.data.model

data class Currency(
    val code: String,
    val nameFa: String,
    val rate: Double,
    val changePercent: Double = 0.0,
    val lastUpdate: Long = System.currentTimeMillis()
)
