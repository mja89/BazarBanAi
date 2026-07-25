package ir.mjahmadi.bazarban.ai.ui.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.mjahmadi.bazarban.ai.data.model.Currency
import ir.mjahmadi.bazarban.ai.data.repository.DataRepository
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {

    private val repository = DataRepository()

    private val _currencies = MutableLiveData<List<Currency>>()
    val currencies: LiveData<List<Currency>> = _currencies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadCurrencies() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            repository.getGlobalRates().fold(
                onSuccess = { list ->
                    _currencies.value = list
                },
                onFailure = { exception ->
                    _error.value = exception.message
                }
            )
            
            _isLoading.value = false
        }
    }
}
