package ir.mjahmadi.bazarban.ai.data.model

data class Alert(
    val id: String,
    val title: String,
    val description: String,
    val severity: AlertSeverity,
    val relatedMarkets: List<String>,
    val timestamp: Long,
    val isRead: Boolean = false
)

enum class AlertSeverity {
    LOW, MEDIUM, HIGH, CRITICAL
}
