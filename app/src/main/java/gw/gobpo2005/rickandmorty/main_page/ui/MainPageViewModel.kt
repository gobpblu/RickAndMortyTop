package gw.gobpo2005.rickandmorty.main_page.ui

import androidx.lifecycle.viewModelScope
import gw.gobpo2005.rickandmorty.common.mvvm.BaseMvvm
import gw.gobpo2005.rickandmorty.main_page.interactor.RickAndMortyInteractor
import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber

class MainPageViewModel(
    private val interactor: RickAndMortyInteractor
) : BaseMvvm() {
    private val _page = MutableStateFlow(1)
    @OptIn(ExperimentalCoroutinesApi::class)
    val characterData = _page.flatMapLatest {
//            handle {
//                val data = interactor.getData(it)
//            }

        flow {

            try {
                _isLoading.value = true
                val data = interactor.getData(it)
                emit(data)
            } catch (e: CancellationException) {
                Timber.e("error---->> ${e.message}")
            } catch (t: Throwable) {
                Timber.e("error----->>>${t.message}")
            } finally {
                _isLoading.value = false
            }
        }

    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _characterDataName = MutableStateFlow<CharacterData?>(null)
    val characterDataName = _characterDataName.asStateFlow()


//    private val _nameData = MutableStateFlow<String?>("")
//    val nameData = _nameData.asStateFlow()


    fun getDataName(name: String) {
        handle {
            val dataName = interactor.getDataName(name)
            _characterDataName.emit(dataName)
            Timber.i("viewModel from dataName --->> $dataName")
        }
    }

    fun changePage(page: Int) {
        _page.value = page
    }

//    fun addName(name: String?) {
//        if (name == null) return
//        _nameData.value = name
//    }
}