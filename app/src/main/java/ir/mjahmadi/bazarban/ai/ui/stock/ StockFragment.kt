package ir.mjahmadi.bazarban.ai.ui.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.mjahmadi.bazarban.ai.databinding.FragmentStockBinding

class StockFragment : Fragment() {

    private var _binding: FragmentStockBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = "📈 بورس ایران"
        binding.tvSubtitle.text = "شاخص و اطلاعات بازار بورس تهران"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
