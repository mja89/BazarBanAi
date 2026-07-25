package ir.mjahmadi.bazarban.ai.vip

import android.content.Context
import android.content.SharedPreferences

class VipManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "bazarban_vip"
        private const val KEY_IS_VIP = "is_vip"
        private const val KEY_EXPIRY_DATE = "expiry_date"
    }

    fun isVip(): Boolean {
        if (!prefs.getBoolean(KEY_IS_VIP, false)) return false
        
        val expiry = prefs.getLong(KEY_EXPIRY_DATE, 0)
        return System.currentTimeMillis() < expiry
    }

    fun activateVip(days: Int) {
        val expiryDate = System.currentTimeMillis() + (days * 24 * 60 * 60 * 1000)
        prefs.edit().apply {
            putBoolean(KEY_IS_VIP, true)
            putLong(KEY_EXPIRY_DATE, expiryDate)
            apply()
        }
    }

    fun deactivateVip() {
        prefs.edit().apply {
            putBoolean(KEY_IS_VIP, false)
            remove(KEY_EXPIRY_DATE)
            apply()
        }
    }

    fun getRemainingDays(): Int {
        if (!isVip()) return 0
        val expiry = prefs.getLong(KEY_EXPIRY_DATE, 0)
        val remaining = expiry - System.currentTimeMillis()
        return (remaining / (24 * 60 * 60 * 1000)).toInt()
    }
}
