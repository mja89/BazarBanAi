package ir.mjahmadi.bazarban.ai.ui.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.mjahmadi.bazarban.ai.data.model.Currency
import ir.mjahmadi.bazarban.ai.databinding.ItemCurrencyBinding

class CurrencyAdapter : ListAdapter<Currency, CurrencyAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCurrencyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemCurrencyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency) {
            binding.tvCode.text = currency.code
            binding.tvName.text = currency.nameFa
            binding.tvRate.text = String.format("%,.2f", currency.rate)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(old: Currency, new: Currency) = old.code == new.code
        override fun areContentsTheSame(old: Currency, new: Currency) = old == new
    }
}
