package ir.mjahmadi.bazarban.ai.data.model

data class Gold(
    val type: String,
    val nameFa: String,
    val price: Double,
    val changePercent: Double,
    val lastUpdate: Long
)
