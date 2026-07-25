package ir.mjahmadi.bazarban.ai.ui.iranmarket

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.mjahmadi.bazarban.ai.data.model.IranMarketItem
import ir.mjahmadi.bazarban.ai.databinding.ItemIranMarketBinding

class IranMarketAdapter : ListAdapter<IranMarketItem, IranMarketAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIranMarketBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemIranMarketBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IranMarketItem) {
            binding.tvIcon.text = item.icon
            binding.tvName.text = item.name
            binding.tvPrice.text = String.format("%,.0f", item.price)
            
            val changeColor = if (item.changePercent >= 0) 
                Color.parseColor("#00C853") 
            else 
                Color.parseColor("#FF1744")
            binding.tvChange.setTextColor(changeColor)
            binding.tvChange.text = String.format("%.2f%%", item.changePercent)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<IranMarketItem>() {
        override fun areItemsTheSame(old: IranMarketItem, new: IranMarketItem) = old.id == new.id
        override fun areContentsTheSame(old: IranMarketItem, new: IranMarketItem) = old == new
    }
}
