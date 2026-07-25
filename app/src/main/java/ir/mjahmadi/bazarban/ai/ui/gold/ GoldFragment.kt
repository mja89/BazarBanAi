package ir.mjahmadi.bazarban.ai.ui.gold

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.mjahmadi.bazarban.ai.databinding.FragmentGoldBinding

class GoldFragment : Fragment() {

    private var _binding: FragmentGoldBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoldBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = "🥇 قیمت طلا"
        binding.tvSubtitle.text = "قیمت لحظه‌ای طلا و سکه"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
