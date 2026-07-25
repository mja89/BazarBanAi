package ir.mjahmadi.bazarban.ai.ui.dashboard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ir.mjahmadi.bazarban.ai.R
import ir.mjahmadi.bazarban.ai.data.model.IranMarketItem
import ir.mjahmadi.bazarban.ai.databinding.FragmentDashboardBinding
import ir.mjahmadi.bazarban.ai.vip.ActivationActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupVipBanner()
        showMockMarketData()
        setupObservers()
        viewModel.loadIranMarketData()
    }

    private fun setupObservers() {
        viewModel.iranMarketData.observe(viewLifecycleOwner) { items ->
            if (items.isNotEmpty()) {
                updateMarketItems(items)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressMarket.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun showMockMarketData() {
        binding.cardIranMarket.visibility = View.VISIBLE
        binding.progressMarket.visibility = View.GONE

        val mockItems = listOf(
            IranMarketItem("price_dollar_rl", "دلار آمریکا", "💵", 19200.0, 1.2),
            IranMarketItem("price_gold_18", "طلا ۱۸ عیار", "🥇", 2850000.0, 0.8),
            IranMarketItem("price_sekhe_emami", "سکه امامی", "🪙", 38500000.0, 0.5)
        )

        mockItems.getOrNull(0)?.let { updateItemView(binding.item1, it) }
        mockItems.getOrNull(1)?.let { updateItemView(binding.item2, it) }
        mockItems.getOrNull(2)?.let { updateItemView(binding.item3, it) }
    }

    private fun updateMarketItems(items: List<IranMarketItem>) {
        val importantItems = items.filter { 
            it.id in listOf("price_dollar_rl", "price_gold_18", "price_sekhe_emami") 
        }.take(3)

        importantItems.getOrNull(0)?.let { updateItemView(binding.item1, it) }
        importantItems.getOrNull(1)?.let { updateItemView(binding.item2, it) }
        importantItems.getOrNull(2)?.let { updateItemView(binding.item3, it) }
    }

    private fun updateItemView(view: View, item: IranMarketItem) {
        view.findViewById<TextView>(R.id.tvIcon)?.text = item.icon
        view.findViewById<TextView>(R.id.tvName)?.text = item.name
        view.findViewById<TextView>(R.id.tvPrice)?.text = formatPrice(item.price)
        
        val changeView = view.findViewById<TextView>(R.id.tvChange)
        val changeText = String.format("%.2f%%", item.changePercent)
        changeView?.text = if (item.changePercent >= 0) "+$changeText" else changeText
        val changeColor = if (item.changePercent >= 0) Color.parseColor("#00C853") else Color.parseColor("#FF1744")
        changeView?.setTextColor(changeColor)
    }

    private fun formatPrice(price: Double): String {
        return when {
            price >= 10_000_000 -> String.format("%,.1fM", price / 1_000_000)
            price >= 1_000_000 -> String.format("%,.2fM", price / 1_000_000)
            price >= 1_000 -> String.format("%,.0fK", price / 1_000)
            else -> String.format("%,.0f", price)
        }
    }

    private fun setupVipBanner() {
        binding.btnActivateVip.setOnClickListener {
            startActivity(Intent(requireContext(), ActivationActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
