package ir.mjahmadi.bazarban.ai.data.model

data class Signal(
    val id: String,
    val symbol: String,
    val nameFa: String,
    val type: SignalType, // BUY or SELL
    val entryPrice: Double,
    val stopLoss: Double,
    val target1: Double,
    val target2: Double,
    val filters: List<String>,
    val confidence: Int, // 0-100
    val timestamp: Long,
    val isVip: Boolean = true
)

enum class SignalType {
    BUY, SELL, NEUTRAL
}
