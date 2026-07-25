package ir.mjahmadi.bazarban.ai.ui.iranmarket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.mjahmadi.bazarban.ai.data.api.TgjuApi
import ir.mjahmadi.bazarban.ai.data.model.IranMarketItem
import ir.mjahmadi.bazarban.ai.data.repository.DataRepository
import kotlinx.coroutines.launch

class IranMarketViewModel : ViewModel() {

    private val repository = DataRepository()

    private val _items = MutableLiveData<List<IranMarketItem>>()
    val items: LiveData<List<IranMarketItem>> = _items

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadIranMarketData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            repository.getIranMarketData().fold(
                onSuccess = { list ->
                    if (list.isEmpty()) {
                        // اگه API خالی برگردوند، داده آزمایشی نشون بده
                        _items.value = TgjuApi.getMockData()
                    } else {
                        _items.value = list
                    }
                },
                onFailure = { 
                    // اگه API خطا داد، داده آزمایشی نشون بده
                    _items.value = TgjuApi.getMockData()
                }
            )
            
            _isLoading.value = false
        }
    }
}
