package gw.gobpo2005.rickandmorty.main_page.ui

import gw.gobpo2005.rickandmorty.common.mvvm.BaseMvvm
import gw.gobpo2005.rickandmorty.main_page.interactor.RickAndMortyInteractor
import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class ManePageViewModel(
    private val interactor: RickAndMortyInteractor
) : BaseMvvm() {

    private val _characterData = MutableStateFlow<CharacterData?>(null)
    val characterData = _characterData.asStateFlow()

    private val _characterDataName = MutableStateFlow<CharacterData?>(null)
    val characterDataName = _characterDataName.asStateFlow()

//    private val _nameData = MutableStateFlow<String?>("")
//    val nameData = _nameData.asStateFlow()

    fun getData(page: Int) {
        handle {
            val data = interactor.getData(page)
            _characterData.emit(data)
            Timber.i("viewModel from data --->> $data ")
        }
    }

    fun getDataName(name: String) {
        handle {
            val dataName = interactor.getDataName(name)
            _characterDataName.tryEmit(dataName)
            Timber.i("viewModel from dataName --->> $dataName")
        }
    }

//    fun addName(name: String?) {
//        if (name == null) return
//        _nameData.value = name
//    }
}