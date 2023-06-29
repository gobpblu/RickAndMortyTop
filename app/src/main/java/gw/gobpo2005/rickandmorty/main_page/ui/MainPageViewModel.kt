package gw.gobpo2005.rickandmorty.main_page.ui

import androidx.lifecycle.viewModelScope
import gw.gobpo2005.rickandmorty.common.mvvm.BaseMvvm
import gw.gobpo2005.rickandmorty.main_page.interactor.MainInteractor
import gw.gobpo2005.rickandmorty.main_page.ui.model.HeroUi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class MainPageViewModel(
    private val interactor: MainInteractor,
) : BaseMvvm() {
    private val _pageEveryCharacter = MutableStateFlow(1)
    private val page = _pageEveryCharacter.onEach {
        Timber.d("_____ view model On each $it")
        handle { interactor.loadHeroes(it) }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, 1)
    private val _pageNameCharacter = MutableStateFlow(1)//for pagination by name

    private val _characterDataName = MutableStateFlow<List<HeroUi>>(emptyList())
    val characterDataName = _characterDataName.asStateFlow()

    private val _heroesData = MutableStateFlow<List<HeroUi>>(emptyList())
    val heroesData = _heroesData.asStateFlow()

    private val _nameData = MutableStateFlow<String?>("")
    val nameData = _nameData.asStateFlow()


    init {
        getHeroes()
    }

    fun getDataName(name: String) {
        handle {
            val dataName = interactor.getDataName(name)
            _characterDataName.emit(dataName)
            Timber.i("__viewModel from dataName --->> $dataName")
        }
    }

    fun changePage(page: Int) {
        _pageEveryCharacter.value = page
    }

//    fun changePageByName(page: Int) {//for pagination by name
//        _pageNameCharacter.value = page
//    }

    fun getHeroes() {
        viewModelScope.launch {
            interactor.getHeroesFromDb()
                .onEach {
                    if (it.isEmpty()) {
                        loadHero(1)
                    }
                }
                .collect {
                    _heroesData.value = it
                    Timber.i("__viewModel from getHeroes")

                }
        }
    }

    fun loadHero(page: Int) {
        handle {
            interactor.loadHeroes(page)
        }
    }


//    fun addName(name: String?) {
//        if (name == null) return
//        _nameData.value = name
//    }
}