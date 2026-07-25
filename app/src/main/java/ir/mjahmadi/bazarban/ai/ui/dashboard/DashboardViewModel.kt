package ir.mjahmadi.bazarban.ai.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.mjahmadi.bazarban.ai.data.model.IranMarketItem
import ir.mjahmadi.bazarban.ai.data.repository.DataRepository
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val repository = DataRepository()

    private val _iranMarketData = MutableLiveData<List<IranMarketItem>>()
    val iranMarketData: LiveData<List<IranMarketItem>> = _iranMarketData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadIranMarketData() {
        viewModelScope.launch {
            _isLoading.value = true

            repository.getIranMarketData().fold(
                onSuccess = { items ->
                    _iranMarketData.value = items
                },
                onFailure = {
                    // خطا رو نادیده بگیر، Fragment خودش mock data داره
                }
            )

            _isLoading.value = false
        }
    }
}
