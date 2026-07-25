package ir.mjahmadi.bazarban.ai.ui.crypto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.mjahmadi.bazarban.ai.databinding.FragmentCryptoBinding

class CryptoFragment : Fragment() {

    private var _binding: FragmentCryptoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = "₿ ارز دیجیتال"
        binding.tvSubtitle.text = "قیمت لحظه‌ای ارزهای دیجیتال"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
