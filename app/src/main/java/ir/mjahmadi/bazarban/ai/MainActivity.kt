package ir.mjahmadi.bazarban.ai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ir.mjahmadi.bazarban.ai.databinding.ActivityMainBinding
import ir.mjahmadi.bazarban.ai.ui.currency.CurrencyFragment
import ir.mjahmadi.bazarban.ai.ui.dashboard.DashboardFragment
import ir.mjahmadi.bazarban.ai.ui.gold.GoldFragment
import ir.mjahmadi.bazarban.ai.ui.stock.StockFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        
        if (savedInstanceState == null) {
            loadFragment(DashboardFragment())
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    loadFragment(DashboardFragment())
                    true
                }
                R.id.nav_currency -> {
                    loadFragment(CurrencyFragment())
                    true
                }
                R.id.nav_gold -> {
                    loadFragment(GoldFragment())
                    true
                }
                R.id.nav_stock -> {
                    loadFragment(StockFragment())
                    true
                }
                R.id.nav_more -> {
                    showMoreOptions()
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun showMoreOptions() {
        val bottomSheet = MoreOptionsBottomSheet()
        bottomSheet.show(supportFragmentManager, "MoreOptions")
    }
}
