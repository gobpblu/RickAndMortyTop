package gw.gobpo2005.rickandmorty.main_page.ui

import androidx.lifecycle.viewModelScope
import gw.gobpo2005.rickandmorty.common.mvvm.BaseMvvm
import gw.gobpo2005.rickandmorty.main_page.interactor.MainInteractor
import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import gw.gobpo2005.rickandmorty.main_page.model.Hero
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class MainPageViewModel(
    private val interactor: MainInteractor,
) : BaseMvvm() {
    private val _pageEveryCharacter = MutableStateFlow(1)
    private val _pageNameCharacter = MutableStateFlow(1)//for pagination by name

    private val _characterDataName = MutableStateFlow<CharacterData?>(null)
    val characterDataName = _characterDataName.asStateFlow()

    private val _heroesData = MutableStateFlow<List<Hero>>(emptyList())
    val heroesData = _heroesData.asStateFlow()

    private val _nameData = MutableStateFlow<String?>("")
    val nameData = _nameData.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val characterData = _pageEveryCharacter.flatMapLatest {
        flow {
            try {
                _isLoading.value = true
                val data = interactor.loadHeroes(it)
                emit(data)
            } catch (e: CancellationException) {
                Timber.e("error---->> ${e.message}")
                emit(null)
            } catch (t: Throwable) {
                Timber.e("error----->>>${t.message}")
                emit(null)
            } finally {
                _isLoading.value = false
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)


    init {
       getHeroes()
    }

    fun getDataName(name: String) {
        handle {
            val dataName = interactor.getDataName(name)
            _characterDataName.emit(dataName)
            Timber.i("viewModel from dataName --->> $dataName")
        }
    }

    fun changePage(page: Int) {
        _pageEveryCharacter.value = page
    }

    fun changePageByName(page: Int) {//for pagination by name
        _pageNameCharacter.value = page
    }

    fun getHeroes(){
        viewModelScope.launch {
            interactor.getHeroesFromDb()
                .onEach {
                    if (it.isEmpty()) {
                        interactor.loadHeroes(2)
                    }
                }
                .collect {
                    _heroesData.value = it
                }
        }
    }


//    fun addName(name: String?) {
//        if (name == null) return
//        _nameData.value = name
//    }
}