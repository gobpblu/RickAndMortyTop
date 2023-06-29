package gw.gobpo2005.rickandmorty.main_page.interactor

import gw.gobpo2005.rickandmorty.main_page.repository.MainLocalRepositoryImpl
import gw.gobpo2005.rickandmorty.main_page.repository.remote.MainRemoteRepositoryImpl
import gw.gobpo2005.rickandmorty.main_page.repository.remote.NameRemoteRepositoryImpl
import gw.gobpo2005.rickandmorty.main_page.ui.model.HeroUi
import gw.gobpo2005.rickandmorty.main_page.ui.model.MainConverterUi
import kotlinx.coroutines.flow.Flow

class MainInteractor(
    private val remoteRepository: MainRemoteRepositoryImpl,
    private val remoteRepositoryName: NameRemoteRepositoryImpl,
    private val localRepository: MainLocalRepositoryImpl,
) {

    suspend fun getHeroesFromDb(): Flow<List<HeroUi>> {
        return localRepository.getHeroes()
    }

    suspend fun loadHeroes(page: Int) {
        val heroes = remoteRepository.getData(page)
        localRepository.insertHeroesToDb(heroes)
    }

    suspend fun getDataName(name: String): List<HeroUi> {
        val data = remoteRepositoryName.getDataName(name).result
        return MainConverterUi.fromNetwork(data)
    }

//    suspend fun getData(page: Int): CharacterData {
//        return remoteRepository.getData(page)
//    }


}