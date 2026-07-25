package ir.mjahmadi.bazarban.ai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.mjahmadi.bazarban.ai.databinding.BottomSheetMoreOptionsBinding
import ir.mjahmadi.bazarban.ai.ui.alert.SmartAlertFragment
import ir.mjahmadi.bazarban.ai.ui.crypto.CryptoFragment
import ir.mjahmadi.bazarban.ai.ui.forex.ForexFragment
import ir.mjahmadi.bazarban.ai.ui.iranmarket.IranMarketFragment
import ir.mjahmadi.bazarban.ai.ui.signal.SignalFragment

class MoreOptionsBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetMoreOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetMoreOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnIranMarket.setOnClickListener {
            navigateTo(IranMarketFragment())
        }

        binding.btnForex.setOnClickListener {
            navigateTo(ForexFragment())
        }

        binding.btnCrypto.setOnClickListener {
            navigateTo(CryptoFragment())
        }

        binding.btnSignal.setOnClickListener {
            navigateTo(SignalFragment())
        }

        binding.btnSmartAlert.setOnClickListener {
            navigateTo(SmartAlertFragment())
        }
    }

    private fun navigateTo(fragment: Fragment) {
        dismiss()
        (activity as? MainActivity)?.let {
            it.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
