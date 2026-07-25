package ir.mjahmadi.bazarban.ai.ui.signal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.mjahmadi.bazarban.ai.databinding.FragmentSignalBinding
import ir.mjahmadi.bazarban.ai.vip.VipManager

class SignalFragment : Fragment() {

    private var _binding: FragmentSignalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val vipManager = VipManager(requireContext())
        
        if (vipManager.isVip()) {
            showVipContent()
        } else {
            showLockedContent()
        }
    }

    private fun showVipContent() {
        binding.tvTitle.text = "🔓 سیگنال‌های VIP"
        binding.tvSubtitle.text = "سیگنال‌های حرفه‌ای با فیلترهای تکنیکال"
        binding.tvLockMessage.visibility = View.GONE
        binding.btnActivate.visibility = View.GONE
    }

    private fun showLockedContent() {
        binding.tvTitle.text = "🔒 سیگنال‌های VIP"
        binding.tvSubtitle.text = "برای مشاهده سیگنال‌ها اشتراک VIP فعال کنید"
        binding.tvLockMessage.visibility = View.VISIBLE
        binding.btnActivate.visibility = View.VISIBLE
        
        binding.btnActivate.setOnClickListener {
            // Navigate to activation
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
