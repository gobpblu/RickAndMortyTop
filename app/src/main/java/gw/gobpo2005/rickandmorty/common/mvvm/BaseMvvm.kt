package gw.gobpo2005.rickandmorty.common.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseMvvm : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    protected fun handle(onDataHandler: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                onDataHandler()
            } catch (e: CancellationException) {
                Timber.e("error---->> ${e.message}")
            } catch (t: Throwable) {
                Timber.e("error----->>>${t.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }


}