package ir.mjahmadi.bazarban.ai.ui.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.mjahmadi.bazarban.ai.databinding.FragmentSmartAlertBinding

class SmartAlertFragment : Fragment() {

    private var _binding: FragmentSmartAlertBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmartAlertBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = "⚡ هوشمند هشدار"
        binding.tvSubtitle.text = "تحلیل همبستگی بازارها"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
