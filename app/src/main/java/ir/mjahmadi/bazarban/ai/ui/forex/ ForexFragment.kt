package ir.mjahmadi.bazarban.ai.ui.forex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.mjahmadi.bazarban.ai.databinding.FragmentForexBinding

class ForexFragment : Fragment() {

    private var _binding: FragmentForexBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = "💹 فارکس"
        binding.tvSubtitle.text = "نرخ جفت‌ارزهای فارکس"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
