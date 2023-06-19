package gw.gobpo2005.rickandmorty.main_page.interactor

import gw.gobpo2005.rickandmorty.main_page.model.CharacterData
import gw.gobpo2005.rickandmorty.main_page.model.Hero
import gw.gobpo2005.rickandmorty.main_page.repository.local.MainLocalRepository
import gw.gobpo2005.rickandmorty.main_page.repository.remote.MainRemoteRepository
import gw.gobpo2005.rickandmorty.main_page.repository.remote.NameRemoteRepository
import kotlinx.coroutines.flow.Flow

class MainInteractor(
    private val remoteRepository: MainRemoteRepository,
    private val remoteRepositoryName: NameRemoteRepository,
    private val localRepository: MainLocalRepository,
) {

    suspend fun getHeroesFromDb(): Flow<List<Hero>> {
        return localRepository.getHeroes()
    }

    suspend fun loadHeroes(page: Int) {
        val heroes = remoteRepository.getData(page)
        localRepository.insertHeroesToDb(heroes)
    }

    suspend fun getDataName(name: String): CharacterData {
        return remoteRepositoryName.getDataName(name)
    }

//    suspend fun getData(page: Int): CharacterData {
//        return remoteRepository.getData(page)
//    }


}