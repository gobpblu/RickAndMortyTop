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

    fun getData(page : Int) {
        handle {
            val data = interactor.getData(page)
            _characterData.emit(data)
            Timber.i ("viewModel --->> $data ")
        }
    }
}