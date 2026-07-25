package ir.mjahmadi.bazarban.ai.vip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.mjahmadi.bazarban.ai.databinding.ActivityActivationBinding

class ActivationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActivationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnActivate.setOnClickListener {
            val code = binding.etActivationCode.text.toString()
            if (code.length == 16) {
                // TODO: Validate code
                binding.tvStatus.text = "✅ کد فعال شد!"
                binding.tvStatus.setTextColor(getColor(android.R.color.holo_green_light))
            } else {
                binding.tvStatus.text = "❌ کد نامعتبر است"
                binding.tvStatus.setTextColor(getColor(android.R.color.holo_red_light))
            }
        }
    }
}


