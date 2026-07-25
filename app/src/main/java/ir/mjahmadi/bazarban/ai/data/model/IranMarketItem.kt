package ir.mjahmadi.bazarban.ai.data.model

data class IranMarketItem(
    val id: String,
    val name: String,
    val icon: String,
    val price: Double,
    val changePercent: Double,
    val updateTime: String = ""
)
